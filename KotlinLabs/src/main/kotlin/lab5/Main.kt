package lab5

import lab5.interfaces.ColoredShape
import lab5.shapes.Circle
import lab5.shapes.Rectangle
import lab5.shapes.Square
import lab5.shapes.Triangle
import java.awt.Color

fun main() {
    val col = ShapeCollector<ColoredShape>()
    col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
    col.pushShape(Circle(Color.WHITE, Color.BLACK, 1.0))
    col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 2.5))
    col.pushShape(Triangle(Color.BLUE, Color.BLACK, 3.5, 1.0))
    col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
    col.pushShape(Circle(Color.WHITE, Color.BLACK, 3.5))

    val lst = listOf<ColoredShape>(Square(Color.RED, Color.BLUE, 2.0), Square(Color.RED, Color.BLUE, 2.0))
    col.addAll(lst)

    /*val a = col.findShapesByType(Square::class.java)
    a.forEach { shape ->
        println(shape.javaClass)
    }*/

    class AreaComparator: Comparator<ColoredShape> {
        override fun compare(o1: ColoredShape?, o2: ColoredShape?): Int {
            if(o1 == null || o2 == null)
                return 0
            return o1.calcArea().compareTo(o2.calcArea())
        }
    }

    val com = AreaComparator()

    col.getSorted(com).forEach {
        println(it.calcArea())
    }
}