package io.github.t3m8ch.web.dto

@kotlinx.serialization.Serializable
data class TodoDTO(val id: Int, val text: String, val isCompleted: Boolean)