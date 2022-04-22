package thirdLab.noteInterface

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import thirdLab.note.Note
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

internal class NoteServiceImplementationTest {
    private val today = LocalDateTime.of(LocalDate.of(2022, 4, 22), LocalTime.of(14, 30))
    private val tomorrow = LocalDateTime.of(LocalDate.of(2022, 4, 23), LocalTime.of(18, 0))
    private val noteList = NoteServiceImplementation()
    private val buyProducts = noteList.createTaskNote("Shopping on Friday","Buy something", today)
    private val task2 = noteList.createTaskNote("Shopping of Saturday", "Buy something", tomorrow)
    private val textNote = noteList.createTextNote("Some text note", "Sample text")
    private val linkNote = noteList.createLinkNote("Git link", "My Git", URL("https://github.com/Y11o"))

    @Test
    fun addToNoteLibrary() {
        noteList.addToNoteLibrary(buyProducts)
        assertEquals(listOf<Note>(buyProducts), noteList.getAllNotes())
    }

    @Test
    fun createTextNote() {
        noteList.addToNoteLibrary(textNote)
        assertEquals(listOf<Note>(textNote), noteList.getAllNotes())
    }

    @Test
    fun createTaskNote() {
        noteList.addToNoteLibrary(task2)
        assertEquals(listOf<Note>(task2), noteList.getAllNotes())
    }

    @Test
    fun createLinkNote() {
        noteList.addToNoteLibrary(linkNote)
        assertEquals(listOf<Note>(linkNote), noteList.getAllNotes())
    }

    @Test
    fun getAllNotes() {
        noteList.addToNoteLibrary(buyProducts)
        noteList.addToNoteLibrary(task2)
        noteList.addToNoteLibrary(textNote)
        noteList.addToNoteLibrary(linkNote)
        assertEquals(listOf(buyProducts, task2, textNote, linkNote), noteList.getAllNotes())
    }

    @Test
    fun getAllTextNotes() {
        noteList.addToNoteLibrary(buyProducts)
        noteList.addToNoteLibrary(task2)
        noteList.addToNoteLibrary(textNote)
        noteList.addToNoteLibrary(linkNote)
        val textNote2 = noteList.createTextNote("Another text note","Sample text")
        noteList.addToNoteLibrary(textNote2)
        assertEquals(listOf<Note>(textNote, textNote2), noteList.getAllTextNotes())
    }

    @Test
    fun getAllTaskNotes() {
        noteList.addToNoteLibrary(buyProducts)
        noteList.addToNoteLibrary(task2)
        noteList.addToNoteLibrary(textNote)
        noteList.addToNoteLibrary(linkNote)
        assertEquals(listOf<Note>(buyProducts, task2), noteList.getAllTaskNotes())
    }

    @Test
    fun getAllLinkNotes() {
        noteList.addToNoteLibrary(buyProducts)
        noteList.addToNoteLibrary(task2)
        noteList.addToNoteLibrary(textNote)
        noteList.addToNoteLibrary(linkNote)
        val linkNote2 = noteList.createLinkNote("Another text note","Sample text", URL("https://github.com/Y11o/kotlin-oop-practice-2022"))
        noteList.addToNoteLibrary(linkNote2)
        assertEquals(listOf<Note>(linkNote, linkNote2), noteList.getAllLinkNotes())
    }

    @Test
    fun removeFromNoteLibrary() {
        noteList.addToNoteLibrary(buyProducts)
        noteList.addToNoteLibrary(task2)
        noteList.addToNoteLibrary(textNote)
        noteList.addToNoteLibrary(linkNote)
        assertEquals(listOf(buyProducts, task2, textNote, linkNote), noteList.getAllNotes())
        noteList.removeFromNoteLibrary(task2)
        assertEquals(listOf(buyProducts, textNote, linkNote), noteList.getAllNotes())
    }

    @Test
    fun findByTitle() {
        noteList.addToNoteLibrary(buyProducts)
        noteList.addToNoteLibrary(task2)
        noteList.addToNoteLibrary(textNote)
        noteList.addToNoteLibrary(linkNote)
        assertEquals(listOf<Note>(task2), noteList.findByTitle("Shopping of Saturday"))
    }

    @Test
    fun findByType() {
        noteList.addToNoteLibrary(buyProducts)
        noteList.addToNoteLibrary(task2)
        noteList.addToNoteLibrary(textNote)
        noteList.addToNoteLibrary(linkNote)
        assertEquals(listOf<Note>(textNote), noteList.findByType(textNote.javaClass))
    }

    @Test
    fun getSortedByDate() {
        noteList.addToNoteLibrary(buyProducts)
        noteList.addToNoteLibrary(task2)
        noteList.addToNoteLibrary(textNote)
        noteList.addToNoteLibrary(linkNote)
        assertEquals(listOf(buyProducts, task2, textNote, linkNote), noteList.getSortedByDate())
    }

    @Test
    fun getSortedByTitle() {
        noteList.addToNoteLibrary(buyProducts)
        noteList.addToNoteLibrary(task2)
        noteList.addToNoteLibrary(textNote)
        noteList.addToNoteLibrary(linkNote)
        assertEquals(listOf(linkNote, task2, buyProducts, textNote), noteList.getSortedByTitle())
    }
}