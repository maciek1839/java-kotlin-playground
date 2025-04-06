package com.showmeyourcode.playground.kotlin.code

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigInteger
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random

object Coroutines {
    val LOGGER: Logger = LoggerFactory.getLogger(Coroutines::class.java)

    // https://kotlinlang.org/docs/whatsnew13.html#coroutines-release
    // https://kotlinlang.org/docs/coroutines-overview.html
    // https://kotlinlang.org/spec/asynchronous-programming-with-coroutines.html
    // https://silica.io/understanding-kotlin-coroutines/
    fun main() =
        runBlocking {
            Logging.LOGGER.info("\n{} Coroutines", Descriptions.INDENT1)
            Logging.LOGGER.info(
                """
                A coroutine is an instance of a suspendable computation.
                
                In Kotlin, coroutines are used to implement suspending functions
                and can switch contexts only at suspension points.
                A call to a suspending function creates and starts a coroutine.
                """.trimIndent()
            )

            basicCoroutines()
            asyncAwait()
            dispatchers()
            coroutineStartOptions()
            customValuesCoroutineContext()
            structuredConcurrency()
            coroutineContextDemo()
            exceptionHandling()
            cooperativeSuspension()
            manualSuspensionAndResumption()
            manualSuspensionAndResumption2()
            try {
                val result = fetchDataWithError()
                println("Success: $result")
            } catch (e: Exception) {
                println("Caught exception: ${e.message}")
            }
            suspendCoroutine(this)

            coroutineFlows()
            channels()
            channelTypes()
            channelBufferOverflowStrategies()

            synchronization()
        }

    private fun basicCoroutines() =
        runBlocking {
            val job1 =
                GlobalScope.launch {
                    delay(1000L)
                    LOGGER.info("Coroutines global scope...")
                }

            val job2 =
                GlobalScope.launch {
                    val result =
                        async {
                            doComputation("GlobalScope.async")
                        }
                    LOGGER.info("Computed result: ${result.await()}")
                }

            // Wait for the GlobalScope jobs to finish
            job1.join()
            job2.join()

            // Run the blocking coroutine as before
            launch { doComputation("runBlocking") }
        }

    private fun asyncAwait() =
        runBlocking {
            // Launch two asynchronous tasks using async
            val deferred1 =
                async {
                    delay(1000L) // Simulate a long-running task
                    println("Task 1 completed")
                    "Result from Task 1"
                }

            val deferred2 =
                async {
                    delay(1500L) // Simulate a longer task
                    println("Task 2 completed")
                    "Result from Task 2"
                }

            // Wait for both tasks to complete using await
            val result1 = deferred1.await() // Suspends until the result is ready
            val result2 = deferred2.await() // Suspends until the result is ready

            // Print the results
            println("Received results: $result1, $result2")
        }

    private fun dispatchers() =
        runBlocking {
            repeat(1000) { index ->
                launch(Dispatchers.Default) { // Default dispatcher
                    // Simulate some work
                    List(1000) { Random.nextLong() }.maxOrNull()

                    val threadName = Thread.currentThread().name
                    println("Default Dispatcher, Coroutine #$index: Running on thread: $threadName")
                }

                launch(Dispatchers.IO) { // IO dispatcher
                    // Simulate some I/O operation
                    List(1000) { Random.nextLong() }.maxOrNull()

                    val threadName = Thread.currentThread().name
                    println("IO Dispatcher, Coroutine #$index: Running on thread: $threadName")
                }

                // Explanation:
                // Initial Thread: The coroutine starts execution on the thread it was launched from.
                // Suspension Point: After the first computation, we call delay(500L), which causes the coroutine to suspend.
                // Thread Switch: After suspension, the coroutine resumes execution on a different thread, which might not be the one it started on.
                repeat(3) { index ->
                    launch(Dispatchers.Unconfined) { // Unconfined dispatcher
                        println("Starting Unconfined Coroutine #$index on thread: ${Thread.currentThread().name}")

                        // Simulate work
                        List(1000) { Random.nextLong() }.maxOrNull()

                        // Suspension point - this will cause a thread switch
                        delay(500L)

                        println("Resuming Unconfined Coroutine #$index on thread: ${Thread.currentThread().name}")
                    }
                }
            }
        }

