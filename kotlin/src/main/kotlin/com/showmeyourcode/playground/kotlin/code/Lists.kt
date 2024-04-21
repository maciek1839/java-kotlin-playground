package com.showmeyourcode.playground.kotlin.code

import com.showmeyourcode.playground.kotlin.common.Logging

object Lists {
    fun main() {
        Logging.LOGGER.info("\n====> Lists")
        // mixing types are not recommended
        val mixedTypes: List<Any> =
            mutableListOf(
                "A",
                1,
                2,
                'f'
            )
    }
}
