package nosov.extentions

import nosov.models.Interview
import nosov.models.InterviewDTO
import nosov.models.InterviewStatus

fun Interview.toDto(): InterviewDTO =
    InterviewDTO(
        id = this.id.toString(),
        creatorName = this.creatorName,
        questions = this.questions
    )

fun InterviewDTO.toInterview(): Interview =
    Interview(
        creatorName = this.creatorName,
        questions = this.questions,
        orderQuestions = listOf(),
        status = InterviewStatus.CREATED,
        results = listOf()
    )