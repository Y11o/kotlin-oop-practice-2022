package sixthLab

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import secondLab.colors.RGBA
import secondLab.shapeCollector.ShapeCollector
import secondLab.shapes.Circle
import secondLab.shapes.Rectangle
import secondLab.shapes.Square
import secondLab.shapes.Triangle


internal class SerializerTest {

    @Test
    fun decodeShapes() {
        val stringShapes = "[\n" +
                "    {\n" +
                "        \"type\": \"secondLab.shapes.Triangle\",\n" +
                "        \"firstSide\": 4.0,\n" +
                "        \"secondSide\": 3.0,\n" +
                "        \"thirdSide\": 5.0,\n" +
                "        \"border\": {\n" +
                "            \"red\": 255.0,\n" +
                "            \"green\": 0.0,\n" +
                "            \"blue\": 0.0,\n" +
                "            \"alpha\": 1.0\n" +
                "        },\n" +
                "        \"fillColor\": {\n" +
                "            \"red\": 0.0,\n" +
                "            \"green\": 0.0,\n" +
                "            \"blue\": 255.0,\n" +
                "            \"alpha\": 1.0\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"type\": \"secondLab.shapes.Rectangle\",\n" +
                "        \"firstSide\": 3.0,\n" +
                "        \"secondSide\": 2.0,\n" +
                "        \"border\": {\n" +
                "            \"red\": 0.0,\n" +
                "            \"green\": 255.0,\n" +
                "            \"blue\": 0.0,\n" +
                "            \"alpha\": 1.0\n" +
                "        },\n" +
                "        \"fillColor\": {\n" +
                "            \"red\": 255.0,\n" +
                "            \"green\": 0.0,\n" +
                "            \"blue\": 0.0,\n" +
                "            \"alpha\": 1.0\n" +
                "        }\n" +
                "    }\n" +
                "]"

        val colorRed = RGBA(255.0, 0.0, 0.0, 1.0)
        val colorGreen = RGBA(0.0, 255.0, 0.0, 1.0)
        val colorBlue = RGBA(0.0, 0.0, 255.0, 1.0)
        val triangle = Triangle(4.0, 3.0, 5.0, colorRed, colorBlue)
        val rectangle = Rectangle(3.0, 2.0, colorGreen, colorRed)
        val shapes = ShapeCollector(listOf(triangle, rectangle))

        assertEquals(Serializer().decodeShapes(stringShapes), shapes.getListOfShapes())
    }

    @Test
    fun encodeShapes() {
        val stringShapes = "[\n" +
                "    {\n" +
                "        \"type\": \"secondLab.shapes.Circle\",\n" +
                "        \"radius\": 5.0,\n" +
                "        \"border\": {\n" +
                "            \"red\": 255.0,\n" +
                "            \"green\": 0.0,\n" +
                "            \"blue\": 0.0,\n" +
                "            \"alpha\": 1.0\n" +
                "        },\n" +
                "        \"fillColor\": {\n" +
                "            \"red\": 0.0,\n" +
                "            \"green\": 0.0,\n" +
                "            \"blue\": 255.0,\n" +
                "            \"alpha\": 1.0\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"type\": \"secondLab.shapes.Square\",\n" +
                "        \"side\": 5.0,\n" +
                "        \"border\": {\n" +
                "            \"red\": 0.0,\n" +
                "            \"green\": 255.0,\n" +
                "            \"blue\": 0.0,\n" +
                "            \"alpha\": 1.0\n" +
                "        },\n" +
                "        \"fillColor\": {\n" +
                "            \"red\": 255.0,\n" +
                "            \"green\": 0.0,\n" +
                "            \"blue\": 0.0,\n" +
                "            \"alpha\": 1.0\n" +
                "        }\n" +
                "    }\n" +
                "]"

        val colorRed = RGBA(255.0, 0.0, 0.0, 1.0)
        val colorGreen = RGBA(0.0, 255.0, 0.0, 1.0)
        val colorBlue = RGBA(0.0, 0.0, 255.0, 1.0)
        val circle = Circle(5.0, colorRed, colorBlue)
        val square = Square(5.0, colorGreen, colorRed)
        val shapes = ShapeCollector(listOf(circle, square))

        assertEquals(stringShapes, Serializer().encodeShapes(shapes.getListOfShapes()))
    }
}