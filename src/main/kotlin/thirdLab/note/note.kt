package thirdLab.note

import java.net.URL
import java.time.LocalDateTime


sealed class Note(
    val title: String,
    val body: String?,
    val creationDate: LocalDateTime
) {

    class TextNote(
        title: String,
        body: String,
        creationDate: LocalDateTime
    ) : Note(title, body, creationDate) {
        override fun toString(): String {
            return "\nTitle: $title\n" +
                    "Note: $body\n" +
                    "Date: $creationDate\n"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as TextNote
            if (title != other.title) return false
            if (body != other.body) return false
            if (creationDate != other.creationDate) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    class Task(
        title: String,
        private val task: String,
        creationDate: LocalDateTime,
        private val deadLine: LocalDateTime
    ) : Note(title, null, creationDate) {
        override fun toString(): String {
            return "\nTitle: $title\n" +
                    "Task: $task\n" +
                    "Date: $creationDate\n" +
                    "Deadline: $deadLine\n"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Task
            if (title != other.title) return false
            if (task != other.task) return false
            if (creationDate != other.creationDate) return false
            if (deadLine != other.deadLine) return false
            return true
        }

        override fun hashCode(): Int {
            var result = task.hashCode()
            result = 31 * result + deadLine.hashCode()
            return result
        }
    }

    class Link(
        title: String,
        body: String,
        private val link: URL,
        creationDate: LocalDateTime
    ) : Note(title, body, creationDate) {
        override fun toString(): String {
            return "\nTitle: $title\n" +
                    "Note: $body\n" +
                    "Link: $link\n" +
                    "Date: $creationDate\n"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Task
            if (title != other.title) return false
            if (body != other.body) return false
            if (creationDate != other.creationDate) return false
            return true
        }

        override fun hashCode(): Int {
            return link.hashCode()
        }

    }


}