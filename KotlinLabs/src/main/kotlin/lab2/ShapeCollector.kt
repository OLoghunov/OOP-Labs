package lab2

import lab2.interfaces.ColoredShape
import java.awt.Color

open class ShapeCollector {
    private val listOfShapes = arrayListOf<ColoredShape>()
    fun pushShape(shape : ColoredShape) {
        listOfShapes.add(shape)
    }

    open fun getShapeWithMaxArea() : ColoredShape? {
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

    open fun getShapeWithMinArea() : ColoredShape? {
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

    open fun getAreaOfCollection() : Double {
        var areaOfCollection = 0.0
        listOfShapes.forEach { shape ->
            areaOfCollection += shape.calcArea()
        }
        return areaOfCollection
    }

    open fun findAllByBorderColor(borderColor : Color) : List<ColoredShape> {
        val resultArray = arrayListOf<ColoredShape>()
        listOfShapes.forEach { shape ->
            if (shape.borderColor == borderColor)
                resultArray.add(shape)
        }
        return resultArray
    }

    open fun findAllByFillColor(fillColor : Color) : List<ColoredShape> {
        val resultArray = arrayListOf<ColoredShape>()
        listOfShapes.forEach { shape ->
            if (shape.fillColor == fillColor)
                resultArray.add(shape)
        }
        return resultArray
    }

    open fun getCollection() : List<ColoredShape> {
        return listOfShapes
    }

    open fun getCollectionGroupedByBorderColor() : Map<Color, List<ColoredShape>> {
        if (listOfShapes.isEmpty())
            return mapOf()
        val sortedList = mutableMapOf<Color, MutableList<ColoredShape>>()
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

    open fun getCollectionGroupedByFillColor() : Map<Color, List<ColoredShape>> {
        if (listOfShapes.isEmpty())
            return mapOf()
        val sortedList = mutableMapOf<Color, MutableList<ColoredShape>>()
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

     fun findShapesByType(reqShape: Class<out ColoredShape>) : List<ColoredShape> {
        val resultList = arrayListOf<ColoredShape>()
        listOfShapes.forEach { shape ->
            if (shape.javaClass == reqShape) resultList.add(shape)
        }
        return resultList
    }
}