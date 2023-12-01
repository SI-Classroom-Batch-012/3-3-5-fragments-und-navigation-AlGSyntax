package com.example.notizapp

import NotesAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notizapp.data.NotesDataSource
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.notizapp.model.Note

class NoteListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private val notesDataSource = NotesDataSource() // Ersetze dies durch deine tatsächliche Datenquelle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.notes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialisiere den Adapter mit einem Click-Listener
        notesAdapter = NotesAdapter(notesDataSource.getNotes()) { noteId ->
            onNoteClicked(noteId)
        }
        recyclerView.adapter = notesAdapter

        // Setze den Floating Action Button Listener, um den Dialog zum Hinzufügen einer Notiz anzuzeigen
        view.findViewById<FloatingActionButton>(R.id.fab_add_note).setOnClickListener {
            showAddNoteDialog()
        }
    }

    private fun showAddNoteDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_note, null)
        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextNoteTitle)
        val editTextContent = dialogView.findViewById<EditText>(R.id.editTextNoteContent)

        AlertDialog.Builder(context)
            .setView(dialogView)
            .setTitle("Neue Notiz hinzufügen")
            .setPositiveButton("Speichern") { _, _ ->
                val title = editTextTitle.text.toString().trim()
                val content = editTextContent.text.toString().trim()
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    val newNote = Note(notesDataSource.getNotes().size + 1, title, content)
                    notesDataSource.addNote(newNote)
                    notesAdapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Abbrechen", null)
            .show()
    }
    private fun onNoteClicked(noteId: Int) {
        val action = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(noteId)
        findNavController().navigate(action)
    }
}

// Stelle sicher, dass du eine entsprechende Methode in deiner NotesDataSource-Klasse hast,
// um Notizen hinzuzufügen und eine Liste von Notizen zurückzugeben.

