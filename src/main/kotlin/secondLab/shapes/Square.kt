package secondLab.shapes

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA

@kotlinx.serialization.Serializable
data class Square(
    val side: Double,
    override val border: RGBA,
    override val fillColor: RGBA
) : ColoredShape2d {

    override val shapeArea: Double
        get() = side * side

    init {
        if (side < 0) {
            throw java.lang.IllegalArgumentException("Side should be greater than 0")
        }
    }

    override fun toString(): String {
        return "[This square has side: $side; border color: $border; fill color: $fillColor; area: $shapeArea]"
    }

}