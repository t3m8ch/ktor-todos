package io.github.t3m8ch.db.dao.pgsql

import io.github.t3m8ch.db.dao.interfaces.TodoDAO
import io.github.t3m8ch.db.models.TodoModel
import java.sql.Connection
import java.sql.ResultSet

class PgsqlTodoDAO(private val connection: Connection) : TodoDAO {
    override fun getAll(): Collection<TodoModel> {
        val statement = connection.prepareStatement("SELECT * FROM todo;")
        val resultSet = statement.executeQuery()

        val models = mutableListOf<TodoModel>()
        while (resultSet.next()) {
            models += createTodoModelFromResultSet(resultSet)
        }

        return models
    }

    override fun getById(id: Int): TodoModel? {
        val statement = connection.prepareStatement("SELECT * FROM todo WHERE id = ?;")
        statement.setInt(1, id)

        val resultSet = statement.executeQuery()
        resultSet.next()

        return createTodoModelFromResultSet(resultSet)
    }

    override fun create(text: String, isCompleted: Boolean) {
        val statement = connection.prepareStatement(
            "INSERT INTO todo (body, is_completed) VALUES (?, ?)"
        )

        statement.setString(1, text)
        statement.setBoolean(2, isCompleted)

        statement.executeUpdate()
    }

    override fun deleteByIdIf(id: Int): Boolean {
        val statement = connection.prepareStatement(
            "DELETE FROM todo WHERE id = ?"
        )
        statement.setInt(1, id)

        return statement.executeUpdate() >= 1
    }
}

private fun createTodoModelFromResultSet(resultSet: ResultSet): TodoModel {
    return TodoModel(
        id = resultSet.getInt("id"),
        text = resultSet.getString("body"),
        isCompleted = resultSet.getBoolean("is_completed")
    )
}