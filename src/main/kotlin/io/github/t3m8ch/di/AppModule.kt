package io.github.t3m8ch.di

import io.github.t3m8ch.Config
import io.github.t3m8ch.db.dao.interfaces.TodoDAO
import io.github.t3m8ch.db.dao.pgsql.PgsqlTodoDAO
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.sql.DriverManager

fun createAppModule(config: Config) = module {
    single { DriverManager.getConnection(config.dbUrl) }
    singleOf(::PgsqlTodoDAO) { bind<TodoDAO>() }
}
