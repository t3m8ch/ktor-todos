package io.github.t3m8ch.di

import io.github.t3m8ch.db.dao.interfaces.TodoDAO
import io.github.t3m8ch.db.models.TodoModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class FakeTodoDAO : TodoDAO {
    override fun getAll(): Collection<TodoModel> {
        return listOf(TodoModel(1, "Eat", false), TodoModel(2, "Sleep", false))
    }

    override fun getById(id: Int): TodoModel? {
        return TodoModel(id, "Eat", false)
    }

    override fun create(text: String, isCompleted: Boolean) {}

    override fun deleteByIdIf(id: Int): Boolean {
        return true
    }
}

val appModule = module {
    singleOf(::FakeTodoDAO) { bind<TodoDAO>() }
}