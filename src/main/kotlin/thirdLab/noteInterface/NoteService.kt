package thirdLab.noteInterface

import thirdLab.note.Note
import java.net.URL
import java.time.LocalDateTime


interface NoteServiceInterface {
    val listOfNotes: List<Note>

    fun createTextNote(title: String, body: String): Note.TextNote
    fun createTaskNote(title: String, task: String, deadLine: LocalDateTime): Note.Task
    fun createLinkNote(title: String, body: String, link: URL): Note.Link

    fun addToNoteLibrary(newNote: Note)
    fun removeFromNoteLibrary(note: Note)

    fun getAllNotes(): List<Note>
    fun getAllTextNotes(): List<Note.TextNote>
    fun getAllTaskNotes(): List<Note.Task>
    fun getAllLinkNotes(): List<Note.Link>

    fun findByType(type: Class<Any>): List<Note>
    fun findByTitle(title: String): List<Note>

    fun getSortedByTitle(): List<Note>
    fun getSortedByDate(): List<Note>

}