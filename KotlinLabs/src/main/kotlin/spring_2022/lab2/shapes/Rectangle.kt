package lab2.shapes

import lab2.interfaces.ColoredShape
import java.awt.Color

class Rectangle(override val borderColor : Color, override val fillColor : Color, val width : Double, val height : Double) : ColoredShape {
    init {
        require(width > 0 && height > 0)
    }
    override fun calcArea(): Double {
        return width * height
    }
}