java {
    sourceCompatibility = JavaVersion.VERSION_21
}

val varVersion = "0.10.4"


dependencies {
    implementation(project(":common"))

    // functional programming
    implementation("io.vavr:vavr:$varVersion")
}
