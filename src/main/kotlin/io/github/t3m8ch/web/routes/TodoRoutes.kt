package io.github.t3m8ch.web.routes

import io.github.t3m8ch.db.dao.interfaces.TodoDAO
import io.github.t3m8ch.db.models.TodoModel
import io.github.t3m8ch.web.dto.CommandStatusDTO
import io.github.t3m8ch.web.dto.CreateTodoDTO
import io.github.t3m8ch.web.dto.TodoDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.todoRouting() {
    val todoDAO by inject<TodoDAO>()

    route("/todos") {
        get {
            val models = todoDAO.getAll()
            call.respond(models.map { m -> mapModelToDTO(m) })
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest,
            )

            val model = todoDAO.getById(
                id.toIntOrNull() ?: return@get call.respondText(
                    "Not valid id",
                    status = HttpStatusCode.BadRequest,
                )
            ) ?: return@get call.respondText(
                "Not found",
                status = HttpStatusCode.NotFound,
            )

            call.respond(mapModelToDTO(model))
        }
        post {
            val createTodoDTO = call.receive<CreateTodoDTO>()

            todoDAO.create(createTodoDTO.text, createTodoDTO.isCompleted)
            call.respond(CommandStatusDTO())
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest,
            )

            if (todoDAO.deleteByIdIf(
                    id.toIntOrNull() ?: return@delete call.respondText(
                        "Not valid id",
                        status = HttpStatusCode.BadRequest,
                    )
                )
            ) {
                call.respond(CommandStatusDTO())
            } else {
                call.respondText(
                    "Not found",
                    status = HttpStatusCode.NotFound,
                )
            }
        }
    }
}

private fun mapModelToDTO(model: TodoModel): TodoDTO =
    TodoDTO(model.id, model.text, model.isCompleted)
