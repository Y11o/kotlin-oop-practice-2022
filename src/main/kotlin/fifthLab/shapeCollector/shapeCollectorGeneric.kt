package fifthLab.shapeCollector

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA

class ShapeCollectorGeneric<out T: ColoredShape2d>(_listOfShapes: List<T>) {
    private val listOfShapes: MutableList<T>

    init {
        listOfShapes = _listOfShapes.toMutableList()
    }

    fun addShape (shape: @UnsafeVariance T) = listOfShapes.add(shape)

    fun findMinArea (): List<ColoredShape2d>{
        if (listOfShapes.isNotEmpty()){
            return listOfShapes.filter { it.shapeArea == listOfShapes.minOf { it1 -> it1.shapeArea } }
        }
        return emptyList()
    }

    fun findMaxArea(): List<ColoredShape2d>{
        if (listOfShapes.isNotEmpty()){
            return listOfShapes.filter { it.shapeArea == listOfShapes.maxOf { it1 -> it1.shapeArea } }
        }
        return emptyList()
    }

    fun areaSum(): Double{
        var areaSum = 0.0
        for (shape in listOfShapes){
            areaSum += shape.shapeArea
        }
        return areaSum
    }

    fun borderColorFilter(border: RGBA): List<ColoredShape2d> =
        listOfShapes.filter { it.border == border }

    fun fillColorFilter(fillColor: RGBA): List<ColoredShape2d> =
        listOfShapes.filter { it.fillColor == fillColor }

    fun getListOfShapes(): List<ColoredShape2d> = listOfShapes.toList()

    fun getListOfShapesSize(): Int = listOfShapes.size

    fun groupByBorderColor(): Map<RGBA, List<ColoredShape2d>> = listOfShapes.groupBy { it.border }

    fun groupByFillColor(): Map<RGBA, List<ColoredShape2d>> = listOfShapes.groupBy { it.fillColor }

    fun groupByType(): Map<Class<Any>, List<ColoredShape2d>> = listOfShapes.groupBy { it.javaClass }

    ///////// New functions

    fun addAll(listToAdd: List<@UnsafeVariance T>) {
        listToAdd.forEach {
            listOfShapes.add(it)
        }
    }

    fun getSorted(shapesComparator: Comparator<@UnsafeVariance T>): List<T> {
        val sortedShapeList = listOfShapes
        sortedShapeList.sortWith(shapesComparator)
        return sortedShapeList.toList()
    }
}