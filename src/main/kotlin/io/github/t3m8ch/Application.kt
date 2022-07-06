package io.github.t3m8ch

import io.github.cdimascio.dotenv.Dotenv
import io.github.t3m8ch.di.appModule
import io.github.t3m8ch.web.plugins.configureRouting
import io.github.t3m8ch.web.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin

fun main() {
    val dotenv = Dotenv.load()

    embeddedServer(
        Netty,
        port = dotenv["WEBAPP_PORT"]?.toInt() ?: 8000,
        host = dotenv["WEBAPP_HOST"] ?: "localhost",
    ) {
        install(Koin) {
            modules(appModule)
        }
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
