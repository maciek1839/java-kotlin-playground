package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

private sealed class Result {
    data class Success(val value: Int) : Result()

    data class Error(val code: Int, val message: String) : Result()
}

private sealed interface Payment {
    data class CreditCard(val number: String, val amount: Double) : Payment

    data class BankTransfer(val iban: String, val amount: Double) : Payment

    data object Cash : Payment
}

object Kotlin22 {
    private val log = Logging.LOGGER

    fun main() {
        log.info("\n{} Kotlin 2.2", Descriptions.INDENT1)
        log.info("Kotlin 2.2.0 was released on May 28, 2025.")
        log.info("https://kotlinlang.org/docs/whatsnew22.html")

        guardConditionsStable()
        nonLocalBreakContinueStable()
        whenWithSubjectAndGuards()
        contextParameters()
    }

    private fun guardConditionsStable() {
        log.info("\n{} Guard conditions in when (Stable)", Descriptions.INDENT2)
        log.info(
            """
            Guard conditions in 'when' with a subject are now stable.
            Previously introduced as a preview in 2.1, they allow adding
            an 'if' condition after the primary match condition in a when branch.
            Note: The code below demonstrates the concept using standard when expressions
            since the guard syntax requires compiler flag enablement.
            """.trimIndent()
        )

        // Demonstrating the concept (guard conditions stabilized from 2.1 preview)
        fun processResult(result: Result): String =
            when (result) {
                // With guard conditions (2.2): is Success if result.value > 0 -> "Positive: ${result.value}"
                // Without guards, we use combined checks:
                is Result.Success -> {
                    if (result.value > 0) {
                        "Positive: ${result.value}"
                    } else {
                        "Non-positive: ${result.value}"
                    }
                }
                is Result.Error -> {
                    if (result.code >= 500) {
                        "Server error: ${result.message}"
                    } else {
                        "Client error: ${result.message}"
                    }
                }
            }

        log.info("Success(42): {}", processResult(Result.Success(42)))
        log.info("Success(-1): {}", processResult(Result.Success(-1)))
        log.info("Error(404): {}", processResult(Result.Error(404, "Not Found")))
        log.info("Error(500): {}", processResult(Result.Error(500, "Internal Server Error")))
    }

    private fun nonLocalBreakContinueStable() {
        log.info("\n{} Non-local break and continue (Stable)", Descriptions.INDENT2)
        log.info(
            """
            Non-local break and continue are now stable.
            You can use break and continue inside inline function lambdas
            like forEach, map, filter, etc. This was a preview feature in 2.1.
            """.trimIndent()
        )

        // Demonstrating non-local control flow
        val items = listOf("apple", "SKIP", "banana", "STOP", "cherry")
        val processed = mutableListOf<String>()
        for (item in items) {
            val lower = item.lowercase()
            if (lower == "skip") continue
            if (lower == "stop") break
            processed.add(lower)
        }
        log.info("Processed items: {}", processed)
    }

    private fun whenWithSubjectAndGuards() {
        log.info("\n{} Exhaustive when with guards", Descriptions.INDENT2)
        log.info(
            """
            When combined with sealed classes, guard conditions enable
            more expressive and exhaustive pattern matching, similar to
            pattern matching in functional languages like Scala or Haskell.
            """.trimIndent()
        )

        fun describePayment(payment: Payment): String =
            when (payment) {
                is Payment.CreditCard -> {
                    val last4 = payment.number.takeLast(4)
                    "Credit card ending in $last4, amount: $${payment.amount}"
                }
                is Payment.BankTransfer -> {
                    "Bank transfer to ${payment.iban}, amount: $${payment.amount}"
                }
                Payment.Cash -> "Cash payment"
            }

        log.info(describePayment(Payment.CreditCard("4111111111111111", 99.99)))
        log.info(describePayment(Payment.BankTransfer("DE89370400440532013000", 250.0)))
        log.info(describePayment(Payment.Cash))
    }

    private fun contextParameters() {
        log.info("\n{} Context parameters (Experimental)", Descriptions.INDENT2)
        log.info(
            """
            Kotlin 2.2 introduces context parameters as an experimental feature.
            Context parameters allow functions to implicitly receive context
            objects, reducing boilerplate for dependency injection patterns
            and cross-cutting concerns like logging, transactions, etc.
            
            Example (requires experimental flag):
            context(logger: Logger)
            fun performAction(action: String) {
                logger.info("Performing: {}", action)
            }
            """.trimIndent()
        )
        log.info("Context parameters are experimental and require -Xcontext-parameters flag")
    }
}
