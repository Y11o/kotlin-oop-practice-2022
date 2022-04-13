package secondLab.shapes

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA

class Square(
    _side: Double,
    _border: RGBA,
    _fillColor: RGBA
): ColoredShape2d {
    private val side: Double
    override val border: RGBA
    override val fillColor: RGBA
    override val shapeArea: Double
        get() = side * side
    init {
        border = _border.copy()
        fillColor = _fillColor.copy()
        if (_side > 0) {
            side = _side
        }else {
            throw java.lang.IllegalArgumentException("Side should be greater than 0")
        }
    }

    override fun toString(): String {
        return "[This square has side: $side; border color: $border; fill color: $fillColor; area: $shapeArea]"
    }

}