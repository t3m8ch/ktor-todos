package io.github.t3m8ch.web.dto

@kotlinx.serialization.Serializable
data class CreateTodoDTO(val text: String, val isCompleted: Boolean = false)
