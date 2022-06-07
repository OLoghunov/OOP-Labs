package lab2.interfaces

import java.awt.Color

interface Shape {
    fun calcArea() : Double
}

interface ColoredShape : Shape {
    val borderColor : Color
    val fillColor : Color
}