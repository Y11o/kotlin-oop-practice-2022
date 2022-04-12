package secondLab.shapes

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA

class Rectangle(
    _firstSide: Double,
    _secondSide: Double,
    _border: RGBA,
    _fillColor: RGBA
): ColoredShape2d {
    private val firstSide: Double
    private val secondSide: Double
    override val border: RGBA
    override val fillColor: RGBA
    override val shapeArea: Double
        get() = firstSide * secondSide
    init {
        border = _border.copy()
        fillColor = _fillColor.copy()
        if (_firstSide > 0 && _secondSide > 0) {
            firstSide = _firstSide
            secondSide = _secondSide
        }else {
            throw java.lang.IllegalArgumentException("Sides should be greater than 0")
        }
    }

    override fun toString(): String {
        return "This rectangle has sides: $firstSide, $secondSide; border color: $border; fill color: $fillColor; area: $shapeArea"
    }

}