package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object Kotlin190 {
    fun main() {
        Logging.LOGGER.info("\n{} 1.9.0", Descriptions.INDENT1)
        Logging.LOGGER.info("{} Stable ..< operator for open-ended ranges", Descriptions.INDENT2)
        for (number in 2..<5) {
            if (number % 2 == 0) {
                Logging.LOGGER.info("{}", number)
            }
        }
    }
}
