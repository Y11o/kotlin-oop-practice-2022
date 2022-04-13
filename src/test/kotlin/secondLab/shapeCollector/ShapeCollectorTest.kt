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

    private val circle = Circle(5.0,colorRed, colorBlue)
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
        assertEquals(listOf(circle, anotherCircle, triangle, rectangle, square, anotherSquare), shapeCollection.getListOfShapes())
    }

    @Test
    fun findMinArea() {
        assertEquals("[[This triangle has sides: 4.0, 3.0, 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 6.0], [This rectangle has sides: 3.0, 2.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 255.0, G 0.0, B 0.0, Alpha 1.0; area: 6.0]]", "${shapeCollection.findMinArea()}")
    }

    @Test
    fun findMaxArea() {
        assertEquals("[[This circle has radius: 6.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 113.09733552923255]]", "${shapeCollection.findMaxArea()}")
    }

    @Test
    fun areaSum() {
        assertEquals(203.63715186897738, shapeCollection.areaSum())
    }

    @Test
    fun borderColorFilter() {
       assertEquals("[[This circle has radius: 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 78.53981633974483], [This triangle has sides: 4.0, 3.0, 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 6.0]]", "${shapeCollection.borderColorFilter(colorRed)}")
    }

    @Test
    fun fillColorFilter() {
        assertEquals("[[This circle has radius: 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 78.53981633974483], [This circle has radius: 6.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 113.09733552923255], [This triangle has sides: 4.0, 3.0, 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 6.0]]", "${shapeCollection.fillColorFilter(colorBlue)}")
    }

    @Test
    fun getListOfShapes() {
        assertEquals("[[This circle has radius: 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 78.53981633974483], [This circle has radius: 6.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 113.09733552923255], [This triangle has sides: 4.0, 3.0, 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 6.0], [This rectangle has sides: 3.0, 2.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 255.0, G 0.0, B 0.0, Alpha 1.0; area: 6.0]]", "${shapeCollection.getListOfShapes()}")
    }

    @Test
    fun getListOfShapesSize() {
        assertEquals(4, shapeCollection.getListOfShapesSize())
    }

    @Test
    fun groupByBorderColor() {
        assertEquals("{R 255.0, G 0.0, B 0.0, Alpha 1.0=[[This circle has radius: 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 78.53981633974483], [This triangle has sides: 4.0, 3.0, 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 6.0]], R 0.0, G 0.0, B 255.0, Alpha 1.0=[[This circle has radius: 6.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 113.09733552923255], [This rectangle has sides: 3.0, 2.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 255.0, G 0.0, B 0.0, Alpha 1.0; area: 6.0]]}", "${shapeCollection.groupByBorderColor()}")
    }

    @Test
    fun groupByFillColor() {
        assertEquals("{R 0.0, G 255.0, B 0.0, Alpha 1.0=[[This circle has radius: 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 78.53981633974483], [This circle has radius: 6.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 113.09733552923255], [This triangle has sides: 4.0, 3.0, 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 6.0]], R 255.0, G 0.0, B 0.0, Alpha 1.0=[[This rectangle has sides: 3.0, 2.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 255.0, G 0.0, B 0.0, Alpha 1.0; area: 6.0]]}", "${shapeCollection.groupByFillColor()}")
    }

    @Test
    fun groupByType() {
        assertEquals("{class secondLab.shapes.Circle=[[This circle has radius: 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 78.53981633974483], [This circle has radius: 6.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 113.09733552923255]], class secondLab.shapes.Triangle=[[This triangle has sides: 4.0, 3.0, 5.0; border color: R 255.0, G 0.0, B 0.0, Alpha 1.0; fill color: R 0.0, G 255.0, B 0.0, Alpha 1.0; area: 6.0]], class secondLab.shapes.Rectangle=[[This rectangle has sides: 3.0, 2.0; border color: R 0.0, G 0.0, B 255.0, Alpha 1.0; fill color: R 255.0, G 0.0, B 0.0, Alpha 1.0; area: 6.0]]}", "${shapeCollection.groupByType()}")
    }
}