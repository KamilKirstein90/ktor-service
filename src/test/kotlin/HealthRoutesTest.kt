package de.kamil.kirstein.ktor

import de.kamil.kirstein.ktor.routes.HealthResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.withCharset
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HealthRoutesTest {

    @Test
    fun `GET - health returns UP and sane uptime`() = testApplication {
        application {
            module()
        }
        // Test-Client mit JSON-Unterstützung
        val http = createClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
        val res = http.get("/health")
        assertEquals(HttpStatusCode.OK, res.status)

        // Direkt in DTO deserialisieren (kotlinx)
        val dto: HealthResponse = res.body()
        assertEquals("UP", dto.status)
        assertTrue(dto.uptime_ms >= 0, "uptime should be non-negative")

        // Optional obere Schranke (App gerade gestartet; sollte 'klein' sein)
        assertTrue(dto.uptime_ms < 60_000, "uptime should be within the first minute after start")
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), res.contentType())
    }

}