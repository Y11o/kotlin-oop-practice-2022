package fifthLab.shapeCollector

import secondLab.colors.RGBA
import secondLab.`interface`.ColoredShape2d


class ShapeCollectorGeneric<T : ColoredShape2d>(_listOfShapes: List<T>) {
    private val listOfShapes: MutableList<T>

    init {
        listOfShapes = _listOfShapes.toMutableList()
    }

    fun addShape(shape: T) = listOfShapes.add(shape)

    fun findMinArea(): List<T> {
        if (listOfShapes.isNotEmpty()) {
            return listOfShapes.filter { it.shapeArea == listOfShapes.minOf { it1 -> it1.shapeArea } }
        }
        return emptyList()
    }

    fun findMaxArea(): List<T> {
        if (listOfShapes.isNotEmpty()) {
            return listOfShapes.filter { it.shapeArea == listOfShapes.maxOf { it1 -> it1.shapeArea } }
        }
        return emptyList()
    }

    fun areaSum(): Double {
        var areaSum = 0.0
        for (shape in listOfShapes) {
            areaSum += shape.shapeArea
        }
        return areaSum
    }

    fun borderColorFilter(border: RGBA): List<T> =
        listOfShapes.filter { it.border == border }

    fun fillColorFilter(fillColor: RGBA): List<T> =
        listOfShapes.filter { it.fillColor == fillColor }

    fun getListOfShapes(): List<T> = listOfShapes.toList()

    fun getListOfShapesSize(): Int = listOfShapes.size

    fun groupByBorderColor(): Map<RGBA, List<T>> = listOfShapes.groupBy { it.border }

    fun groupByFillColor(): Map<RGBA, List<T>> = listOfShapes.groupBy { it.fillColor }

    fun getByType(shape: ColoredShape2d): List<T> = listOfShapes.filter { it.javaClass == shape.javaClass }

    ///////// New functions

    fun addAll(listToAdd: List<T>) {
        listToAdd.forEach {
            listOfShapes.add(it)
        }
    }

    fun getSorted(shapesComparator: Comparator<T>): List<T> {
        val sortedShapeList = listOfShapes
        sortedShapeList.sortWith(shapesComparator)
        return sortedShapeList.toList()
    }
}