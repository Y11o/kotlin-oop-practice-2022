package fifthLab

import secondLab.`interface`.ColoredShape2d
import java.util.Comparator

class Comparator<T : ColoredShape2d>() : Comparator<T> {
    override fun compare(first: T, second: T): Int {
        if (first.shapeArea == second.shapeArea)
            return 0
        return if (first.shapeArea > second.shapeArea)
            1
        else
            -1
    }
}