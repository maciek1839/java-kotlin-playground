package com.showmeyourcode.playground.kotlin.code

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.AnnotationSpec

class SamplesTest : AnnotationSpec() {
    @Test
    fun `should call the main function without errors`() {
        shouldNotThrow<Throwable> {
            main(arrayOf())
        }
    }
}
