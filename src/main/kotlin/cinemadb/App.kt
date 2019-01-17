package cinemadb

import cinemadb.movies.movieModule
import cinemadb.movies.moviesV1
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import org.koin.log.Logger.SLF4JLogger
import org.koin.standalone.StandAloneContext.startKoin

fun Application.main() {

    startKoin(listOf(movieModule), logger = SLF4JLogger())

    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            registerModule(JavaTimeModule())
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }

    routing {
        moviesV1()
    }
}
