package nosov

import nosov.models.Person
import org.litote.kmongo.Id
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class PersonService {
    private val client = KMongo.createClient()
    private val database = client.getDatabase("person")
    private val personCollection = database.getCollection<Person>()

    fun create(person: Person): Id<Person>? {
        personCollection.insertOne(person)
        return person.id
    }
}