package cinemadb.persons

import io.ktor.application.call
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route
import io.ktor.util.url
import org.koin.dsl.module.module
import org.koin.ktor.ext.inject
import java.time.LocalDateTime
import java.util.UUID

val personModule = module {
    single { PersonService(get()) }
    single { PersonRepository(get()) }
}

fun Routing.personsV1() {

    val personService by inject<PersonService>()

    route("/api/v1/persons") {
        get("/") {
            val result = personService.findAll()
            call.respond(HttpStatusCode.OK, result)
        }

        get("/{id}") {
            val result = personService.findOne(call.parameters["id"]!!)
            call.respond(HttpStatusCode.OK, result)
        }

        post("/") {
            val result = personService.create(call.receive())
            call.response.headers.append(HttpHeaders.Location, call.url {
                encodedPath += "/" + result
            })
            call.respond(HttpStatusCode.Created)
        }

        put("/{id}") {
            personService.update(call.parameters["id"]!!, call.receive())
            call.respond(HttpStatusCode.NoContent)
        }

        delete("/{id}") {
            personService.delete(call.parameters["id"]!!)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}

data class PersonDto(
    val id: String? = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val age: Int,
    val version: Int,
    val created: LocalDateTime,
    val updated: LocalDateTime
)

enum class Gender {
    MALE, FEMALE
}
