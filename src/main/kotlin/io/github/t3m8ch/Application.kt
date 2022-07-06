package io.github.t3m8ch

import io.github.t3m8ch.di.createAppModule
import io.github.t3m8ch.web.plugins.configureRouting
import io.github.t3m8ch.web.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin

fun main() {
    val config = loadConfigFromDotEnv()

    embeddedServer(Netty, host = config.webappHost, port = config.webappPort) {
        install(Koin) {
            modules(createAppModule(config))
        }
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
