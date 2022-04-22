package thirdLab

import thirdLab.noteInterface.NoteServiceImplementation
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


fun main() {
    val today = LocalDateTime.of(LocalDate.of(2022, 4, 22), LocalTime.of(14, 30))
    val tomorrow = LocalDateTime.of(LocalDate.of(2022, 4, 23), LocalTime.of(18, 0))
    val noteList = NoteServiceImplementation()

    val buyProducts = noteList.createTaskNote("Shopping on Friday","Buy something", today)
    val task2 = noteList.createTaskNote("Shopping of Saturday", "Buy something", tomorrow)

    val textNote = noteList.createTextNote("Some text note", "Sample text")
    val linkNote = noteList.createLinkNote("Git link", "My Git", URL("https://github.com/Y11o"))

    noteList.addToNoteLibrary(buyProducts)
    noteList.addToNoteLibrary(task2)
    noteList.addToNoteLibrary(textNote)
    println("Note library without link note: ${noteList.getAllNotes()}")
    noteList.addToNoteLibrary(linkNote)
    println("Note library with all notes: ${noteList.getAllNotes()}")
    println("All text notes: ${noteList.getAllTextNotes()}")
    println("Note tasks: ${noteList.getAllTaskNotes()}")
    println("Note links: ${noteList.getAllLinkNotes()}")

    println("Note library filtered by title: ${noteList.findByTitle("Shopping")}")
    println("Note library filtered by type: ${noteList.findByType(buyProducts.javaClass)}")

    println("Note library sorted by title: ${noteList.getSortedByTitle()}")
    println("Note library sorted by date: ${noteList.getSortedByDate()}")

    noteList.removeFromNoteLibrary(task2)
    println("Note library with removed task 2 ${noteList.getAllNotes()}")
}