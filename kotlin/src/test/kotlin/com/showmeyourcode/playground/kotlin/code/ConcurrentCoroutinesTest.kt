package com.showmeyourcode.playground.kotlin.code

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ConcurrentCoroutinesTest {
    @Test
    fun testWithDelay() =
        runTest {
            // Launch a coroutine that uses delay
            val result =
                async {
                    delay(500L)
                    "Delayed result"
                }

            // Simulate 500ms of time passing
            advanceTimeBy(500L)

            // Assert that the coroutine completed after the delay
            assertEquals("Delayed result", result.await())
        }

    @Test
    fun testTimeout() =
        runTest {
            // Test a coroutine with timeout
            val result =
                withTimeoutOrNull(1000L) {
                    delay(500L)
                    "Completed"
                }

            // Assert that it completed successfully within the timeout
            assertEquals("Completed", result)
        }

    @Test
    fun testTimeoutExceeded() =
        runTest {
            // Test that a timeout exception is thrown
            val result =
                withTimeoutOrNull(200L) {
                    delay(500L)
                    "This should not complete"
                }

            // Assert that the result is null due to timeout
            assertEquals(null, result)
        }

    @Test
    fun testConcurrentCoroutines() =
        runTest {
            // Launch multiple coroutines
            val job1 =
                launch {
                    delay(1000L)
                    println("Job 1 completed")
                }
            val job2 =
                launch {
                    delay(500L)
                    println("Job 2 completed")
                }

            // Fast forward time by 1000ms, which will cover both jobs
            advanceTimeBy(1000L)

            // Assert both jobs completed
            job1.join()
            job2.join()

            assertTrue { job1.isCompleted }
            assertTrue { job2.isCompleted }
        }
}
