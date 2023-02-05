package nosov.models

import kotlinx.serialization.Serializable

@Serializable
data class Answer(val text: String, val id: String)
