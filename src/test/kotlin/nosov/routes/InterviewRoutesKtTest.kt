package nosov.routes;

import nosov.module

import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class InterviewRoutesKtTest {

    @Test
    fun testPostInterview() = testApplication {
        application {
            module()
        }
        client.post("/interview").apply {
            TODO("Please write your test here")
        }
    }
}