import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notizapp.R
import com.example.notizapp.model.Note

class NotesAdapter(private val notes: List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    // Diese Methode erstellt neue Views für jeden Listeneintrag.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    // Diese innere Klasse hält die Referenzen zu den Views, die jedes Listenelement darstellen.
    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.note_title)
        val contentTextView: TextView = view.findViewById(R.id.note_content)
    }

    // Diese Methode bindet die Daten an die Views.
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
    }

    // Gibt die Gesamtanzahl der Elemente in der Liste zurück.
    override fun getItemCount() = notes.size
}
