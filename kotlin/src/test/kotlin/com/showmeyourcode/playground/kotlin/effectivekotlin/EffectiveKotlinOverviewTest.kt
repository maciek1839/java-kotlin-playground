package com.showmeyourcode.playground.kotlin.effectivekotlin

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.AnnotationSpec

class EffectiveKotlinOverviewTest : AnnotationSpec() {
    @Test
    fun `should call the main method without errors`() {
        shouldNotThrow<Throwable> {
            EffectiveKotlinOverview.main(arrayOf())
        }
    }
}
