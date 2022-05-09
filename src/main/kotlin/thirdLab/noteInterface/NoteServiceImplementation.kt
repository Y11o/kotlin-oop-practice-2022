package thirdLab.noteInterface

import thirdLab.note.Note
import java.net.URL
import java.time.LocalDateTime

class NoteServiceImplementation :NoteServiceInterface {
    private val _mutableListOfNotes: MutableList<Note> = mutableListOf()
    override val listOfNotes: List<Note>
        get() = _mutableListOfNotes.toList()

    override fun createTextNote(title: String, body: String): Note.TextNote {
        return Note.TextNote(title, body, LocalDateTime.now())
    }

    override fun createTaskNote(title: String, task: String, deadLine: LocalDateTime): Note.Task {
        return Note.Task(title, task, LocalDateTime.now(), deadLine)
    }

    override fun createLinkNote(title: String, body: String, link: URL): Note.Link {
        return Note.Link(title, body, link, LocalDateTime.now())
    }

    override fun addToNoteLibrary(newNote: Note) {
        _mutableListOfNotes.add(newNote)
    }

    override fun getAllNotes(): List<Note> {
        return listOfNotes
    }
    override fun getAllTextNotes(): List<Note.TextNote> {
        return _mutableListOfNotes.filterIsInstance<Note.TextNote>()
    }
    override fun getAllTaskNotes(): List<Note.Task> {
        return _mutableListOfNotes.filterIsInstance<Note.Task>()
    }
    override fun getAllLinkNotes(): List<Note.Link> {
        return _mutableListOfNotes.filterIsInstance<Note.Link>()
    }

    override fun removeFromNoteLibrary(note: Note) {
        _mutableListOfNotes.remove(note)
    }

    override fun findByTitle(title: String): List<Note> {
        return _mutableListOfNotes.filter{it.title == title}
    }

    override fun findByType(type: Class<Any>): List<Note> {
        return _mutableListOfNotes.filter{it.javaClass == type}
    }

    override fun getSortedByDate(): List<Note> {
        return _mutableListOfNotes.sortedBy { it.creationDate }
    }

    override fun getSortedByTitle(): List<Note> {
        return _mutableListOfNotes.sortedBy { it.title }
    }
}
