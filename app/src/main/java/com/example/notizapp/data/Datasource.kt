package com.example.notizapp.data

import com.example.notizapp.model.Note

class NotesDataSource {
    val notes = mutableListOf<Note>()

    init {
        for (i in 1..100) {
            notes.add(Note(
                id = i,
                title = "Notiz $i",
                content = "Inhalt für Notiz $i"
            ))
        }
    }
}