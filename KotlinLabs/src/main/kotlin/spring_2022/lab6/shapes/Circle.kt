package lab6.shapes

import lab6.SerShape
import kotlin.math.PI
import kotlinx.serialization.Serializable
import lab6.SerColor

@Serializable
class Circle(override val borderColor: SerColor, override val fillColor: SerColor, val radius: Double) : SerShape() {
    init {
        require(radius > 0)
    }
    override fun calcArea(): Double {
        return radius * radius * PI
    }
}