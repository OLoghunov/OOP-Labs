package lab5

import lab2.interfaces.ColoredShape
import java.awt.Color

class ShapeCollector<T : ColoredShape> : lab2.ShapeCollector() {
    private val listOfShapes = arrayListOf<T>()

    internal fun pushShape(shape : T) {
        listOfShapes.add(shape)
    }

    override fun getShapeWithMaxArea() : T? {
        if (listOfShapes.isEmpty())
            return null
        var maxArea = listOfShapes[0].calcArea()
        var shapeWithMaxArea = listOfShapes[0]
        listOfShapes.forEach { shape ->
            if (shape.calcArea() > maxArea) {
                maxArea = shape.calcArea()
                shapeWithMaxArea = shape
            }
        }
        return shapeWithMaxArea
    }

    override fun getShapeWithMinArea() : T? {
        if (listOfShapes.isEmpty())
            return null
        var minArea = listOfShapes[0].calcArea()
        var shapeWithMinArea = listOfShapes[0]
        listOfShapes.forEach { shape ->
            if (shape.calcArea() < minArea) {
                minArea = shape.calcArea()
                shapeWithMinArea = shape
            }
        }
        return shapeWithMinArea
    }

    override fun findAllByBorderColor(borderColor : Color) : List<T> {
        val resultArray = arrayListOf<T>()
        listOfShapes.forEach { shape ->
            if (shape.borderColor == borderColor)
                resultArray.add(shape)
        }
        return resultArray
    }

    override fun findAllByFillColor(fillColor : Color) : List<T> {
        val resultArray = arrayListOf<T>()
        listOfShapes.forEach { shape ->
            if (shape.fillColor == fillColor)
                resultArray.add(shape)
        }
        return resultArray
    }

    override fun getCollection() : List<T> {
        return listOfShapes
    }

    override fun getCollectionGroupedByBorderColor() : Map<Color, List<T>> {
        if (listOfShapes.isEmpty())
            return mapOf()
        val sortedList = mutableMapOf<Color, MutableList<T>>()
        val collectionColors = mutableSetOf<Color>()

        listOfShapes.forEach { shape ->
            if (collectionColors.all { it != shape.borderColor })
                collectionColors.add(shape.borderColor)
        }

        collectionColors.forEach { color ->
            sortedList[color] = mutableListOf()
        }

        sortedList.keys.forEach { color ->
            listOfShapes.forEach { shape ->
                if (shape.borderColor == color)
                    sortedList[color]?.add(shape)
            }
        }
        return sortedList
    }

    override fun getCollectionGroupedByFillColor() : Map<Color, List<T>> {
        if (listOfShapes.isEmpty())
            return mapOf()
        val sortedList = mutableMapOf<Color, MutableList<T>>()
        val collectionColors = mutableSetOf<Color>()

        listOfShapes.forEach { shape ->
            if (collectionColors.all { it != shape.fillColor })
                collectionColors.add(shape.fillColor)
        }

        collectionColors.forEach { color ->
            sortedList[color] = mutableListOf()
        }

        sortedList.keys.forEach { color ->
            listOfShapes.forEach { shape ->
                if (shape.fillColor == color)
                    sortedList[color]?.add(shape)
            }
        }
        return sortedList
    }

    internal fun findShapesByType(reqShape: Class<out T>) : List<T> {
        val resultList = arrayListOf<T>()
        listOfShapes.forEach { shape ->
            if (shape.javaClass == reqShape) resultList.add(shape)
        }
        return resultList
    }

    fun addAll(col : Collection<T>) {
        col.forEach {
            this.pushShape(it)
        }
    }

    fun getSorted(com : Comparator<in T>) : List<T> {
        return listOfShapes.sortedWith(com)
    }
}