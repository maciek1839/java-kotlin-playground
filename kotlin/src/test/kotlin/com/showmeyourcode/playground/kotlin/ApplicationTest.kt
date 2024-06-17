package com.showmeyourcode.playground.kotlin

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.AnnotationSpec

class ApplicationTest : AnnotationSpec() {
    @Test
    fun `should call the main function without errors`() {
        shouldNotThrow<Throwable> {
            main(arrayOf())
        }
    }
}
