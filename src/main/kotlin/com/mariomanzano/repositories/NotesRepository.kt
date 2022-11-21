package com.mariomanzano.repositories

import com.mariomanzano.models.Note
import com.mariomanzano.models.Note.*
import com.mariomanzano.models.Note.Type.*

object NotesRepository {

    fun getAll() : List<Note> {
        val notes = (0..10).map {
            Note(
                "Title $it",
                "Description $it",
                if (it % 3 == 0) AUDIO else TEXT
            )
        }
        return notes
    }
}