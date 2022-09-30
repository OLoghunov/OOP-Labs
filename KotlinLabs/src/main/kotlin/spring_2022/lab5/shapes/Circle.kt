package lab5.shapes

import lab5.interfaces.ColoredShape
import java.awt.Color
import kotlin.math.PI

class Circle(override val borderColor: Color, override val fillColor: Color, private val radius: Double) : ColoredShape {
    init {
        require(radius > 0)
    }
    override fun calcArea(): Double {
        return radius * radius * PI
    }
}