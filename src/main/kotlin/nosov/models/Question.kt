package nosov.models

import kotlinx.serialization.Serializable

@Serializable
data class Question (val id: String, val question: String, val answers: List<Answer>, val answerOrder: List<Int>)
