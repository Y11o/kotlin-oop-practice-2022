package secondLab.shapeCollector

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.w3c.dom.css.RGBColor
import secondLab.colors.RGBA
import secondLab.`interface`.ColoredShape2d
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
        val map = mutableMapOf(
            RGBA(255.0, 0.0, 0.0, 1.0) to listOf(circle, triangle),
            RGBA(0.0, 0.0, 255.0, 1.0) to listOf(anotherCircle, rectangle)
        )
        assertEquals(map.toString(), shapeCollection.groupByBorderColor())
    }

    @Test
    fun groupByFillColor() {
        val mapFill = mutableMapOf(
            RGBA(0.0, 0.0, 255.0, 1.0) to listOf(circle, anotherCircle, triangle),
            RGBA(255.0, 0.0, 0.0, 1.0) to listOf(rectangle)
        )
        assertEquals(mapFill.toString(), shapeCollection.groupByFillColor())
    }

    @Test
    fun groupByType() {
        val map = mutableMapOf(
            javaClass to listOf(circle, anotherCircle)
        )
        assertEquals(map.toString(), shapeCollection.getByType(circle))
    }
}