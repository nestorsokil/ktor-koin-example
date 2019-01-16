package cinemadb

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging

fun Application.main() {

    install(CallLogging)

}
