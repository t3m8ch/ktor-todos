package io.github.t3m8ch

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.github.t3m8ch.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
