package secondLab.shapes

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA
import kotlin.math.sqrt

@kotlinx.serialization.Serializable
data class Triangle(
    val firstSide: Double,
    val secondSide: Double,
    val thirdSide: Double,
    override val border: RGBA,
    override val fillColor: RGBA
) : ColoredShape2d {

    override val shapeArea: Double
        get() {
            val halfOfSum = (firstSide + secondSide + thirdSide) / 2
            return sqrt(halfOfSum * (halfOfSum - firstSide) * (halfOfSum - secondSide) * (halfOfSum - thirdSide))
        }

    init {
        if (firstSide < 0 || secondSide < 0 || thirdSide < 0 || firstSide + secondSide < thirdSide ||
            secondSide + thirdSide < firstSide || firstSide + thirdSide < secondSide
        ) {
            throw java.lang.IllegalArgumentException("Sides should be greater than 0")
        }
    }

    override fun toString(): String {
        return "[This triangle has sides: $firstSide, $secondSide, $thirdSide; border color: $border; fill color: $fillColor; area: $shapeArea]"
    }

}