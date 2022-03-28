package firstLab

class Book (
    _title: String,
    _author: String,
    _publicationDate: String
) {
    private val title: String
    private val author: String
    private val publicationDate: String
    init {
        title = _title.ifEmpty { error("Title shouldn't be empty") }
        author = _author.ifEmpty { error("You should mention at least one author") }
        publicationDate = _publicationDate.ifEmpty { error("Publication date shouldn't be empty") }
    }

    fun getPublicationDate(): Int {
        if (publicationDate.length > 4)
            return publicationDate.substringBefore(' ').toInt()
        return publicationDate.toInt()
    }
    fun getTitle():String{
        return title
    }
    fun printBook(){
        println("$title, $author, $publicationDate")
    }
    fun getAuthor():String{
        return author
    }
}

fun parseBooks(books: String): List<Book>{
    var counterForFirstLine = 0
    var charCounter = 0
    var subStart = 0
    var prevChar = 'a'
    val bookList = mutableListOf<Book>()
    for (char in books){
        charCounter++
        if (charCounter == books.length || (counterForFirstLine > 1 && char == '.' && (prevChar == '1' || prevChar == '2' || prevChar == '3' || prevChar == '4' || prevChar == '5' || prevChar == '6' || prevChar == '7' || prevChar == '8' || prevChar == '9' || prevChar == '0'))){
            bookList.add(bookDeclaration(books.substring(startIndex = subStart, endIndex = charCounter).substringAfter('.').substringBeforeLast(' ')))
            subStart = charCounter - 1
        }
        counterForFirstLine++
        prevChar = char
    }
    return bookList
}

fun bookDeclaration (book: String): Book{
    return Book(book.substringBefore("//"),book.substringAfter("//").substringBefore("//"), book.substringAfterLast("//"))
}

fun findLongestTitle(bookList: List<Book>){
    var maxLength = 0
    for (book in bookList){
        if (maxLength < book.getTitle().length){
            maxLength = book.getTitle().length
        }
    }
    for (book in bookList){
        if (maxLength == book.getTitle().length)
            book.printBook()
    }
}

fun findShortestTitle(bookList: List<Book>) {
    var minLength:Int  = Int.MAX_VALUE
    for (book in bookList){
        if (minLength > book.getTitle().length){
            minLength = book.getTitle().length
        }
    }
    for (book in bookList){
        if (minLength == book.getTitle().length)
            book.printBook()
    }
}

fun findEarliestPublicationDate(bookList: List<Book>){
    var minDate:Int = Int.MAX_VALUE
    for (book in bookList){
        val currDate: Int = book.getPublicationDate()
        if (minDate > currDate){
            minDate = currDate
        }
    }
    for (book in bookList){
        if (minDate == book.getPublicationDate())
            book.printBook()
    }
}

fun findLatestPublicationDate(bookList: List<Book>){
    var maxDate:Int = Int.MIN_VALUE
    for (book in bookList){
        val currDate: Int = book.getPublicationDate()
        if (maxDate < currDate){
            maxDate = currDate
        }
    }
    for (book in bookList){
        if (maxDate == book.getPublicationDate())
            book.printBook()
    }
}

