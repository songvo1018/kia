package nosov.resources

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nosov.extentions.toDto
import nosov.extentions.toInterview
import nosov.models.*
import nosov.services.InterviewService

fun Routing.interview() {
    val service = InterviewService()

    /*
    Get all interviews
     */
    get<Interviews> {
        call.respond(service.getAll().map(Interview::toDto))
    }

    /*
    Get interview by String ID
    */
    get<Interviews.Id> { attribute ->
        call.respond(service.findById(attribute.id)
            ?.let { interview -> call.respond(interview.toDto()) }
            ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE))
    }

    /*
    Create interview from json
    @Parameter (Interview)
     */
    post<Interviews> {
        service.create(call.receive())
            ?.let { userId ->
                call.response.headers.append("My-User-Id-Header", userId.toString())
                call.respond(HttpStatusCode.Created, userId.toString())
            } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
    }

    /*
    Update {orderQuestions, questions}
     */
    put<Interviews.Id.Edit> { attribute ->
        service.findById(attribute.parent.id)
            ?.let {
                val interviewWithUpdates = call.receive<InterviewDTO>()
                if (service.update(attribute.parent.id, interviewWithUpdates.toInterview()))
                    call.respond(HttpStatusCode.OK, "Interview updated")
                else
                    call.respond(HttpStatusCode.InternalServerError, ErrorResponse.INTERNAL_SERVER_ERROR)
            }
            ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
    }

    /*
    Update interview: add new question to exist interview. Also adds the index of the new question to the orderQuestion property
     */
    put<Interviews.Id.AddQuestion> { attribute ->
        service.findById(attribute.parent.id)
            ?.let {
                val questionToAdd = call.receive<Question>()
                if (service.addNewQuestionToInterview(attribute.parent.id, questionToAdd))
                    call.respond(HttpStatusCode.OK, "Question added")
                else
                  call.respond(HttpStatusCode.InternalServerError, ErrorResponse.INTERNAL_SERVER_ERROR)
            }
            ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
    }

    /*
    Get order data from exist interview
     */
    get<Interviews.Id.Order> { attribute ->
        service.findById(attribute.parent.id)
            ?.let { interview ->
                call.respond(InterviewOrderDTO(interview.orderQuestions, interview.questions.size))
            }
            ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
    }

    /*
    Update order data to exist interview
     */
    put<Interviews.Id.Order> { attribute ->
        service.findById(attribute.parent.id)
            ?.let { interview ->
                val newOrderData = call.receive<InterviewOrderDTO>()
                if (service.updateOrderData(interview, newOrderData))
                    call.respond(HttpStatusCode.OK, "Order updated")
                else
                    call.respond(HttpStatusCode.InternalServerError, ErrorResponse.INTERNAL_SERVER_ERROR)
            }
            ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
    }
}