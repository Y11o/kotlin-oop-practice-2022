package secondLab.colors

data class RGBA(
    val red: Double,
    val green: Double,
    val blue: Double,
    val alpha: Double
) {
    init {
        if (red < 0.0 || red > 255.0) throw error("Red color should be initialised in range 0.0..255.0")
        if (green < 0.0 || green > 255.0) throw error("Green color should be initialised in range 0.0..255.0")
        if (blue < 0.0 || blue > 255.0) throw error("Blue color should be initialised in range 0.0..255.0")
        if (alpha < 0.0 || alpha > 1.0) throw error("Alpha should be initialised in range 0.0..1.0")
    }

    override fun toString(): String {
        return "R $red, G $blue, B $green, Alpha $alpha"
    }
}
