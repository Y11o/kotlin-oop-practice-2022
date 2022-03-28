package firstLab

fun main() {
    val books = "1.Kotlin in action//Dmitry Zhemerov, Svetlana Isakova//2016 " +
            "2.The Golden Book of the Etruscans//Unknown//660 BC " +
            "3.War And Peace//L. N. Tolstoy//1863 "
    val booksList: List<Book> = parseBooks(books)
    for (book in booksList) {
        book.printBook()
    }
    findEarliestPublicationDate(booksList)
    findLatestPublicationDate(booksList)
    findLongestTitle(booksList)
    findShortestTitle(booksList)
}