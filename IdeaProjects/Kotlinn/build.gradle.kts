plugins {
    kotlin("jvm") version "1.9.22"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0") // JUnit 5 -riippuvuus
}

tasks.test {
    useJUnitPlatform() // Pakota Gradle käyttämään JUnit 5 -alustaa
}

kotlin {
    jvmToolchain(21) // JVM:n version asetukset
}
