plugins {
    java
    kotlin("jvm") version "1.9.23" apply false

    id("org.jlleitschuh.gradle.ktlint") version "12.1.0" apply false

    id("org.sonarqube") version "4.4.1.3373"
}

allprojects{
    group = "com.showmeyourcode.playground"
    version = "0.0.1-SNAPSHOT"

    apply(plugin ="java")
    apply(plugin ="org.sonarqube")

    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    sonar {
        properties {
            property("sonar.projectKey", "ShowMeYourCodeYouTube_java-kotlin-playground")
            property("sonar.organization", "showmeyourcodeyoutube")
        }
    }
}

val slf4jVersion = "1.7.30"
val logbackVersion = "1.2.3"
val junitJupiterVersion = "5.10.2"

subprojects {
    apply(plugin ="java")
    apply(plugin ="kotlin")

    val implementation by configurations
    val testRuntimeOnly by configurations
    val testImplementation by configurations

    dependencies {
        implementation("org.slf4j:slf4j-api:$slf4jVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")
        implementation("ch.qos.logback:logback-core:$logbackVersion")

        // required to create a Gradle test report
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    }

    tasks.test {
        useJUnitPlatform()
    }
}