    private fun coroutineStartOptions() =
        runBlocking {
            println("Main starts: ${Thread.currentThread().name}")

            val jobDefault =
                launch(start = CoroutineStart.DEFAULT) {
                    println("DEFAULT: ${Thread.currentThread().name}")
                }

            val jobLazy =
                launch(start = CoroutineStart.LAZY) {
                    println("LAZY: ${Thread.currentThread().name}")
                }
            jobLazy.start() // Manually start it

            val jobAtomic =
                launch(start = CoroutineStart.ATOMIC) {
                    println("ATOMIC: ${Thread.currentThread().name}")
                    delay(500L)
                    println("Resumed ATOMIC: ${Thread.currentThread().name}")
                }

            val jobUndispatched =
                launch(start = CoroutineStart.UNDISPATCHED) {
                    println("UNDISPATCHED: ${Thread.currentThread().name}")
                    delay(500L)
                    println("Resumed UNDISPATCHED: ${Thread.currentThread().name}")
                }

            jobDefault.join()
            jobLazy.join()
            jobAtomic.join()
            jobUndispatched.join()

            println("Main ends: ${Thread.currentThread().name}")
        }

    // Custom coroutine context elements
    data class UserId(val value: String) : CoroutineContext.Element {
        companion object Key : CoroutineContext.Key<UserId>

        override val key: CoroutineContext.Key<*>
            get() = Key
    }

    data class SessionId(val value: String) : CoroutineContext.Element {
        companion object Key : CoroutineContext.Key<SessionId>

        override val key: CoroutineContext.Key<*>
            get() = Key
    }

    // Function to access custom context elements
    private fun printCoroutineContext(context: CoroutineContext) {
        val userId = context[UserId]
        val sessionId = context[SessionId]
        val job = context[Job]

        println("User ID: ${userId?.value ?: "Unknown"}")
        println("Session ID: ${sessionId?.value ?: "Unknown"}")
        println("Job: ${job?.isActive ?: "Inactive"}")
    }

    private fun customValuesCoroutineContext() =
        runBlocking {
            // Launch a coroutine with custom context values
            launch(UserId("1") + SessionId("abc")) {
                // Now print the custom context values
                printCoroutineContext(coroutineContext)

                launch {
                    println("In Child Coroutine:")
                    printCoroutineContext(coroutineContext) // This will show the child's inherited context
                }
            }
        }

    private fun structuredConcurrency() =
        runBlocking {
            LOGGER.info("\nStructured Concurrency")
            val parentJob = Job() // Creates a parent Job, which will manage the lifetime of child coroutines
            val name = CoroutineName("Parent Coroutine") // Assigns a name to the parent coroutine

            // Launch a new coroutine with a combined context (name + parentJob)
            // Launches a new coroutine within the runBlocking scope.
            // The coroutine is given a context that includes both the CoroutineName ("Parent Coroutine") and the Job (parentJob),
            // meaning this coroutine will inherit both the name and job from the parent.
            launch(name + parentJob) {
                val childName = coroutineContext[CoroutineName] // Get the name of the current coroutine
                LOGGER.info("{} {} {}", childName, name, childName == name) // true
                val childJob = coroutineContext[Job] // Get the Job associated with this coroutine
                LOGGER.info("{} {} {}", childJob, parentJob, childJob == parentJob) // false
                LOGGER.info("{} {} {}", childJob, parentJob.children, childJob == parentJob.children.first()) // true
            }
            // This ensures that the main runBlocking coroutine will wait for all child coroutines (managed by parentJob) to complete.
            // The join() function waits for each child coroutine to finish execution.
            parentJob.children.forEach { it.join() }
        }

