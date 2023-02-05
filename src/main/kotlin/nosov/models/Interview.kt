package nosov.models

import io.swagger.v3.oas.annotations.media.Schema
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Schema(description = "Model for interview data")
@Serializable
data class Interview (
    @BsonId
    val id: Id<Interview>? = null,

    @field:Schema(
        description = "Interviewer name",
        example = "Alex",
        type = "String",
    )
    val creatorName: String,

    @field:Schema(
        description = "List of @Question for the interviewee. May be empty, but interview with empty question list will not be passed",
        type = "List<Question>"
    )
    val questions: List<Question>,

    @field:Schema(
        description = "List question indexes. Might be use for rendering order of question. In future will be refactoring",
        type = "List<Int>"
    )
    var orderQuestions: List<Int>?,

    @field:Schema(
        description = "Status of interview. Will be changed by life circle of Interview",
        type = "one of enum InterviewStatus"
    )
    var status: InterviewStatus,

    @field:Schema(
        description = "The list contains the results of the survey after passing by the respondents",
        type = "List<InterviewResult"
    )
    var results: List<InterviewResult>
    )
{}
