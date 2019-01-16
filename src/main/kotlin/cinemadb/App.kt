package cinemadb

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders

fun Application.main() {

    install(DefaultHeaders)
    install(CallLogging)

}
