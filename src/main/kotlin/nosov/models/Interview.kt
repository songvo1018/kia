package nosov.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class Interview (
    @BsonId
    val id: Id<Interview>? = null,
    val creatorName: String,
    val questions: List<Question>,
    var orderQuestions: List<Int>?,
    var status: InterviewStatus,
    var results: List<InterviewResult>
    )
{}
