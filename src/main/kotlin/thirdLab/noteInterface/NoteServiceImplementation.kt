package thirdLab.noteInterface

import thirdLab.note.Note
import java.net.URL
import java.time.LocalDateTime
import java.util.logging.Logger

val programLogger: Logger = Logger.getLogger(NoteServiceImplementation::class.java.name)

class NoteServiceImplementation :NoteServiceInterface {
    private val _mutableListOfNotes: MutableList<Note> = mutableListOf()
    override val listOfNotes: List<Note>
        get() = _mutableListOfNotes.toList()

    override fun createTextNote(title: String, body: String): Note.TextNote {
        programLogger.info("New text note was created")
        return Note.TextNote(title, body, LocalDateTime.now())
    }

    override fun createTaskNote(title: String, task: String, deadLine: LocalDateTime): Note.Task {
        programLogger.info("New task note was created")
        return Note.Task(title, task, LocalDateTime.now(), deadLine)
    }

    override fun createLinkNote(title: String, body: String, link: URL): Note.Link {
        programLogger.info("New link note was created")
        return Note.Link(title, body, link, LocalDateTime.now())
    }

    override fun addToNoteLibrary(newNote: Note) {
        programLogger.info("Note was added to note list")
        _mutableListOfNotes.add(newNote)
    }

    override fun getAllNotes(): List<Note> {
        programLogger.info("All notes were returned")
        return listOfNotes
    }
    override fun getAllTextNotes(): List<Note.TextNote> {
        programLogger.info("All text notes were filtered")
        return _mutableListOfNotes.filterIsInstance<Note.TextNote>()
    }
    override fun getAllTaskNotes(): List<Note.Task> {
        programLogger.info("All task notes were filtered")
        return _mutableListOfNotes.filterIsInstance<Note.Task>()
    }
    override fun getAllLinkNotes(): List<Note.Link> {
        programLogger.info("All link notes were filtered")
        return _mutableListOfNotes.filterIsInstance<Note.Link>()
    }

    override fun removeFromNoteLibrary(note: Note) {
        programLogger.info("Note was removed")
        _mutableListOfNotes.remove(note)
    }

    override fun findByTitle(title: String): List<Note> {
        programLogger.info("List of notes was filtered by title")
        return _mutableListOfNotes.filter{it.title == title}
    }

    override fun findByType(type: Class<Any>): List<Note> {
        programLogger.info("List of notes was filtered by note's type")
        return _mutableListOfNotes.filter{it.javaClass == type}
    }

    override fun getSortedByDate(): List<Note> {
        programLogger.info("List of notes was sorted by creation dates")
        return _mutableListOfNotes.sortedBy { it.creationDate }
    }

    override fun getSortedByTitle(): List<Note> {
        programLogger.info("List of notes was sorted by titles")
        return _mutableListOfNotes.sortedBy { it.title }
    }
}