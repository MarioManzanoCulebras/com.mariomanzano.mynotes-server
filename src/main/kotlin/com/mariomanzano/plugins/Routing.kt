package com.mariomanzano.plugins

import com.mariomanzano.models.Note
import com.mariomanzano.repositories.NotesRepository
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    // Starting point for a Ktor app:
    routing {
        route("/notes"){

            // CREATE
            post {
                try {
                    val note = call.receive<Note>()
                    val savedNote = NotesRepository.save(note)
                    call.respond(HttpStatusCode.Created, savedNote)
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Bad JSON Data Body: ${e.message.toString()}"
                    )
                }
            }

            // READ
            get {
                call.respond(NotesRepository.getAll())
            }

            // UPDATE
            get("{id}") {
                val id = call.parameters["id"] ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing or malformed id"
                )

                val note =
                    NotesRepository.getById(id.toLong()) ?: return@get call.respond(
                        HttpStatusCode.NotFound,
                        "No note with id $id"
                    )

                call.respond(note)
            }

            // DELETE
            delete("{id}") {
                val id = call.parameters["id"] ?: return@delete call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing or malformed id"
                )

                if (NotesRepository.delete(id.toLong())) {
                    call.respond(HttpStatusCode.Accepted)
                } else {
                    call.respond(
                        HttpStatusCode.NotFound,
                        "No order with id $id"
                    )
                }
            }
        }
    }
}
