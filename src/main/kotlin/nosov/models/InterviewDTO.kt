package nosov.models

import kotlinx.serialization.Serializable

@Serializable
data class InterviewDTO(
    val id: String? = null,
    val creatorName: String,
    val questions: List<Question>,
    val orderQuestions: List<Int>?
) {}
