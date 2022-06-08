import lab5.ShapeCollector
import lab2.interfaces.ColoredShape
import lab2.shapes.Circle
import lab2.shapes.Rectangle
import lab2.shapes.Square
import lab2.shapes.Triangle
import java.awt.Color
internal class Lab5Tests {

    @org.junit.jupiter.api.Test
    fun addAllTest() {
        val col = ShapeCollector<ColoredShape>()
        val lst = listOf<ColoredShape>(Square(Color.RED, Color.BLUE, 2.0), Square(Color.RED, Color.BLUE, 2.0))
        col.addAll(lst)
        assert(col.getCollection().isNotEmpty())
    }

    @org.junit.jupiter.api.Test
    fun getSortedTest() {
        val col = ShapeCollector<ColoredShape>()
        class AreaComparator: Comparator<ColoredShape> {
            override fun compare(o1: ColoredShape?, o2: ColoredShape?): Int {
                if(o1 == null || o2 == null)
                    return 0
                return o1.calcArea().compareTo(o2.calcArea())
            }
        }
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Circle(Color.WHITE, Color.BLACK, 1.0))
        col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 2.5))
        col.pushShape(Triangle(Color.BLUE, Color.BLACK, 3.5, 1.0))
        col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
        col.pushShape(Circle(Color.WHITE, Color.BLACK, 3.5))
        val com = AreaComparator()
        var res = true

        var maxArea = -1.0
        col.getSorted(com).forEach {
            if (it.calcArea() < maxArea)
                res = false
            else maxArea = it.calcArea()
            assert(res)
        }
    }
}