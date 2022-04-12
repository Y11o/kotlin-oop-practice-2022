package secondLab.shapeCollector

import secondLab.`interface`.ColoredShape2d
import secondLab.colors.RGBA

class ShapeCollector(_listOfShapes: List<ColoredShape2d>) {
    private val listOfShapes: MutableList<ColoredShape2d>

    init {
        listOfShapes = _listOfShapes.toMutableList()
    }

    fun addShape (shape: ColoredShape2d) = listOfShapes.add(shape)

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
}