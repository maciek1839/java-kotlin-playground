package com.showmeyourcode.playground.kotlin.code.exercise.misc

import com.showmeyourcode.playground.kotlin.common.Logging

/**
 * The same exercise is available in Java. See the Java version for the instruction.
 */
class TuringMachineExercise(input: String) {
    companion object {
        private const val ZERO = '0'
        private const val ONE = '1'

        private enum class MachineState {
            STATE_0,
            STATE_1,
            STATE_STOP
        }
    }

    private val tape: CharArray = input.toCharArray()
    private var head: Int = input.length - 1
    private var state: MachineState = MachineState.STATE_0

    fun run() {
        while (state != MachineState.STATE_STOP) {
            val currentChar = tape[head]
            when (state) {
                MachineState.STATE_0 -> {
                    if (currentChar == ONE) {
                        tape[head] = ZERO
                        state = MachineState.STATE_STOP
                    } else {
                        tape[head] = ONE
                        head--
                        state = MachineState.STATE_1
                    }
                }
                MachineState.STATE_1 -> {
                    if (currentChar == ZERO) {
                        tape[head] = ONE
                        --head
                    } else {
                        tape[head] = ZERO
                        state = MachineState.STATE_STOP
                    }
                }
                else -> Logging.LOGGER.debug("Ignoring state: {}", state)
            }
        }
    }

    fun getTape(): String = tape.concatToString().trim()

    fun printTape() {
        Logging.LOGGER.info("{}", tape.contentToString())
    }
}
