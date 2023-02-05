val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kmongo_version: String by project
val swagger_codegen_version = "1.0.36"
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
    implementation("io.ktor:ktor-server-resources:$ktor_version")
    implementation("org.litote.kmongo:kmongo:$kmongo_version")
    implementation("io.ktor:ktor-server-openapi:$ktor_version")
    implementation("io.ktor:ktor-server-swagger:$ktor_version")
    implementation("io.swagger.codegen.v3:swagger-codegen-generators:$swagger_codegen_version")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.6.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.0")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.0")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-cors-jvm:2.2.2")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.2.2")
}

// The values oasPackage, oasSpecLocation, oasGenOutputDir are defined earlier
//tasks.register(name = "generateServer", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
//    input = project.file(oasSpecLocation).path
//    outputDir.set(oasGenOutputDir.get().toString())
//    modelPackage.set("$oasPackage.model")
//    apiPackage.set("$oasPackage.api")
//    packageName.set(oasPackage)
//    generatorName.set("kotlin-spring")
//    configOptions.set(
//        mapOf(
//            "dateLibrary" to "java8",
//            "interfaceOnly" to "true",
//            "useTags" to "true"
//        )
//    )
//}