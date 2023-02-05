package nosov.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nosov.extentions.toDto
import nosov.models.ErrorResponse
import nosov.models.Interview
import nosov.services.InterviewService


fun Route.interviewRouting() {
    val service = InterviewService()
    route("/interview") {
        get {
            call.respond(service.getAll().map(Interview::toDto))
        }

//        this is creating above routing
        post {
            service.create(call.receive())
                ?.let { userId ->
                    call.response.headers.append("My-User-Id-Header", userId.toString())
                    call.respond(HttpStatusCode.Created, userId.toString())
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)

        }
    }

}