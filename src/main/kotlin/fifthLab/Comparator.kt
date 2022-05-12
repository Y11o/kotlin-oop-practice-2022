package fifthLab

import secondLab.`interface`.ColoredShape2d
import java.util.Comparator

class Comparator : Comparator<ColoredShape2d> {
    override fun compare(first: ColoredShape2d, second: ColoredShape2d): Int {
        if (first.shapeArea == second.shapeArea)
            return 0
        return if (first.shapeArea > second.shapeArea)
            1
        else
            -1
    }
}