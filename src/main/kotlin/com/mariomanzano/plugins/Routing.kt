package com.mariomanzano.plugins

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
            get {
                call.respond(NotesRepository.getAll())
            }
        }
    }
}
