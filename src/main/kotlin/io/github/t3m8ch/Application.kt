package io.github.t3m8ch

import io.github.cdimascio.dotenv.Dotenv
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.github.t3m8ch.plugins.*

fun main() {
    val dotenv = Dotenv.load()

    embeddedServer(
        Netty,
        port = dotenv["WEBAPP_PORT"].toInt() ?: 8000,
        host = dotenv["WEBAPP_HOST"] ?: "localhost",
    ) {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
