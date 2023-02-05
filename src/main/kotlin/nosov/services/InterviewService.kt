package nosov.services

import io.swagger.v3.oas.annotations.Operation
import jdk.jfr.internal.EventWriter
import nosov.extentions.toInterview
import nosov.models.*
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

class InterviewService {
    private val client = KMongo.createClient()
    private val database = client.getDatabase("interview")
    private val interviewCollection = database.getCollection<Interview>()

    fun create(input: InterviewDTO): Id<Interview>? {
        val interviewFromInput = input.toInterview()
        interviewCollection.insertOne(interviewFromInput)
        return interviewFromInput.id
    }

    fun Interview.setStatus(status: InterviewStatus) {
        this.status = status
    }

    fun getAll(): List<Interview> {
        return interviewCollection.find().toList()
    }

    fun findById(id: String): Interview? {
        val bsonId: Id<Interview> = ObjectId(id).toId()
        return interviewCollection
            .findOne(Interview::id eq bsonId)
    }

    fun update(id: String, request: Interview): Boolean {
        return findById(id)
            ?.let { interview ->
                val updateResult = interviewCollection.replaceOne(
                    interview.copy(orderQuestions = request.orderQuestions, questions = request.questions)
                )
                updateResult.modifiedCount == 1L
            } ?: false
    }

    fun addNewQuestionToInterview(iterviewId: String, question: Question): Boolean {
        return findById(iterviewId)
            ?.let { interview ->
                val questions = interview.questions.toMutableList()
                questions.add(question)
                val orderQuestions = interview.orderQuestions?.toMutableList()
                orderQuestions?.add(questions.size)
                val updateResult = interviewCollection.replaceOne(
                    interview.copy(orderQuestions = orderQuestions, questions = questions)
                )
                updateResult.modifiedCount == 1L
            } == true
    }

    fun updateOrderData(interview: Interview, newOrderData: InterviewOrderDTO): Boolean {
        val updateResult = interviewCollection.replaceOne(
            interview.copy(orderQuestions = newOrderData.orderQuestions)
        )
        return updateResult.modifiedCount == 1L
    }
}