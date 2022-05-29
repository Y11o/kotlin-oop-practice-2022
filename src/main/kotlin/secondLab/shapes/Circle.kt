package secondLab.shapes

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA
import kotlin.math.PI

data class Circle(
    val radius: Double,
    override val border: RGBA,
    override val fillColor: RGBA,
) : ColoredShape2d {

    override val shapeArea: Double
        get() = PI * radius * radius

    init {
        if (radius <= 0) {
            throw java.lang.IllegalArgumentException("Radius should be greater than 0")
        }
    }

    override fun toString(): String {
        return "[This circle has radius: $radius; border color: $border; fill color: $fillColor; area: $shapeArea]"
    }

}