plugins {
    id("org.sonarqube") version "4.4.1.3373"
}

sonar {
    properties {
        property("sonar.projectKey", "ShowMeYourCodeYouTube_java-kotlin-playground")
        property("sonar.organization", "showmeyourcodeyoutube")
    }
}

subprojects {
	group = "com.showmeyourcode.playground"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}
