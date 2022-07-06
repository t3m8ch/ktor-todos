package io.github.t3m8ch.web.plugins

import io.github.t3m8ch.web.routes.todoRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        todoRouting()
    }
}
