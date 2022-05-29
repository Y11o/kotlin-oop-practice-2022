package firstLab

import kotlin.test.Test
import org.junit.jupiter.api.Assertions.*

internal class BookKtTest{

    @Test
    fun bookParserTest(){
        val books = "1.Crime and Punishment//F.M. Dostoevsky//1866 " +
                "2.The Master and Margarita//M.A. Bulgakov//1966 " +
                "3.1984//G. Orwell//1949 "
        val bookList:List<Book> = parseBooks(books)
        assertEquals(bookList[0].title, "Crime and Punishment")
        assertEquals(bookList[0].author, "F.M. Dostoevsky")
        assertEquals(bookList[0].publicationDate, 1866)
        assertEquals(bookList[1].title, "The Master and Margarita")
        assertEquals(bookList[1].author, "M.A. Bulgakov")
        assertEquals(bookList[1].publicationDate, 1966)
        assertEquals(bookList[2].title, "1984")
        assertEquals(bookList[2].author, "G. Orwell")
        assertEquals(bookList[2].publicationDate, 1949)
    }
}