package fifthLab.shapeCollector

import org.junit.jupiter.api.Test
import secondLab.colors.RGBA
import secondLab.shapes.Circle
import secondLab.shapes.Triangle
import secondLab.shapes.Rectangle
import secondLab.shapes.Square
import fifthLab.Comparator

import org.junit.jupiter.api.Assertions.*
import secondLab.shapeCollector.ShapeCollector

internal class ShapeCollectorTest {

    @Test
    fun addAll() {
        val colorRed = RGBA(255.0, 0.0, 0.0, 1.0)
        val colorGreen = RGBA(0.0, 255.0, 0.0, 1.0)
        val colorBlue = RGBA(0.0, 0.0, 255.0, 1.0)

        val circle = Circle(5.0, colorRed, colorBlue,)
        val anotherCircle = Circle(6.0, colorGreen, colorBlue,)
        val triangle = Triangle(4.0, 3.0, 5.0, colorRed, colorBlue)
        val rectangle = Rectangle(3.0, 2.0, colorGreen, colorRed)
        val square = Square(5.0, colorGreen, colorRed)
        val anotherSquare = Square(7.0, colorRed, colorBlue)

        val shapeCollection = ShapeCollector(listOf(circle, anotherCircle, triangle))
        val shapeCollectionToTest = ShapeCollector(listOf())
        shapeCollectionToTest.addAll(shapeCollection.getListOfShapes())
        assertEquals(shapeCollection.getListOfShapes(), shapeCollectionToTest.getListOfShapes())

        val lastShapes = listOf(rectangle, square, anotherSquare)
        shapeCollectionToTest.addAll(lastShapes)
        val allShapes = shapeCollection.getListOfShapes() + lastShapes
        assertEquals(allShapes, shapeCollectionToTest.getListOfShapes())
    }

    @Test
    fun getSorted() {
        val colorRed = RGBA(255.0, 0.0, 0.0, 1.0)
        val colorGreen = RGBA(0.0, 255.0, 0.0, 1.0)
        val colorBlue = RGBA(0.0, 0.0, 255.0, 1.0)

        val triangle = Triangle(4.0, 3.0, 5.0, colorRed, colorBlue)
        val rectangle = Rectangle(4.0, 2.0, colorGreen, colorRed)
        val square = Square(5.0, colorGreen, colorRed)
        val anotherSquare = Square(7.0, colorRed, colorBlue)

        val shapeCollection = ShapeCollector(listOf(square, triangle, anotherSquare, rectangle))
        shapeCollection.getSorted(Comparator())
        val sortedShapes = listOf(triangle, rectangle, square, anotherSquare)
        assertEquals(sortedShapes, shapeCollection.getListOfShapes())
    }
}