package de.kamil.kirstein.ktor.routes

import io.ktor.server.routing.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

private val startTime = System.currentTimeMillis()


/*
In REST-APIs, vor allem in Microservices oder später Cloud Deployments, ist es Best Practice, immer eigene Response-Klassen zu definieren → sogar für einfache Health-Endpoints.

Warum?

Klare Schnittstellen → später prüfbar & versionierbar

Leicht erweiterbar (z. B. version, env, last_scan_at, ...)

Statisch analysierbar (für Dokumentation / OpenAPI später)
 */

@Serializable
data class HealthResponse(val status: String, val uptime_ms: Long)

fun Route.healthRoutes() {

    route("/health") {
        get {
            call.respond(
                HealthResponse(
                    status = "UP",
                    uptime_ms = (System.currentTimeMillis() - startTime)
                )
            )
        }
    }

}