    private fun coroutineContextDemo() =
        runBlocking {
            LOGGER.info("\nCoroutine Context Demo")
            // This line starts a new coroutine with a specific dispatcher and a custom coroutine name.
            launch(Dispatchers.Default + CoroutineName("CustomContext")) {
                LOGGER.info("Running in thread: ${Thread.currentThread().name}")
            }

            // Sequence builder
            //  A sequence is a lazy collection that allows you to build and process elements one by one,
            //  avoiding the overhead of creating a collection upfront.
            val childNumbers =
                sequence {
                    yield(1)
                    print("AAA")
                    yieldAll(listOf(2, 3))
                }
            childNumbers.forEach { print(it) } // 1AAA23
            val nums = childNumbers.joinToString() // AAA
            print(nums) // 1, 2, 3

            val fibonacci: Sequence<BigInteger> =
                sequence {
                    var first = 0.toBigInteger()
                    var second: BigInteger = 1.toBigInteger()
                    while (true) {
                        yield(first)
                        val temp = first
                        first += second
                        second = temp
                    }
                }

            print(fibonacci.take(10).toList())
            // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]

            fun randomNumbers(seed: Long = System.currentTimeMillis()): Sequence<Int> =
                sequence {
                    val random = Random(seed)
                    while (true) {
                        yield(random.nextInt())
                    }
                }

            randomNumbers(1).iterator().next()

            fun randomUniqueStrings(
                length: Int,
                seed: Long = System.currentTimeMillis()
            ): Sequence<String> =
                sequence {
                    val random = Random(seed)

                    val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
                    while (true) {
                        val randomString =
                            (1..length)
                                .map { i -> random.nextInt(charPool.size) }
                                .map(charPool::get)
                                .joinToString("")
                        yield(randomString)
                    }
                }.distinct()

            randomUniqueStrings(5).iterator().next()
        }

    private fun exceptionHandling() =
        runBlocking {
            LOGGER.info("\nException Handling in Coroutines")

            val handler =
                CoroutineExceptionHandler { _, exception ->
                    LOGGER.error("Caught Exception: ${exception.localizedMessage}")
                }

            // launch propagates exceptions to its parent by default
            // supervisorScope ensures that other coroutines inside it continue running even if one fails.
            supervisorScope { // Ensures the failure of one coroutine doesn't cancel others
                val job =
                    launch(handler) {
                        throw RuntimeException("Coroutine Exception Example")
                    }
                job.join() // Ensures it waits for completion
            }

            try {
                // coroutineScope: If any child coroutine fails, all coroutines inside the scope will be canceled,
                // and the exception will propagate.
                //
                // supervisorScope: It works similarly, but child coroutines are isolated.
                // If one fails, the other coroutines will continue running, and the exception is not propagated.
                coroutineScope {
                    val job1 =
                        launch {
                            delay(1000L)
                            println("Job 1 completed")
                        }

                    val job2 =
                        launch {
                            delay(500L)
                            throw RuntimeException("Job 2 failed")
                        }

                    // `coroutineScope` will fail as soon as any coroutine fails
                    job1.join() // Might not reach this if job2 throws
                    job2.join() // This will throw and the scope will be cancelled
                    println("This will not be printed if any child throws an exception")
                }
            } catch (e: Exception) {
                println("Caught exception: ${e.message}")
            }

            supervisorScope {
                val job1 =
                    launch {
                        delay(1000L)
                        println("Job 1 completed")
                    }

                val job2 =
                    launch {
                        delay(500L)
                        throw RuntimeException("Job 2 failed")
                    }

                // supervisorScope allows job1 to continue even if job2 fails
                job1.join()
                job2.join()
                println("This will be printed even if job2 fails")
            }

            runBlocking {
                try {
                    // Launch a coroutine that simulates a long-running task
                    withTimeout(2000L) { // Timeout in 2 seconds
                        println("Starting long-running task...")
                        delay(3000L) // Simulate a task that takes longer than the timeout
                        println("Task completed") // This will never be printed
                    }
                } catch (e: TimeoutCancellationException) {
                    println("Task timed out!")
                }
            }
        }

