package nosov.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val message: String) {
    companion object {
        val NOT_FOUND_RESPONSE = ErrorResponse("Person was not found")
        val BAD_REQUEST_RESPONSE = ErrorResponse("Invalid request")
        val INTERNAL_SERVER_ERROR = ErrorResponse("Something went wrong")
    }
}
