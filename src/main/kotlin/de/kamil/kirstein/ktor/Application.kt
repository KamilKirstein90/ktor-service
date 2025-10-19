package de.kamil.kirstein.ktor

import de.kamil.kirstein.ktor.de.kamil.kirstein.ktor.configureMonitoring
import de.kamil.kirstein.ktor.de.kamil.kirstein.ktor.plugins.configureSerialization
import de.kamil.kirstein.ktor.de.kamil.kirstein.ktor.routes.configureRouting
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}


// Application.kt ruft Plugins auf → Plugins registrieren Routing → Routing kümmert sich um Routes.
// Plugins sind wie nue fähigkeiten des services
// in Routes sind neue APIs Endpunkte


fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
