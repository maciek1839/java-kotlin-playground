package com.showmeyourcode.playground.kotlin

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.nulls.shouldNotBeNull

class ApplicationTest : AnnotationSpec() {
    @Test
    fun `should initialize class instance`() {
        val app = Application()
        app.shouldNotBeNull()
    }
}
