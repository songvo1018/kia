package nosov

import nosov.models.Person
import nosov.models.PersonDto

fun Person.toDto(): PersonDto =
    PersonDto(
        id = this.id.toString(),
        name = this.name,
        age = this.age
    )
fun PersonDto.toPerson(): Person =
    Person(
        name = this.name,
        age = this.age
    )