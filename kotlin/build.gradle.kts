plugins {
    id("org.jlleitschuh.gradle.ktlint")
}

val coroutinesVersion: String = "1.10.2"
val arrowVersion: String = "1.2.4"
val kotestVersion: String = "5.9.1"
val arrowTestVersion: String = "1.4.0"
val mockkVersion: String = "1.14.7"

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    api(project(":common"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    // functional programming
    implementation("io.arrow-kt:arrow-core-jvm:$arrowVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-assertions-arrow-jvm:$arrowTestVersion")
    testImplementation("io.kotest:kotest-property-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-json-jvm:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-property-arrow:$arrowTestVersion")
}
