package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object KotlinReleases {
    private val log = Logging.LOGGER

    fun main() {
        log.info(Descriptions.header("Kotlin Releases"))
        log.info(
            """
            Kotlin follows a regular release cadence.
            Major features are introduced in feature releases (e.g. 1.5, 1.6, 2.0).
            Incremental releases (e.g. 1.5.30) bring tooling updates and bugfixes.

            https://kotlinlang.org/docs/releases.html#release-details
            """.trimIndent()
        )
        Kotlin22.main()
        Kotlin21.main()
        Kotlin20.main()
        Kotlin19.main()
        Kotlin18.main()
        Kotlin17.main()
        Kotlin16.main()
        Kotlin15.main()
        Kotlin14.main()
    }
}
