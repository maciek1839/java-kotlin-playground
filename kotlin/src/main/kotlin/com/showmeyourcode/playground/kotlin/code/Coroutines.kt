package com.showmeyourcode.playground.kotlin.code

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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
                It is conceptually similar to a thread,
                in the sense that it takes a block of code to run that works concurrently with the rest of the code.
                However, a coroutine is not bound to any particular thread.
                It may suspend its execution in one thread and resume in another one.
                
                In Kotlin, coroutines are used to implement suspending functions
                and can switch contexts only at suspension points.
                A call to a suspending function creates and starts a coroutine.
                """.trimIndent()
            )

            basicCoroutines()
            structuredConcurrency()
            coroutineContextDemo()
            exceptionHandling()
            coroutineFlows()
            channels()
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

    private fun structuredConcurrency() =
        runBlocking {
            LOGGER.info("\nStructured Concurrency")
            val parentJob = Job()
            val name = CoroutineName("Parent Coroutine")

            launch(name + parentJob) {
                val childName = coroutineContext[CoroutineName]
                LOGGER.info("{} {} {}", childName, name, childName == name) // true
                val childJob = coroutineContext[Job]
                LOGGER.info("{} {} {}", childJob, parentJob, childJob == parentJob) // false
                LOGGER.info("{} {} {}", childJob, parentJob.children, childJob == parentJob.children.first()) // true
            }
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
            val childNumbers =
                sequence {
                    yield(1)
                    print("AAA")
                    yieldAll(listOf(2, 3))
                }
            childNumbers.forEach { print(it) } // 1AAA23
            val nums = childNumbers.joinToString() // AAA
            print(nums) // 1, 2, 3
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
                    fetchNumbers().collect { value ->
                        LOGGER.info("Received from fetchNumbers Flow: $value")

                        // Simulate updating the state in StateFlow
                        mutableStateFlow.value = value // Update the state based on the Flow data
                    }
                }

            // Wait for both jobs to finish
            // Fetching numbers and collecting from state flow need to be done before exiting the runBlocking scope.
            fetchNumbersJob.join() // Wait for the flow to finish emitting
            stateFlowJob.cancelAndJoin() // Cancel the state flow collection if done (because it's infinite)
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
    }
}
