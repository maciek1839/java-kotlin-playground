package com.showmeyourcode.playground.kotlin.code.exercise.misc

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class TuringMachineExerciseTest : AnnotationSpec() {
    @Test
    fun `should print the tape without errors`() {
        shouldNotThrowAny {
            TuringMachineExercise("100").printTape()
        }
    }

    @Test
    fun `should find the predecessor`() {
        forAll(
            row("1", "0"),
            row("10", "01"),
            row("100", "011"),
            row("1100", "1011"),
            row("1110", "1101")
        ) { input, predecessor ->
            val machine = TuringMachineExercise(input)

            machine.run()

            machine.getTape() shouldBe predecessor
        }
    }
}
