package lab6

import lab2.interfaces.Shape
import kotlinx.serialization.*

@Serializable
abstract class SerShape : Shape, ColoredShape {
    abstract override fun calcArea(): Double
    abstract override val borderColor: SerColor
    abstract override val fillColor: SerColor
}