package com.showmeyourcode.playground.kotlin.code

import com.showmeyourcode.playground.kotlin.common.Logging

object Arrays {
    fun main() {
        Logging.LOGGER.info("\n====> Arrays")
        // mixing types are not recommended
        val mixedTypes: Array<Any> =
            arrayOf(
                "A",
                1,
                2,
                'f'
            )
    }
}