    /**
     * Coroutines also allow for cooperative suspension.
     * A coroutine can suspend and let other coroutines run, and it can resume at a later time when its conditions are met.
     */
    private suspend fun cooperativeSuspension() =
        runBlocking {
            launch {
                println("Task 1 started")
                delay(2000L)
                println("Task 1 resumed after delay")
            }

            launch {
                println("Task 2 started")
                delay(1000L)
                println("Task 2 resumed after delay")
            }
        }

    private val executor =
        Executors.newSingleThreadScheduledExecutor {
            Thread(it, "scheduler").apply { isDaemon = true }
        }

    /**
     * You can also manually suspend and resume a coroutine using the suspendCoroutine function.
     * This allows more control over suspension and resumption,
     * which can be useful when working with callbacks or event-based systems.
     */
    private suspend fun manualSuspensionAndResumption(): String =
        suspendCoroutine { continuation ->
            println("Suspending...")
            // Simulating some operation that will resume the coroutine later
            GlobalScope.launch {
                delay(2000L) // Simulate some async work (e.g., a network call)
                continuation.resume("Operation completed") // Resume the coroutine
            }
        }

    private suspend fun manualSuspensionAndResumption2(): Result<String> =
        suspendCoroutine { continuation ->
            executor.schedule({
                continuation.resume(Result.success("Resumed"))
            }, 1000, TimeUnit.MILLISECONDS)
        }

    private suspend fun fetchDataWithError(): String =
        suspendCoroutine { cont ->
            println("Simulating API call...")

            // Simulating async failure (e.g., API error)
            GlobalScope.launch {
                delay(1000)
                cont.resumeWithException(IllegalStateException("Something went wrong!"))
            }
        }

    private suspend fun suspendCoroutine(scope: CoroutineScope) {
        val job =
            scope.launch {
                try {
                    val result = cancellableWork()
                    println("Result: $result")
                } catch (e: CancellationException) {
                    println("Caught cancellation: ${e.message}")
                }
            }

        scope.launch {
            delay(1000) // Let it start, then cancel early
            println("Cancelling coroutine...")
            job.cancelAndJoin()
        }

        runBlocking {
            val job =
                launch {
                    repeat(10) { i ->
                        println("Coroutine is working $i...")
                        delay(500L) // Simulate some work
                    }
                }

            delay(1000L) // Let it run for a while
            println("Cancelling coroutine...")
            job.cancel() // Cancel the coroutine
            job.invokeOnCompletion { println("Finished ....") }

            // This will print the cancellation message
            println("Coroutine cancelled")
        }

        coroutineScope {
            val job = Job() // Create a new Job
            launch(job) { // Launch a coroutine with the job
                repeat(50) { i ->
                    Thread.sleep(200) // This is a blocking call
                    // Complex operations or file I/O can be here
                    println("Printing $$i") // Printing
                }
            }
            delay(100) // Wait for 0.1 seconds
            job.cancelAndJoin() // Cancel the job and wait for it to finish
            println("Cancelled successfully") // Print that cancellation is complete
            delay(200) // Wait a little after cancellation
        }
    }

    private suspend fun cancellableWork(): String =
        suspendCancellableCoroutine { cont ->
            println("Starting cancellable work...")

            val job =
                GlobalScope.launch {
                    delay(3000) // Simulate long-running work
                    if (cont.isActive) {
                        cont.resume("Done!")
                    }
                }

            // If the coroutine gets cancelled, cancel the launched job too
            cont.invokeOnCancellation {
                println("Coroutine was cancelled. Cleaning up...")
                job.cancel()
            }
        }

