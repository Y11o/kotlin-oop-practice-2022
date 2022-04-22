package thirdLab.note

import java.net.URL
import java.time.LocalDateTime


sealed class Note(
    var title: String,
    var body: String?,
    val creationDate: LocalDateTime
) {

    class TextNote(
        _title: String,
        _body: String,
        _creationDate: LocalDateTime
    ) : Note(_title, _body, _creationDate){
        override fun toString(): String {
            return "Title: $title" +
                    "Note: $body" +
                    "Date: $creationDate"
        }
    }

    class Task(
        _title: String,
        private val task: String,
        _creationDate: LocalDateTime,
        private var deadLine: LocalDateTime
    ) : Note(_title, null, _creationDate){
        override fun toString(): String {
            return "Title: $title" +
                    "Task: $task" +
                    "Date: $creationDate" +
                    "Deadline: $deadLine"
        }
    }

    class Link(
        _title: String,
        _body: String,
        private var link: URL,
        _creationDate: LocalDateTime
    ) : Note(_title, _body, _creationDate){
        override fun toString(): String {
            return "Title: $title" +
                    "Note: $body" +
                    "Link: $link" +
                    "Date: $creationDate"
        }
    }



}