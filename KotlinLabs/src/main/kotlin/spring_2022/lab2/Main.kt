package lab2

import lab2.shapes.Circle
import lab2.shapes.Rectangle
import lab2.shapes.Square
import lab2.shapes.Triangle
import java.awt.Color

fun main() {
    val col = ShapeCollector()
    col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
   // col.pushShape(Circle(Color.WHITE, Color.BLACK, -1.0))
    col.pushShape(Rectangle(Color.RED, Color.BLUE, 2.0, 2.5))
    col.pushShape(Triangle(Color.BLUE, Color.BLACK, 3.5, 1.0))
    col.pushShape(Square(Color.RED, Color.BLUE, 2.0))
    col.pushShape(Circle(Color.WHITE, Color.BLACK, 3.5))
    val a = col.findShapesByType(Square::class.java)
    a.forEach { shape ->
        println(shape.javaClass)
    }
}