    // Flow is a cold asynchronous data stream in Kotlin Coroutines,
    // designed to handle multiple values over time.
    // It provides reactive-style programming with backpressure support.
    //
    // Key Features of Flow
    // - Cold Stream → Flow only runs when it is collected.
    // - Suspending Functions → Flow builders (flow {}) run in a coroutine.
    // - Sequential Execution → It emits values one by one, unlike List.
    // - Backpressure Handling → Supports slow collectors.
    // - Operators → Can transform data (map, filter, flatMapConcat).
    private fun coroutineFlows() =
        runBlocking {
            LOGGER.info("\nWorking with Flows")

            // Example of a StateFlow to track a value
            val mutableStateFlow = MutableStateFlow(0) // Initial state is 0
            val stateFlow: StateFlow<Int> = mutableStateFlow // Exposed as StateFlow

            // Launch a coroutine to collect from the StateFlow
            val stateFlowJob =
                launch {
                    // Collecting from StateFlow and logging the values
                    stateFlow.collect { value ->
                        LOGGER.info("StateFlow received: $value")
                    }
                }

            // Launch another coroutine to simulate fetching numbers in a Flow
            val fetchNumbersJob =
                launch {
                    fetchNumbers()
                        .onEach { LOGGER.info("fetchNumbers emitted: $it") }
                        .filter { it % 2 != 0 } // only odd numbers
                        .map { it * 10 }
                        .collect { value ->
                            LOGGER.info("Received from fetchNumbers Flow: $value")

                            // Simulate updating the state in StateFlow
                            mutableStateFlow.value = value // Update the state based on the Flow data
                        }
                }

            // Wait for both jobs to finish
            // Fetching numbers and collecting from state flow need to be done before exiting the runBlocking scope.
            fetchNumbersJob.join() // Wait for the flow to finish emitting
            stateFlowJob.cancelAndJoin() // Cancel the state flow collection if done (because it's infinite)

            fetchNumbers()
                .onCompletion { cause ->
                    if (cause != null) {
                        println("Flow completed with error: ${cause.message}")
                    } else {
                        println("Flow completed successfully.")
                    }
                }
                .catch { e -> println("Caught: ${e.message}") }
                .collect { println("Collected: $it") }
        }

    // Simulating fetchNumbers function (can be replaced with your actual function)
    private fun fetchNumbers(): Flow<Int> =
        flow {
            for (i in 1..5) {
                delay(100) // Simulate some delay
                emit(i) // Emit numbers 1 to 5
            }
        }

    private suspend fun doComputation(value: String): Int {
        delay(1000L)
        LOGGER.info("Suspended computation ($value)...")
        return 42
    }

    // Channels in Kotlin Coroutines provide a way to transfer values between coroutines safely and asynchronously.
    // They work like concurrent queues but are designed specifically for coroutines,
    // supporting suspending send and receive operations.
    //
    //  Why Use Channels?
    // - Avoid Shared Mutable State → Channels allow safe communication between coroutines.
    // - Producer-Consumer Pattern → Efficient data processing between sender and receiver.
    // - Backpressure Support → The sender suspends when the channel is full, preventing memory issues.
    private fun channels() {
        val channel = Channel<Int>()

        GlobalScope.launch {
            for (i in 1..5) {
                channel.send(i) // Send values
                println("Sent: $i")
            }
            channel.close() // Close the channel when done
        }

        GlobalScope.launch {
            for (value in channel) { // Receive values
                println("Received: $value")
            }
        }

        // You might forgot to close the channel so use a builder
        runBlocking {
            // Create a producer coroutine using produce
            val channel =
                produce {
                    for (i in 1..5) {
                        delay(100) // Simulate some work
                        send(i) // Send the integer i to the channel
                    }
                }

            // Consumer coroutine that receives values from the channel
            launch {
                for (value in channel) {
                    println("Received: $value") // Print the received values
                }
            }

            // Wait for the consumer to finish
            delay(1000)
        }
    }

    sealed class CounterMessage

    class Increment(val amount: Int) : CounterMessage()

    class GetCounter(val replyTo: CompletableDeferred<Int>) : CounterMessage()

