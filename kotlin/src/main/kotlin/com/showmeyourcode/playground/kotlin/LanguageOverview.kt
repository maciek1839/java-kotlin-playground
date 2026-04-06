package com.showmeyourcode.playground.kotlin

import com.showmeyourcode.playground.kotlin.common.Logging
import com.showmeyourcode.playground.kotlin.overview.Equality
import com.showmeyourcode.playground.kotlin.overview.LanguageFeatures
import com.showmeyourcode.playground.kotlin.overview.datatype.Datatypes
import com.showmeyourcode.playground.kotlin.overview.exception.Exceptions
import com.showmeyourcode.playground.kotlin.overview.keyword.Keywords
import com.showmeyourcode.playground.kotlin.release.KotlinReleases

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    Logging.LOGGER.info("\nStarting Kotlin Playground Application...\n")

    LanguageFeatures.main()

    Keywords.main()

    Datatypes.main()

    Exceptions.main()

    Equality.main()

    KotlinReleases.main()
}
