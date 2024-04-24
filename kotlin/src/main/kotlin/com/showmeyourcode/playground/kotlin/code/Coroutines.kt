package com.showmeyourcode.playground.kotlin.code

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Coroutines {
    val LOGGER: Logger = LoggerFactory.getLogger(Coroutines::class.java)

    fun main() {
        // https://kotlinlang.org/docs/whatsnew13.html#coroutines-release
        // https://kotlinlang.org/docs/coroutines-overview.html
        // https://kotlinlang.org/spec/asynchronous-programming-with-coroutines.html
        // https://silica.io/understanding-kotlin-coroutines/
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

        GlobalScope.launch {
            delay(1000L)
            LOGGER.info("Coroutines global scope...")
        }

        // launch is used to fire and forget coroutine.
        // It's perfect for cases where you don't need to compute any result.
        GlobalScope.launch {
            // async is used when you need a result computed in a coroutine.
            // It starts a new coroutine and returns a Deferred<T>,
            // which is a non-blocking future that represents a promise to provide a result later.
            val result =
                async {
                    doComputation("GlobalScope.async")
                }
            // note that the log statement won't be printed as program finishes and it won't wait for all
            // started coroutines with GlobalScope.
            delay(5000L)
            LOGGER.info("Computed result: ${result.await()}")
        }

        // runBlocking is a bridge between non-coroutine world and coroutine world.
        // It's a way to start top-level main coroutine.
        runBlocking {
            launch { doComputation("runBlocking") }
        }

        structuredConcurrency()
    }

    private fun structuredConcurrency() {
        runBlocking {
            LOGGER.info("Structured Concurrency")
            val name = CoroutineName("Parent name")
            val parentJob = Job()
            launch(name + parentJob) {
                val childName = coroutineContext[CoroutineName]
                LOGGER.info("{} {} {}", childName, name, childName == name) // true
                val childJob = coroutineContext[Job]
                LOGGER.info("{} {} {}", childJob, parentJob, childJob == parentJob) // false
                LOGGER.info("{} {} {}", childJob, parentJob.children, childJob == parentJob.children.first()) // true
            }
            parentJob.children.forEach { it.join() }
        }
    }

    // this is your first suspending function
    private suspend fun doComputation(value: String) {
        delay(1000L)
        LOGGER.info("Suspended computation ($value)...")
    }
}
