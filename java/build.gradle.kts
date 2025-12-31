plugins {
    id("io.freefair.lombok") version "8.14.3"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

val varVersion = "0.11.0"

dependencies {
    implementation(project(":common"))

    // functional programming
    implementation("io.vavr:vavr:$varVersion")
}
