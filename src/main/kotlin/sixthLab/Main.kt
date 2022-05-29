package sixthLab

import secondLab.colors.RGBA
import secondLab.shapeCollector.ShapeCollector
import secondLab.shapes.Circle
import secondLab.shapes.Square
import secondLab.shapes.Rectangle
import secondLab.shapes.Triangle
import java.io.File


fun main() {
    println("Input the file name (full path required)")   //Input file located in ..\kotlin-oop-practice-2022\src\main\resources\InputShapes.txt
    val fileName: String = readln()
    if (!File(fileName).exists()) throw IllegalAccessError("Incorrect file name")

    val shapesFromFile = Serializer().decodeShapes(File(fileName).readText()).toMutableList()
    val shapes = ShapeCollector(shapesFromFile)

    val colorRed = RGBA(255.0, 0.0, 0.0, 1.0)
    val colorGreen = RGBA(0.0, 255.0, 0.0, 1.0)
    val colorBlue = RGBA(0.0, 0.0, 255.0, 1.0)
    val circle = Circle(5.0, colorRed, colorBlue)
    val square = Square(5.0, colorGreen, colorRed)
    println("Shapes read from file: ${shapes.getListOfShapes()}")

    shapes.addShape(circle)
    shapes.addShape(square)

    println("Changed shape list, that will be printed in file: ${shapes.getListOfShapes()}")
    println("Type the output file (full path required)")

    val outFileName: String = readln()
    File(outFileName).writeText(Serializer().encodeShapes(shapes.getListOfShapes()))
}
