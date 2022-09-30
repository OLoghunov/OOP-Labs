package lab6.shapes

import lab6.SerShape
import kotlinx.serialization.Serializable
import lab6.SerColor

@Serializable
class Rectangle(override val borderColor : SerColor, override val fillColor : SerColor, val width : Double, val height : Double) : SerShape() {
    init {
        require(width > 0 && height > 0)
    }
    override fun calcArea(): Double {
        return width * height
    }
}