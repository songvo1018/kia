package nosov.models

import kotlinx.serialization.Serializable

@Serializable
data class InterviewResult (val id: String, val personId: String, val personName: String, val interviewId: String, val answers: List<Answer>)
