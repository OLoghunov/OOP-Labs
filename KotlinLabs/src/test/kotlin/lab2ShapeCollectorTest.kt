import lab2.ShapeCollector
import lab2.shapes.Circle
import lab2.shapes.Rectangle
import lab2.shapes.Square
import java.awt.Color

internal class ShapeCollectorTest {

    @org.junit.jupiter.api.Test
    fun checkPush() {
        val col = ShapeCollector()
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 2.5))
        var res = false
        if (col.getCollection().isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun checkGetShapeWithMaxArea() {
        val col = ShapeCollector()
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 2.5))
        var res = false
        if (col.getShapeWithMaxArea() == col.getCollection()[1])
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun checkGetShapeWithMinArea() {
        val col = ShapeCollector()
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 2.5))
        var res = false
        if (col.getShapeWithMinArea() == col.getCollection()[0])
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun checkGetAreaOfCollection() {
        val col = ShapeCollector()
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 1.0))
        var res = false
        if (col.getAreaOfCollection() == 6.0)
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun checkFindAllByBorderColor() {
        val col = ShapeCollector()
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 1.0))
        var res = false
        if (col.findAllByBorderColor(Color.RED).isNotEmpty() && col.findAllByBorderColor(Color.RED)[0].borderColor == Color.RED
            && col.findAllByBorderColor(Color.RED)[1].borderColor == Color.RED)
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun checkFindAllByFillColor() {
        val col = ShapeCollector()
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 1.0))
        var res = false
        if (col.findAllByFillColor(Color.BLUE).isNotEmpty() && col.findAllByFillColor(Color.BLUE)[0].fillColor == Color.BLUE
            && col.findAllByFillColor(Color.BLUE)[1].fillColor == Color.BLUE)
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun checkGetCollectionGroupedByBorderColor() {
        val col = ShapeCollector()
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 1.0))
        col.pushShape(Rectangle(Color.BLUE, Color.RED, 2.0, 1.0))
        var res = false
        if (col.getCollectionGroupedByBorderColor()[Color.RED]!!.isNotEmpty() &&
            col.getCollectionGroupedByBorderColor()[Color.BLUE]!!.isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun checkGetCollectionGroupedByFillColor() {
        val col = ShapeCollector()
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 1.0))
        col.pushShape(Rectangle(Color.BLUE, Color.RED, 2.0, 1.0))
        var res = false
        if (col.getCollectionGroupedByFillColor()[Color.RED]!!.isNotEmpty() &&
            col.getCollectionGroupedByFillColor()[Color.BLUE]!!.isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun checkFindShapesByType() {
        val col = ShapeCollector()
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 1.0))
        col.pushShape(Rectangle(Color.BLUE, Color.RED, 2.0, 1.0))
        var res = false
        if (col.findShapesByType(Square::class.java).isNotEmpty() &&
            col.findShapesByType(Rectangle::class.java).isNotEmpty() &&
            col.findShapesByType(Circle::class.java).isEmpty())
            res = true
        assert(res)
    }
}