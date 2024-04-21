package com.showmeyourcode.playground.kotlin

import com.showmeyourcode.playground.kotlin.code.Samples
import com.showmeyourcode.playground.kotlin.common.Logging
import com.showmeyourcode.playground.kotlin.overview.Equality
import com.showmeyourcode.playground.kotlin.overview.LanguageFeatures
import com.showmeyourcode.playground.kotlin.overview.datatype.Datatypes
import com.showmeyourcode.playground.kotlin.overview.exception.Exceptions
import com.showmeyourcode.playground.kotlin.overview.keyword.Keywords

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    Logging.LOGGER.info("\nStarting Kotlin Playground Application...\n")

    LanguageFeatures.main()

    Keywords.main()

    Datatypes.main()

    Exceptions.main()

    Equality.main()

    // code samples
    Samples.main()
}
