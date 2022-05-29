package secondLab.shapeCollector

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import secondLab.colors.RGBA
import secondLab.shapes.Circle
import secondLab.shapes.Rectangle
import secondLab.shapes.Square
import secondLab.shapes.Triangle

internal class ShapeCollectorTest {

    private val colorRed = RGBA(255.0, 0.0, 0.0, 1.0)
    private val colorGreen = RGBA(0.0, 255.0, 0.0, 1.0)
    private val colorBlue = RGBA(0.0, 0.0, 255.0, 1.0)

    private val circle = Circle(5.0, colorRed, colorBlue)
    private val anotherCircle = Circle(6.0, colorGreen, colorBlue)
    private val triangle = Triangle(4.0, 3.0, 5.0, colorRed, colorBlue)
    private val rectangle = Rectangle(3.0, 2.0, colorGreen, colorRed)
    private val square = Square(5.0, colorGreen, colorRed)
    private val anotherSquare = Square(7.0, colorRed, colorBlue)

    private val shapeCollection = ShapeCollector(listOf(circle, anotherCircle, triangle, rectangle))

    @Test
    fun addShape() {
        assertEquals(listOf(circle, anotherCircle, triangle, rectangle), shapeCollection.getListOfShapes())
        shapeCollection.addShape(square)
        shapeCollection.addShape(anotherSquare)
        assertEquals(
            listOf(circle, anotherCircle, triangle, rectangle, square, anotherSquare),
            shapeCollection.getListOfShapes()
        )
    }

    @Test

    fun findMinArea() {
        assertEquals(listOf(triangle, rectangle), shapeCollection.findMinArea())
    }

    @Test
    fun findMaxArea() {
        assertEquals(listOf(anotherCircle), shapeCollection.findMaxArea())
    }

    @Test
    fun areaSum() {
        assertEquals(203.63715186897738, shapeCollection.areaSum())
    }

    @Test
    fun borderColorFilter() {
        assertEquals(listOf(anotherCircle, rectangle), shapeCollection.borderColorFilter(colorGreen))
    }

    @Test
    fun fillColorFilter() {
        assertEquals(listOf(circle, anotherCircle, triangle), shapeCollection.fillColorFilter(colorBlue))
    }

    @Test
    fun getListOfShapes() {
        assertEquals(listOf(circle, anotherCircle, triangle, rectangle), shapeCollection.getListOfShapes())
    }

    @Test
    fun getListOfShapesSize() {
        assertEquals(4, shapeCollection.getListOfShapesSize())
    }

    @Test
    fun groupByBorderColor() {
        val anotherShapeCollection = ShapeCollector(listOf(circle, triangle))
        val map = mutableMapOf(
            colorRed to listOf(circle, triangle),
        )
        assertEquals(map.toString(), anotherShapeCollection.groupByBorderColor().toString())
    }

    @Test
    fun groupByFillColor() {
        val mapFill = mutableMapOf(
            colorGreen to listOf(circle, anotherCircle, triangle),
            colorRed to listOf(rectangle)
        )
        assertEquals(mapFill.toString(), shapeCollection.groupByFillColor().toString())
    }

    @Test
    fun getByType() {
        assertEquals(listOf(circle, anotherCircle), shapeCollection.getByType(circle))
    }
}