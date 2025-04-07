plugins {
    java
    kotlin("jvm") version "2.1.20" apply false

    id("org.jlleitschuh.gradle.ktlint") version "12.2.0" apply false

    id("org.sonarqube") version "6.1.0.5360"
    jacoco
}

allprojects{
    group = "com.showmeyourcode.playground"
    version = "0.0.2-SNAPSHOT"

    apply(plugin ="java")
    apply(plugin ="org.sonarqube")

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    sonar {
        properties {
            property("sonar.projectKey", "ShowMeYourCodeYouTube_java-kotlin-playground")
            property("sonar.organization", "showmeyourcodeyoutube")
            property("sonar.coverage.jacoco.xmlReportPaths",
                "$rootDir/coverage-report/build/reports/jacoco/testCodeCoverageReport/testCodeCoverageReport.xml"
            )
        }
    }
}

val slf4jVersion = "2.0.17"
val logbackVersion = "1.5.18"
val junitJupiterVersion = "5.11.4"
val awaitilityVersion = "4.3.0"
val reactorVersion = "3.7.4"

subprojects {
    apply(plugin ="java")
    apply(plugin ="kotlin")
    apply(plugin = "jacoco")

    val implementation by configurations
    val testRuntimeOnly by configurations
    val testImplementation by configurations

    dependencies {
        implementation("org.slf4j:slf4j-api:$slf4jVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")
        implementation("ch.qos.logback:logback-core:$logbackVersion")
        implementation("io.projectreactor:reactor-core:$reactorVersion")

        // required to create a Gradle test report
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")

        // other test libraries
        testImplementation("org.awaitility:awaitility:$awaitilityVersion")
    }

    tasks.test {
        // report is always generated after tests run
        finalizedBy(tasks.jacocoTestReport)
    }
    tasks.jacocoTestReport {
        // tests are required to run before generating the report
        dependsOn(tasks.test)
    }

    tasks.test {
        useJUnitPlatform()
    }
}
