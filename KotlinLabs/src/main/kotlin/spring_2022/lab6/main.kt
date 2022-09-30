package lab6

import lab6.jsonHandling.Saver
import lab6.jsonHandling.Serializer
import lab6.shapes.Circle
import lab6.shapes.Square


fun main() {
    val col = ShapeCollector()
    val serializer = Serializer()
    val saver = Saver()

    col.pushShape(Square(SerColor(1f,1f,1f), SerColor(1f,1f,1f), 2.0))
    col.pushShape(Circle(SerColor(0f, 0f, 0f), SerColor(0f, 0f, 0f), 1.0))
    col.pushShape(Square(SerColor(1f,1f,1f), SerColor(1f,1f,1f), 2.0))
    col.pushShape(Circle(SerColor(0f, 0f, 0f), SerColor(0f, 0f, 0f), 1.0))

    val desCol = serializer.deserialize<ShapeCollector>(saver.readFile("out.txt"))
    desCol.getCollection().forEach {
        println(it.toString())
    }

    saver.save(serializer.serialize(col), "out.txt")
}