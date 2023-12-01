package com.example.notizapp

import NotesAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notizapp.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        recyclerView = view.findViewById(R.id.notes_recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Ersetze dies durch deine tatsächliche Datenquelle
        val notes = listOf<Note>() // Hier die Notizen laden

        notesAdapter = NotesAdapter(notes)
        recyclerView.adapter = notesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Listener für den Button, um eine neue Notiz hinzuzufügen
        view.findViewById<FloatingActionButton>(R.id.fab_add_note).setOnClickListener {
            findNavController().navigate(R.id.action_NoteListFragment_to_addNoteFragment)
        }
    }
}
