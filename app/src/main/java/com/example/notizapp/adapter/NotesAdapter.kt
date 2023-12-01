import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notizapp.model.Note
import com.example.notizapp.R

class NotesAdapter(
    private val notes: List<Note>,
    private val onNoteClicked: (noteId: Int) -> Unit // Parameter für den Klick-Listener
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
        holder.itemView.setOnClickListener { onNoteClicked(note.id) } // Verwende den Klick-Listener
    }

    override fun getItemCount() = notes.size

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.note_title)
        val contentTextView: TextView = view.findViewById(R.id.note_content)
    }
}
