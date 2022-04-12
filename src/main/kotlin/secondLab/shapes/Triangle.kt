package secondLab.shapes

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA
import kotlin.math.sqrt

class Triangle(
    _firstSide: Double,
    _secondSide: Double,
    _thirdSide: Double,
    _border: RGBA,
    _fillColor: RGBA
):ColoredShape2d {
    private val firstSide: Double
    private val secondSide: Double
    private val thirdSide: Double
    override val border: RGBA
    override val fillColor: RGBA
    override val shapeArea: Double
        get(){
            val halfOfSum = (firstSide + secondSide + thirdSide) / 2
            return sqrt(halfOfSum * (halfOfSum - firstSide) * (halfOfSum - secondSide) * (halfOfSum - thirdSide))
        }
    init {
        border = _border.copy()
        fillColor = _fillColor.copy()
        if (_firstSide > 0 && _secondSide > 0 && _thirdSide > 0) {
            firstSide = _firstSide
            secondSide = _secondSide
            thirdSide = _thirdSide
        }else {
            throw java.lang.IllegalArgumentException("Sides should be greater than 0")
        }
    }

    override fun toString(): String {
        return "This triangle has sides: $firstSide, $secondSide, $thirdSide; border color: $border; fill color: $fillColor; area: $shapeArea"
    }

}