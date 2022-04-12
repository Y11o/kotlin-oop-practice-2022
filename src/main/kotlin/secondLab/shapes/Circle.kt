package secondLab.shapes

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA
import kotlin.math.PI

class Circle(
    _radius: Double,
    _border: RGBA,
    _fillColor: RGBA
):ColoredShape2d {
    private val radius: Double
    override val border: RGBA
    override val fillColor: RGBA
    override val shapeArea: Double
        get() = PI * radius * radius
    init {
        border = _border.copy()
        fillColor = _fillColor.copy()
        if (_radius > 0) {radius = _radius}else {
            throw java.lang.IllegalArgumentException("Radius should be greater than 0")
        }
    }

    override fun toString(): String {
        return "This circle has radius: $radius; border color: $border; fill color: $fillColor; area: $shapeArea"
    }

}