package lab6
import kotlinx.serialization.Serializable


@Serializable
class ShapeCollector {
    private val listOfShapes = arrayListOf<SerShape>()
    fun pushShape(shape : SerShape) {
        listOfShapes.add(shape)
    }

     fun getShapeWithMaxArea() : SerShape? {
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

     fun getShapeWithMinArea() : SerShape? {
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

     fun getAreaOfCollection() : Double {
         var areaOfCollection = 0.0
         listOfShapes.forEach { shape ->
             areaOfCollection += shape.calcArea()
         }
         return areaOfCollection
     }

     fun findAllByBorderColor(borderColor : SerColor) : List<SerShape> {
         val resultArray = arrayListOf<SerShape>()
         listOfShapes.forEach { shape ->
             if (shape.borderColor == borderColor)
                 resultArray.add(shape)
         }
         return resultArray
     }

     fun findAllByFillColor(fillColor : SerColor) : List<SerShape> {
         val resultArray = arrayListOf<SerShape>()
         listOfShapes.forEach { shape ->
             if (shape.fillColor == fillColor)
                 resultArray.add(shape)
         }
         return resultArray
     }

     fun getCollection() : List<SerShape> {
         return listOfShapes
     }

     fun getCollectionGroupedByBorderColor() : Map<SerColor, List<SerShape>> {
         if (listOfShapes.isEmpty())
             return mapOf()
         val sortedList = mutableMapOf<SerColor, MutableList<SerShape>>()
         val collectionColors = mutableSetOf<SerColor>()

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

     fun getCollectionGroupedByFillColor() : Map<SerColor, List<SerShape>> {
         if (listOfShapes.isEmpty())
             return mapOf()
         val sortedList = mutableMapOf<SerColor, MutableList<SerShape>>()
         val collectionColors = mutableSetOf<SerColor>()

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

    fun findShapesByType(reqShape: Class<out SerShape>) : List<SerShape> {
        val resultList = arrayListOf<SerShape>()
        listOfShapes.forEach { shape ->
            if (shape.javaClass == reqShape) resultList.add(shape)
        }
        return resultList
    }
}