    private fun channelTypes() =
        runBlocking {
            // 1. Basic Channel
            val basicChannel = Channel<Int>()

            launch {
                for (i in 1..5) {
                    delay(100)
                    basicChannel.send(i)
                }
                basicChannel.close()
            }

            launch {
                for (value in basicChannel) {
                    println("Basic Channel received: $value")
                }
            }

            delay(1000)
            basicChannel.close()

            // 2. Broadcast Channel
            val broadcastChannel = BroadcastChannel<Int>(Channel.BUFFERED)

            launch {
                for (i in 1..3) {
                    delay(100)
                    broadcastChannel.send(i)
                }
                broadcastChannel.close()
            }

            launch {
                for (value in broadcastChannel.openSubscription()) {
                    println("Broadcast Channel Consumer 1 received: $value")
                }
            }

            launch {
                for (value in broadcastChannel.openSubscription()) {
                    println("Broadcast Channel Consumer 2 received: $value")
                }
            }

            // 3. ConflatedChannel alternative (StateFlow)
            val stateFlow = MutableStateFlow(0)

            launch {
                for (i in 1..5) {
                    delay(100)
                    stateFlow.value = i // Only the latest value is retained
                }
            }

            launch {
                stateFlow.take(5).collect { value ->
                    println("StateFlow received: $value")
                }
            }

            delay(1000)

            // 4. Actor
            fun CoroutineScope.counterActor() =
                actor<CounterMessage> {
                    var counter = 0
                    for (msg in channel) {
                        when (msg) {
                            is Increment -> counter += msg.amount
                            is GetCounter -> msg.replyTo.complete(counter)
                        }
                    }
                }

            val counter = counterActor()

            counter.send(Increment(10))
            counter.send(Increment(5))

            val response = CompletableDeferred<Int>()
            counter.send(GetCounter(response))
            println("Actor Counter value: ${response.await()}")

            counter.close()
        }

    private suspend fun bufferOverflow(bufferOverflowStrategy: BufferOverflow) {
        println("Testing with strategy: $bufferOverflowStrategy")
        // Create a channel with a buffer size of 3, and the provided overflow strategy
        val channel = Channel<Int>(3, onBufferOverflow = bufferOverflowStrategy)

        // Launch a coroutine to send data into the channel
        GlobalScope.launch {
            for (i in 1..5) {
                println("Sending: $i")
                channel.send(i) // Will behave based on the selected overflow strategy
                println("Sent: $i")
            }
            channel.close() // Close the channel once done
        }

        // Launch another coroutine to receive data from the channel
        GlobalScope.launch {
            for (i in channel) {
                delay(1000) // Simulate work done while processing each element
                println("Received: $i")
            }
        }

        // Allow some time for the above operations to complete before moving to the next test
        delay(5000)
    }

    private suspend fun channelBufferOverflowStrategies() {
        // Call the bufferOverflow function with different BufferOverflow strategies
        bufferOverflow(BufferOverflow.DROP_OLDEST)
        bufferOverflow(BufferOverflow.DROP_LATEST)
        bufferOverflow(BufferOverflow.SUSPEND)
    }

    // Shared state
    var sharedCounter = 0

    // Mutex to ensure safe access to the shared state
    val mutex = Mutex()

    private suspend fun incrementCounter() {
        // Lock the mutex to ensure that only one coroutine can access the shared state at a time
        mutex.withLock {
            // Critical section: safe access to shared state
            val current = sharedCounter
            println("Current counter value: $current")
            delay(100) // Simulating some work
            sharedCounter = current + 1
            println("Counter incremented to: $sharedCounter")
        }
    }

    val sharedCounter2 = AtomicInteger(0)

    private suspend fun incrementCounter2() {
        // Atomically increment the counter
        val current = sharedCounter2.getAndIncrement()
        println("Current counter value: $current")
        delay(100) // Simulating some work
        println("Counter incremented to: ${sharedCounter2.get()}")
    }

    private suspend fun synchronization() {
        // Launching multiple coroutines to increment the shared counter
        val jobs =
            List(10) {
                GlobalScope.launch {
                    incrementCounter()
                }
            }

        // Wait for all coroutines to finish
        jobs.joinAll()

        // Final counter value
        println("Final counter value: $sharedCounter")

        // Alternative: Using AtomicInteger
        // Launching multiple coroutines to increment the shared counter
        val jobs2 =
            List(10) {
                GlobalScope.launch {
                    incrementCounter()
                }
            }

        // Wait for all coroutines to finish
        jobs2.joinAll()

        // Final counter value
        println("Final counter value: ${sharedCounter2.get()}")
    }
}
