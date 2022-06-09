package lab6

interface ColoredShape : lab2.interfaces.Shape {
    val borderColor: SerColor
    val fillColor: SerColor
}