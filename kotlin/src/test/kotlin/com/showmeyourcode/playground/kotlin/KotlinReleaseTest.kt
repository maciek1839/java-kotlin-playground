package com.showmeyourcode.playground.kotlin

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.AnnotationSpec

class KotlinReleaseTest : AnnotationSpec() {
    @Test
    fun `should call the KotlinRelease without errors`() {
        shouldNotThrow<Throwable> {
            KotlinRelease.main(arrayOf())
        }
    }
}
