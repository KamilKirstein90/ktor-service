plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "de.kamil.kirstein.ktor"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)



    // Ktor TestHost: In-Memory-Server für Tests
    testImplementation(libs.ktor.server.test.host)

    // HTTP-Client im Test (für echte Requests gegen testApplication)
    testImplementation(libs.ktor.client.core)
    testImplementation(libs.ktor.client.cio)
    testImplementation(libs.ktor.client.content.negotiation)

    // JSON & Coroutines Test-Hilfen
    testImplementation(libs.kotlinx.serialization.json)
    testImplementation(libs.kotlinx.coroutines.test)

    // JUnit 5 + Kotlin Test
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotlin.test)        // oder libs.kotlin.test.junit, wenn du lieber das nutzt

}

tasks.test {
    // useJUnitPlatform() aktiviert den JUnit 5 TestRunner
    //Ohne das werden Ktor-/JUnit5-Tests nicht ausgeführt
    useJUnitPlatform()
}
