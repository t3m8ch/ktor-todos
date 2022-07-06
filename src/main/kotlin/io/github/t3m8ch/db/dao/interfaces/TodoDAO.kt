package io.github.t3m8ch.db.dao.interfaces

import io.github.t3m8ch.db.models.TodoModel

interface TodoDAO {
    fun getAll(): Collection<TodoModel>
    fun getById(id: Int): TodoModel?
    fun create(text: String, isCompleted: Boolean = false)
    fun deleteByIdIf(id: Int): Boolean
}