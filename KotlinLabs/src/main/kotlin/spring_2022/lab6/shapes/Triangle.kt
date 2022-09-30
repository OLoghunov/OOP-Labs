package lab6.shapes

import lab6.SerShape
import kotlinx.serialization.Serializable
import lab6.SerColor

@Serializable
class Triangle(override val borderColor : SerColor, override val fillColor : SerColor, val height : Double, val baseLength : Double) : SerShape() {
    init {
        require(height > 0 && baseLength > 0)
    }
    override fun calcArea(): Double {
        return height * baseLength * 0.5
    }
    override fun toString(): String {
        return "${this::class} Height: $height BaseLength: $baseLength Area: ${calcArea()} Fill: $fillColor Border: $borderColor"
    }
}