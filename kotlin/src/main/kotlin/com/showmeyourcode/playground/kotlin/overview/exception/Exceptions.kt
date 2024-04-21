package com.showmeyourcode.playground.kotlin.overview.exception

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object Exceptions {
    fun main() {
        Logging.LOGGER.info("\n${Descriptions.EXCEPTION_HEADER}")
        Logging.LOGGER.info("${Descriptions.EXCEPTION}\n")

        Logging.LOGGER.info("Kotlin does not have checked exceptions.")
        Logging.LOGGER.info("The throw expression has the type Nothing.")
        Logging.LOGGER.info("Error = java.lang.Error")
        Logging.LOGGER.info("Exception = java.lang.Exception")
        // https://kotlinlang.org/docs/exceptions.html#the-nothing-type
    }
}
