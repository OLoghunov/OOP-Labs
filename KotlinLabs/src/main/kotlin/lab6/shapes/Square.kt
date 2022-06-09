package lab6.shapes

import lab6.SerShape
import kotlinx.serialization.Serializable
import lab6.SerColor

@Serializable
class Square(override val borderColor : SerColor, override val fillColor : SerColor, val sideLength : Double) : SerShape() {

    init {
        require(sideLength > 0)
    }
    override fun calcArea(): Double {
        return sideLength * sideLength
    }
}