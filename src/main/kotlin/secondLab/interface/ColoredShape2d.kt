package secondLab.`interface`

import secondLab.colors.RGBA

interface ColoredShape2d:Shape2d {
    val border: RGBA
    val fillColor: RGBA
}