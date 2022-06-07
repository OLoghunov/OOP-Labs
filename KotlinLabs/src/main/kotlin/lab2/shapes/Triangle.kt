package lab2.shapes

import lab2.interfaces.ColoredShape
import java.awt.Color

class Triangle(override val borderColor : Color, override val fillColor : Color, private val height : Double, private val baseLength : Double) : ColoredShape {
    init {
        require(height > 0 && baseLength > 0)
    }
    override fun calcArea(): Double {
        return height * baseLength * 0.5
    }
}