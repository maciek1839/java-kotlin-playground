package com.showmeyourcode.playground.kotlin.code

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object Samples {
    fun main() {
        Logging.LOGGER.info("\n\n${Descriptions.headerTemplate(Descriptions.CODE_SAMPLES)}")
        Classes.main()
        Ranges.main()
        Arrays.main()
        Lists.main()
    }
}
