plugins {
    id("io.freefair.lombok") version "8.14.2"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

val varVersion = "0.10.7"

dependencies {
    implementation(project(":common"))

    // functional programming
    implementation("io.vavr:vavr:$varVersion")
}
