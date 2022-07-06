package io.github.t3m8ch.db.exceptions

class TodoNotFound(
    override val message: String = "Todo with this ID not found"
) : Exception(message)
