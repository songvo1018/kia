package nosov.models

import kotlinx.serialization.Serializable

@Serializable
data class InterviewOrderDTO(val orderQuestions: List<Int>?, val size: Int?) {}
