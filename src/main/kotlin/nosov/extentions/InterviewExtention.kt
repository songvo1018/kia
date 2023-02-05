package nosov.extentions

import nosov.models.Interview
import nosov.models.InterviewDTO

fun Interview.toDto(): InterviewDTO =
    InterviewDTO(
        id = this.id.toString(),
        creatorName = this.creatorName,
        questions = this.questions
    )
