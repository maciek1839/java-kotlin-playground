plugins {
    id("io.freefair.lombok") version "8.12.2.1"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

val varVersion = "0.10.6"

dependencies {
    implementation(project(":common"))

    // functional programming
    implementation("io.vavr:vavr:$varVersion")
}
