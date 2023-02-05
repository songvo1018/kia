val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kmongo_version: String by project
//TODO: documentation lib compile on 17 java, but my project on 8

plugins {
    kotlin("jvm") version "1.8.0" // change here 
    application
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"
    id("io.ktor.plugin") version "2.2.2"
}

group = "nosov"
version = "0.0.1"
application {
    mainClass.set("nosov.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()

}

allprojects {
    repositories {
        maven { setUrl("https://jitpack.io") }
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-jetty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("org.litote.kmongo:kmongo:$kmongo_version")
//    implementation("io.ktor:ktor-server-openapi:$ktor_version")
//    implementation("io.ktor:ktor-server-swagger:$ktor_version")
//    implementation("io.bkbn:kompendium-core:latest.release")
    implementation("ch.qos.logback:logback-classic:$logback_version")
//    implementation("io.github.smiley4:ktor-swagger-ui:1.1.0")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.2.2")
}