package com.example.notizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.notizapp.data.NotesDataSource
import com.example.notizapp.model.Note

class NoteDetailFragment : Fragment() {

    private var noteId: Int = 0
    private lateinit var editTextTitle: EditText
    private lateinit var editTextContent: EditText
    private val notesDataSource = NotesDataSource()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Lade das Layout für dieses Fragment
        return inflater.inflate(R.layout.fragment_details_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextTitle = view.findViewById(R.id.editTextNoteTitle)
        editTextContent = view.findViewById(R.id.editTextNoteContent)

        // Annahme: Die Note ID wird als Argument übergeben
        arguments?.let {
            noteId = it.getInt("noteId")
        }

        // Lade die Notiz basierend auf der ID
        val note = notesDataSource.getNoteById(noteId)
        note?.let {
            editTextTitle.setText(it.title)
            editTextContent.setText(it.content)
        }

        view.findViewById<Button>(R.id.buttonSaveNote).setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val updatedTitle = editTextTitle.text.toString()
        val updatedContent = editTextContent.text.toString()
        val updatedNote = Note(noteId, updatedTitle, updatedContent)

        notesDataSource.updateNote(updatedNote)
        // Optional: Zeige eine Bestätigungsnachricht oder navigiere zurück zur Liste
    }
}
