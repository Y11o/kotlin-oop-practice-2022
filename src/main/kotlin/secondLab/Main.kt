package secondLab

import secondLab.colors.RGBA
import secondLab.shapes.Circle
import secondLab.shapes.Triangle
import secondLab.shapes.Rectangle
import secondLab.shapes.Square
import secondLab.shapeCollector.ShapeCollector

fun main() {
    val colorRed = RGBA(255.0, 0.0, 0.0, 1.0)
    val colorGreen = RGBA(0.0, 255.0, 0.0, 1.0)
    val colorBlue = RGBA(0.0, 0.0, 255.0, 1.0)

    val circle = Circle(5.0, colorRed, colorBlue,)
    val anotherCircle = Circle(6.0, colorGreen, colorBlue,)
    val triangle = Triangle(4.0, 3.0, 5.0, colorRed, colorBlue)
    val rectangle = Rectangle(3.0, 2.0, colorGreen, colorRed)
    val square = Square(5.0, colorGreen, colorRed)
    val anotherSquare = Square(7.0, colorRed, colorBlue)

    val shapeCollection = ShapeCollector(listOf(circle, anotherCircle, triangle, rectangle))
    println("Shape collection without Squares: ${shapeCollection.getListOfShapes()}")
    shapeCollection.addShape(square)
    shapeCollection.addShape(anotherSquare)
    println("Shape collection with all shapes: ${shapeCollection.getListOfShapes()}")
    println("Collection's size: ${shapeCollection.getListOfShapesSize()}")
    println("Shape with minimal area: ${shapeCollection.findMinArea()}")
    println("Shape with maximal area: ${shapeCollection.findMaxArea()}")
    println("Summary of shape's areas: ${shapeCollection.areaSum()}")
    println("Shapes with red borders: ${shapeCollection.borderColorFilter(colorRed)}")
    println("Shapes with blue filled: ${shapeCollection.fillColorFilter(colorBlue)}")
    println("Shapes grouped by border color: ${shapeCollection.groupByBorderColor()}")
    println("Shapes grouped by fill color: ${shapeCollection.groupByFillColor()}")
    println("Shapes groped by type: ${shapeCollection.getByType(Circle::class.java)}")

}