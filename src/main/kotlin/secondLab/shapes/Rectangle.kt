package secondLab.shapes

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA

@kotlinx.serialization.Serializable
data class Rectangle(
    val firstSide: Double,
    val secondSide: Double,
    override val border: RGBA,
    override val fillColor: RGBA
) : ColoredShape2d {

    override val shapeArea: Double
        get() = firstSide * secondSide

    init {
        if (firstSide < 0 || secondSide < 0) {
            throw java.lang.IllegalArgumentException("Sides should be greater than 0")
        }
    }

    override fun toString(): String {
        return "[This rectangle has sides: $firstSide, $secondSide; border color: $border; fill color: $fillColor; area: $shapeArea]"
    }

}