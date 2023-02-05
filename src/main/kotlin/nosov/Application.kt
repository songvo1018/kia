package nosov

import com.example.routes.customerRouting
//import io.bkbn.kompendium.core.plugin.NotarizedApplication
//import io.bkbn.kompendium.json.schema.KotlinXSchemaConfigurator
//import io.bkbn.kompendium.oas.OpenApiSpec
//import io.bkbn.kompendium.oas.info.Contact
//import io.bkbn.kompendium.oas.info.Info
//import io.bkbn.kompendium.oas.info.License
//import io.bkbn.kompendium.oas.server.Server
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.application.install
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nosov.models.ErrorResponse
import nosov.models.PersonDto
import nosov.plugins.configureSecurity
import nosov.routes.interviewRouting
import java.net.URI

fun main() {
    embeddedServer(Jetty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureRouting()
    configureSerialization()
}

fun Application.configureRouting() {
    val service = PersonService()
    routing {
        route("/person") {
            post {
                val request = call.receive<PersonDto>()
                val person = request.toPerson()
                service.create(person)
                    ?.let { userId ->
                        call.response.headers.append("My-User-Id-Header", userId.toString())
                        call.respond(HttpStatusCode.Created)
                    } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
            }
        }
        get("/api") {
            call.respondRedirect("/swagger-ui/index.html?url=/static/test.json", true)
        }
        interviewRouting()
        customerRouting()
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}