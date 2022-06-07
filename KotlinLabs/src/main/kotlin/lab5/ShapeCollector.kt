package lab5

import lab5.interfaces.ColoredShape
import java.awt.Color

class ShapeCollector<T : ColoredShape> {
    private val listOfShapes = arrayListOf<T>()
    private var size = 0
    fun pushShape(shape : T) {
        listOfShapes.add(shape)
        size++
    }

    fun getShapeWithMaxArea() : ColoredShape {
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

    fun getShapeWithMinArea() : ColoredShape {
        require(size > 0)
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

    fun getAreaOfCollection() : Double {
        var areaOfCollection = 0.0
        listOfShapes.forEach { shape ->
            areaOfCollection += shape.calcArea()
        }
        return areaOfCollection
    }

    fun findAllByBorderColor(borderColor : Color) : List<ColoredShape> {
        val resultArray = arrayListOf<ColoredShape>()
        listOfShapes.forEach { shape ->
            if (shape.borderColor == borderColor)
                resultArray.add(shape)
        }
        return resultArray
    }

    fun findAllByFillColor(fillColor : Color) : List<ColoredShape> {
        val resultArray = arrayListOf<ColoredShape>()
        listOfShapes.forEach { shape ->
            if (shape.fillColor == fillColor)
                resultArray.add(shape)
        }
        return resultArray
    }

    fun getCollection() : List<ColoredShape> {
        return listOfShapes
    }

    fun getSizeOfCollection() : Int {
        return size
    }

    fun getCollectionGroupedByBorderColor() : Map<Color, List<ColoredShape>> {
        require(size > 0)
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

    fun getCollectionGroupedByFillColor() : Map<Color, List<ColoredShape>> {
        require(size > 0)
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

    fun addAll(col : Collection<out T>) {
        col.forEach {
            this.pushShape(it)
        }
    }

    fun getSorted(com : Comparator<in T>) : List<T> {
        return listOfShapes.sortedWith(com)
    }
}