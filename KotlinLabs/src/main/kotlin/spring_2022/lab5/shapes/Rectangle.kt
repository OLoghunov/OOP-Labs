package lab5.shapes

import lab5.interfaces.ColoredShape
import java.awt.Color

class Rectangle(override val borderColor : Color, override val fillColor : Color, private val width : Double, private val height : Double) : ColoredShape {
    init {
        require(width > 0 && height > 0)
    }
    override fun calcArea(): Double {
        return width * height
    }
}