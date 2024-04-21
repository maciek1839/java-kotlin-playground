@file:Suppress("ktlint:standard:max-line-length")

package com.showmeyourcode.playground.kotlin.common

object Descriptions {
    // necessary for Java
    @JvmStatic
    fun header(title: String): String {
        return headerTemplate(title)
    }
    // necessary for Java

    val headerTemplate: (String) -> String = { value: String -> "========== $value ==========" }
    const val DATA_TYPE_TITLE = "DATA TYPES"
    val DATA_TYPE_HEADER = headerTemplate(DATA_TYPE_TITLE)
    const val EXCEPTION_TITLE = "EXCEPTIONS"
    val EXCEPTION_HEADER = headerTemplate(EXCEPTION_TITLE)
    const val EQUALITY = "EQUALITY"
    val EQUALITY_HEADER = headerTemplate(EQUALITY)
    const val LANGUAGE_FEATURES = "LANGUAGE FEATURES"
    const val CODE_SAMPLES = "CODE SAMPLES"

    // https://press.rebus.community/programmingfundamentals/chapter/data-types/
    const val DATA_TYPE =
        "A data type is a classification of data which tells the compiler or interpreter \n" +
            "how the programmer intends to use the data and what type of mathematical, relational or logical operations can be applied.\n"

    // https://www.tutorialspoint.com/java/java_exceptions.htm
    const val EXCEPTION =
        "An exception (or exceptional event) is a problem that arises during the execution of a program."
}
