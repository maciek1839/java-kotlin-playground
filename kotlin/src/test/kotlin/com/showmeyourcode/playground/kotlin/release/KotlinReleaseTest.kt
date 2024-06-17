package com.showmeyourcode.playground.kotlin.release

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.AnnotationSpec

class KotlinReleaseTest : AnnotationSpec() {
    @Test
    fun `should call the KotlinRelease without errors`() {
        shouldNotThrow<Throwable> {
            main(arrayOf())
        }
    }
}
