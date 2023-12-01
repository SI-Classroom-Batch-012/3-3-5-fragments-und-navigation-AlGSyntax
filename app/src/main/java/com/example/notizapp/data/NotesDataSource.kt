package com.example.notizapp.data

import com.example.notizapp.model.Note

class NotesDataSource {
    private val notes = mutableListOf<Note>()

    init {
        // Initialfüllen der Liste mit Beispieldaten
        for (i in 1..100) {
            notes.add(Note(
                id = i,
                title = "Notiz $i",
                content = "Inhalt für Notiz $i"
            ))
        }
    }

    fun getNotes(): List<Note> {
        return notes
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id }
    }
    fun updateNote(updatedNote: Note) {
        val index = notes.indexOfFirst { it.id == updatedNote.id }
        if (index != -1) {
            notes[index] = updatedNote
        }
    }

}
