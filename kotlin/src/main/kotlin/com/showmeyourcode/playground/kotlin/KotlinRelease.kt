package com.showmeyourcode.playground.kotlin

import com.showmeyourcode.playground.kotlin.common.Logging
import com.showmeyourcode.playground.kotlin.release.Kotlin190

object KotlinRelease {
    @Suppress("UNUSED_PARAMETER")
    fun main(args: Array<String>) {
        Logging.LOGGER.info("\nStarting Kotlin Release Application...\n")

        Kotlin190.main()
    }
}
