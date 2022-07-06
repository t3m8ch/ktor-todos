package io.github.t3m8ch

import io.github.cdimascio.dotenv.Dotenv

data class Config(val webappPort: Int, val webappHost: String, val dbUrl: String)

fun loadConfigFromDotEnv(): Config {
    val dotenv = Dotenv.load()
    return Config(
        dbUrl = dotenv["DB_URL"] ?: throw Exception(
            "You have not set the DB_URL environment variable"
        ),
        webappHost = dotenv["WEBAPP_HOST"] ?: "localhost",
        webappPort = dotenv["WEBAPP_PORT"]?.toInt() ?: 8000
    )
}
