package sixthLab

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*
import secondLab.`interface`.ColoredShape2d
import secondLab.shapes.Circle
import secondLab.shapes.Rectangle
import secondLab.shapes.Square
import secondLab.shapes.Triangle


open class Serializer() {
    private val json = Json {
        prettyPrint = true

        serializersModule = SerializersModule {
            polymorphic(ColoredShape2d::class) {
                subclass(Circle::class)
                subclass(Triangle::class)
                subclass(Rectangle::class)
                subclass(Square::class)
            }
        }
    }

    fun decodeShapes(shapesString: String): List<ColoredShape2d> = json.decodeFromString(shapesString)

    fun encodeShapes(shapesList: List<ColoredShape2d>): String = json.encodeToString(shapesList)
}

