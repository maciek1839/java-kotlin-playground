package com.showmeyourcode.playground.kotlin.overview.keyword

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object Keywords {
    fun main() {
        Logging.LOGGER.info("\n${Descriptions.headerTemplate("KEYWORDS")}")
        Logging.LOGGER.info("Keywords are predefined or reserved words that have special meanings to the compiler.\n")

        Logging.LOGGER.info(
            """The main difference between val and const val is that val can be assigned a value at runtime, 
                |whereas const val must be assigned a value at compile time and cannot be changed afterward 
                |(their values are hardcoded).
            """.trimMargin()
        )
    }
}
