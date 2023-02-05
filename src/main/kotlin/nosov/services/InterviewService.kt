package nosov.services

import com.mongodb.client.FindIterable
import nosov.models.*
import org.litote.kmongo.Id
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class InterviewService {
    private val client = KMongo.createClient()
    private val database = client.getDatabase("interview")
    private val interviewCollection = database.getCollection<Interview>()

    fun create(input: InterviewDTO): Id<Interview>? {
        val interviewFromInput = input.toInterview()
        interviewCollection.insertOne(interviewFromInput)
        return interviewFromInput.id
    }

    private fun InterviewDTO.toInterview(): Interview =
        Interview(
            creatorName = this.creatorName,
            questions = this.questions,
            orderQuestions = listOf(),
            status = InterviewStatus.CREATED,
            results = listOf()
        )

    fun Interview.setStatus(status: InterviewStatus) {
        this.status = status
    }

    fun getAll() : List<Interview> {
        return interviewCollection.find().toList()
    }
}