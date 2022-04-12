package secondLab

import secondLab.colors.RGBA
import secondLab.shapes.Circle
import secondLab.shapes.Triangle
import secondLab.shapes.Rectangle
import secondLab.shapes.Square
import secondLab.shapeCollector.ShapeCollector

fun main(){
    val colorRed = RGBA(255.0, 0.0, 0.0, 1.0)
    val colorGreen = RGBA(0.0, 255.0, 0.0, 1.0)
    val colorBlue = RGBA(0.0, 0.0, 255.0, 1.0)

    val circle = Circle(5.0,colorRed, colorBlue)


}