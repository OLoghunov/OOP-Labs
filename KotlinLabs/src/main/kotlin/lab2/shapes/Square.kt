package lab2.shapes

import lab2.interfaces.ColoredShape
import java.awt.Color

class Square(override val borderColor : Color, override val fillColor : Color, private val sideLength : Double) : ColoredShape {
    init {
        require(sideLength > 0)
    }
    override fun calcArea(): Double {
        return sideLength * sideLength
    }
}