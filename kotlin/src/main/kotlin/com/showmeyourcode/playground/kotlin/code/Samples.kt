package com.showmeyourcode.playground.kotlin.code

import com.showmeyourcode.playground.kotlin.code.paradigm.functional.FunctionalProgramming
import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    Logging.LOGGER.info("\n\n${Descriptions.headerTemplate(Descriptions.CODE_SAMPLES)}")
    Classes.main()
    Ranges.main()
    Arrays.main()
    Lists.main()
    Coroutines.main()
    Logging.LOGGER.info(Descriptions.headerTemplate(Descriptions.PROGRAMMING_PARADIGMS))
    Logging.LOGGER.info(Descriptions.PROGRAMMING_PARADIGM)
    FunctionalProgramming.main()
}
