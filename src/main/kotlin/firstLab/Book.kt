package firstLab

class Book(
    val title: String,
    val authors: List<String>,
    val publicationDate: Int
) {
    override fun toString(): String {
        return "$title, $authors, $publicationDate; "
    }
}

fun parseBooks(books: String): List<Book> {
    var counterForFirstLine = 0
    var charCounter = 0
    var subStart = 0
    var prevChar = 'a'
    val bookList = mutableListOf<Book>()
    for (char in books) {
        charCounter++
        if (charCounter == books.length || (counterForFirstLine > 1 && char == '.' && (prevChar == '1' || prevChar == '2' || prevChar == '3' || prevChar == '4' || prevChar == '5' || prevChar == '6' || prevChar == '7' || prevChar == '8' || prevChar == '9' || prevChar == '0'))) {
            bookList.add(
                bookDeclaration(
                    books.substring(startIndex = subStart, endIndex = charCounter).substringAfter('.')
                        .substringBeforeLast(' ')
                )
            )
            subStart = charCounter - 1
        }
        counterForFirstLine++
        prevChar = char
    }
    return bookList
}

private fun bookDeclaration(book: String): Book {
    return Book(
        book.substringBefore("//"),
        book.substringAfter("//").substringBefore("//").split(","),
        book.substringAfterLast("//").toInt()
    )
}

fun findLongestTitle(bookList: List<Book>): Book? {
    var maxLength = 0
    for (book in bookList) {
        if (maxLength < book.title.length) {
            maxLength = book.title.length
        }
    }
    for (book in bookList) {
        if (maxLength == book.title.length)
            return book
    }
    return null
}

fun findShortestTitle(bookList: List<Book>): Book? {
    var minLength: Int = Int.MAX_VALUE
    for (book in bookList) {
        if (minLength > book.title.length) {
            minLength = book.title.length
        }
    }
    for (book in bookList) {
        if (minLength == book.title.length)
            return book
    }
    return null
}

fun findEarliestPublicationDate(bookList: List<Book>): Book? {
    var minDate: Int = Int.MAX_VALUE
    for (book in bookList) {
        val currDate: Int = book.publicationDate
        if (minDate > currDate) {
            minDate = currDate
        }
    }
    for (book in bookList) {
        if (minDate == book.publicationDate)
            return book
    }
    return null
}

fun findLatestPublicationDate(bookList: List<Book>): Book? {
    var maxDate: Int = Int.MIN_VALUE
    for (book in bookList) {
        val currDate: Int = book.publicationDate
        if (maxDate < currDate) {
            maxDate = currDate
        }
    }
    for (book in bookList) {
        if (maxDate == book.publicationDate)
            return book
    }
    return null
}

