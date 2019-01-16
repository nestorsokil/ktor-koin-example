package cinemadb.movies

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

val movieModule = module {
    single { MovieService() }
}

fun Routing.moviesV1() {

    val movieService by inject<MovieService>()

    route("/api/v1/movies") {

        get("/") {
            val result = movieService.findAll()
            call.respond(HttpStatusCode.OK, result)
        }

        get("/{id}") {
            val result = movieService.findOne(call.parameters["id"]!!)
            call.respond(HttpStatusCode.OK, result)
        }

        post("/") {
            val result = movieService.create(call.receive())
            call.response.headers.append(HttpHeaders.Location, call.url {
                encodedPath += "/" + result.id
            })
            call.respond(HttpStatusCode.Created)
        }

        put("/{id}") {
            movieService.update(call.parameters["id"]!!, call.receive())
            call.respond(HttpStatusCode.NoContent)
        }

        delete("/{id}") {
            movieService.delete(call.parameters["id"]!!)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}

data class MovieDto(val id: String)
