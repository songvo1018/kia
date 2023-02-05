package nosov

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.*
import io.ktor.server.plugins.cors.CORS
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.resources.Resources
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nosov.models.ErrorResponse
import nosov.models.PersonDto
import nosov.plugins.configureSecurity
import nosov.resources.interview
import nosov.routes.interviewRouting

fun main() {
    embeddedServer(Jetty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    configureResources()
    configureCors()
    configureSecurity()
    configureRouting()
    configureSerialization()
}

fun Application.configureResources() {
    install(Resources)
    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
        interview()
    }
}

fun Application.configureCors() {
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }
}

fun Application.configureRouting() {

    val service = PersonService()
    routing {
//        openAPI(path="openapi", swaggerFile = "openapi/documentation.yaml")
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
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
        interviewRouting()
//        customerRouting()
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}