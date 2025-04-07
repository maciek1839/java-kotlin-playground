package com.showmeyourcode.playground.kotlin.code.exercise.cache

import java.time.Clock
import java.time.Duration
import java.util.PriorityQueue
import kotlin.collections.LinkedHashMap

interface LRUCache<K : Any, V : Any> {
    val size: Int

    fun get(key: K): V?

    fun put(
        key: K,
        value: V
    )
}

// Implementation is using  combination of HashMap and LinkedList.
// Time Complexity: O(1)
class LRUCacheImpl1<K : Any, V : Any>(private val capacity: Int) : LRUCache<K, V> {
    private val map = mutableMapOf<K, CacheItem<K, V>>()

    private var head: CacheItem<K, V>? = null
    private var tail: CacheItem<K, V>? = null

    override val size get() = map.size

    override fun put(
        key: K,
        value: V
    ) {
        // Check if node exits
        val existingCacheItem = map[key]

        if (existingCacheItem == null) {
            // Check Map capacity
            if (map.size >= capacity) {
                val removedNode = removeHead()
                if (removedNode != null) {
                    map.remove(removedNode.key)
                }
                map.remove(removedNode?.key)
            }

            // Add a new node
            val newCacheItem = CacheItem(key, value)

            map[key] = newCacheItem
            addTail(newCacheItem)
        } else {
            existingCacheItem.value = value
            moveToTail(existingCacheItem)
        }
    }

    private fun addTail(cacheItem: CacheItem<K, V>) {
        // If list is empty
        if (head == null) {
            head = cacheItem
        } else {
            cacheItem.prev = tail
            tail?.next = cacheItem
        }

        tail = cacheItem
    }

    private fun removeHead(): CacheItem<K, V>? {
        // Head exists
        if (head != null) {
            // Store current head to return
            val cacheItem = head

            // Remove head
            head = head?.next
            head?.prev = null

            // Remove tail if head is tail
            if (cacheItem == tail) tail = null

            return cacheItem
        }

        return null
    }

    override fun get(key: K): V? {
        // Get the node
        val node = map[key]

        // Move to tail if exists
        if (node != null) {
            moveToTail(node)
        }

        // Return value
        return node?.value
    }

    private fun moveToTail(cacheItem: CacheItem<K, V>) {
        // Check if node is tail
        if (cacheItem != tail) {
            // Remove node from list
            if (cacheItem == head) {
                head = cacheItem.next
            } else {
                cacheItem.prev?.next = cacheItem.next
                cacheItem.next?.prev = cacheItem.prev
            }

            // Add node to tail
            addTail(cacheItem)
        }
    }

    private data class CacheItem<K, V>(
        val key: K,
        var value: V,
        var prev: CacheItem<K, V>? = null,
        var next: CacheItem<K, V>? = null
    )
}

// Implementation using LinkedHashMap
// Time Complexity: O(1)
class LRUCacheImpl2<K : Any, V : Any>(private val capacity: Int) : LRUCache<K, V> {
    override val size get() = linkedHashMap.size

    private val linkedHashMap =
        object :
            LinkedHashMap<K, V>(capacity, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
                return size > capacity
            }
        }

    override fun put(
        key: K,
        value: V
    ) {
        linkedHashMap[key] = value
    }

    override fun get(key: K): V? {
        return linkedHashMap[key]?.also {
            linkedHashMap.remove(key)
            linkedHashMap[key] = it
        }
    }
}

// Implementation using List
// Time Complexity: O(n)
class LRUCacheImpl3<K : Any, V : Any>(private val capacity: Int) : LRUCache<K, V> {
    private val list = mutableListOf<Pair<K, V>>()

    override val size get() = list.size

    override fun get(key: K): V? {
        val value = list.firstOrNull { it.first == key }?.second

        if (value != null) {
            val pair = Pair(key, value)
            list.remove(pair)
            list.add(pair)
        }

        return list.firstOrNull { it.first == key }?.second
    }

    override fun put(
        key: K,
        value: V
    ) {
        list.removeIf { it.first == key }
        list.add(Pair(key, value))

        if (list.size > capacity) {
            list.removeFirst()
        }
    }
}

class AdvancedLRUCache<K : Any, V : Any>(
    private val capacity: Int,
    private val clock: Clock = Clock.systemDefaultZone()
) : LRUCache<K, V> {
    private val map: MutableMap<K, CacheItem<K, V>> = mutableMapOf()

    private val expiryQueue: PriorityQueue<CacheItem<K, V>> =
        PriorityQueue { item1, item2 ->
            compareBy<CacheItem<K, V>>({ it.expiryTime }).compare(item1, item2)
        }

    private val priorityQueue: PriorityQueue<CacheItem<K, V>> =
        PriorityQueue { item1, item2 ->
            compareBy<CacheItem<K, V>>({ it.priority }, { it.lastUsed }).compare(item1, item2)
        }

    override val size: Int
        get() = map.size

    override fun put(
        key: K,
        value: V
    ) {
        // Default to priority 0 and TTL of 1 hour
        put(key, value, priority = 0, ttl = Duration.ofHours(1))
    }

    fun put(
        key: K,
        value: V,
        priority: Int,
        ttl: Duration
    ) {
        remove(key)
        checkAndExpireCachedItems()

        val now = clock.millis()
        val item = CacheItem(key, value, priority, now + ttl.toMillis(), now)
        map[key] = item

        expiryQueue.offer(item)
        priorityQueue.offer(item)
    }

    override fun get(key: K): V? {
        val now = clock.millis()
        val item = map[key]

        return if (item == null || item.expiryTime < now) {
            if (item != null) {
                expiryQueue.remove(item)
                priorityQueue.remove(item)
                map.remove(key)
            }
            null
        } else {
            priorityQueue.remove(item)
            priorityQueue.add(item.touch(now))
            item.value
        }
    }

    private fun remove(key: K) {
        map[key]?.let {
            expiryQueue.remove(it)
            priorityQueue.remove(it)
            map.remove(key)
        }
    }

    private fun checkAndExpireCachedItems() {
        val now = clock.millis()

        while (expiryQueue.isNotEmpty() && expiryQueue.peek().expiryTime < now) {
            val item = expiryQueue.poll()
            map.remove(item.key)
            priorityQueue.remove(item)
        }

        if (map.size < capacity) return

        if (priorityQueue.isNotEmpty()) {
            val item = priorityQueue.poll()
            map.remove(item.key)
            expiryQueue.remove(item)
        }
    }

    private class CacheItem<K, V>(
        val key: K,
        val value: V,
        val priority: Int,
        val expiryTime: Long,
        val lastUsed: Long
    ) {
        override fun equals(other: Any?): Boolean {
            return this === other || (other is CacheItem<*, *> && key == other.key)
        }

        override fun hashCode(): Int = key.hashCode()

        fun touch(now: Long = this.lastUsed): CacheItem<K, V> = CacheItem(key, value, priority, expiryTime, now)

        override fun toString(): String =
            "CacheItem(key=$key, value=$value, priority=$priority, expiryTime=$expiryTime, lastUsed=$lastUsed)"
    }
}
