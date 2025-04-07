package com.showmeyourcode.playground.kotlin.code.exercise.cache

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LRUCacheTest : AnnotationSpec() {
    private fun provideCaches(capacity: Int): List<LRUCache<String, String>> {
        return listOf(
            LRUCacheImpl1(capacity),
            LRUCacheImpl2(capacity),
            LRUCacheImpl3(capacity),
            AdvancedLRUCache(capacity)
        )
    }

    @Test
    fun `should cache and evict correctly`() {
        val capacity = 2

        provideCaches(capacity).forEach { cache ->
            cache.put("A", "1")
            cache.put("B", "2")
            cache.get("A") shouldBe "1"
            cache.put("C", "3") // should evict "B"
            cache.get("B") shouldBe null
            cache.get("A") shouldBe "1"
            cache.get("C") shouldBe "3"
        }
    }

    @Test
    fun `should update value and reorder correctly`() {
        val capacity = 2

        provideCaches(capacity).forEach { cache ->
            cache.put("X", "100")
            cache.put("Y", "200")
            cache.put("X", "999") // update value and move to recent
            cache.put("Z", "300") // should evict "Y"
            cache.get("Y") shouldBe null
            cache.get("X") shouldBe "999"
            cache.get("Z") shouldBe "300"
        }
    }

    @Test
    fun `should respect capacity and maintain order`() {
        forAll(
            row(2, listOf("1", "2", "3"), listOf("2", "3")),
            row(3, listOf("1", "2", "3", "4"), listOf("2", "3", "4")),
            row(1, listOf("A", "B"), listOf("B"))
        ) { cap, inputs, expectedKeys ->
            provideCaches(cap).forEach { cache ->
                inputs.forEach { cache.put(it, it) }

                expectedKeys.forEach { key ->
                    cache.get(key) shouldBe key
                }

                // Ensure all other keys are evicted
                val evictedKeys = inputs.filterNot { it in expectedKeys }
                evictedKeys.forEach { key ->
                    cache.get(key) shouldBe null
                }
            }
        }
    }
}
