package secondLab.shapeCollector

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA

class ShapeCollector<T: ColoredShape2d>(_listOfShapes: List<T>) {
    private val listOfShapes: MutableList<T>

    init {
        listOfShapes = _listOfShapes.toMutableList()
    }

    fun addShape(shape: T) = listOfShapes.add(shape)

    fun findMinArea(): List<T> {
        return listOfShapes.filter { it.shapeArea == listOfShapes.minOf { it1 -> it1.shapeArea } }
    }

    fun findMaxArea(): List<T> {
        return listOfShapes.filter { it.shapeArea == listOfShapes.maxOf { it1 -> it1.shapeArea } }
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

    fun getByType(clazz: Class<out T>): List<T> = listOfShapes.filterIsInstance(clazz)

    ///////// New functions

    fun addAll(listToAdd: List<T>) {
        listToAdd.forEach {
            listOfShapes.add(it)
        }
    }

    fun getSorted(shapesComparator: Comparator<in T>): List<T> {
        val sortedShapeList = listOfShapes
        sortedShapeList.sortWith(shapesComparator)
        return sortedShapeList.toList()
    }
}