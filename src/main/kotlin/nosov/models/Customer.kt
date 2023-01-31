package nosov.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(val id: String,
                    val name: String)

val customerStorage = mutableListOf<Customer>()