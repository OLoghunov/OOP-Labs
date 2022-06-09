import lab6.SerColor
import lab6.ShapeCollector
import lab6.jsonHandling.Saver
import lab6.jsonHandling.Serializer
import lab6.shapes.Circle
import lab6.shapes.Rectangle
import lab6.shapes.Square
import lab6.shapes.Triangle

internal class Lab6JsonTests {
    @org.junit.jupiter.api.Test
    fun serializeDeserializeTest() {
        val col = ShapeCollector()

        col.pushShape(Square(SerColor(1f,1f,1f),  SerColor(1f,1f,1f),1.0))
        col.pushShape(Circle(SerColor(1f,1f,1f),  SerColor(1f,1f,1f), 1.0))
        col.pushShape(Rectangle(SerColor(1f,1f,1f),  SerColor(1f,1f,1f), 2.5, 1.0))
        col.pushShape(Triangle(SerColor(1f,1f,1f),  SerColor(1f,1f,1f), 1.0, 2.0))
        col.pushShape(Square(SerColor(1f,1f,1f),  SerColor(1f,1f,1f), 2.0))
        col.pushShape(Circle(SerColor(1f,1f,1f),  SerColor(1f,1f,1f), 3.5))

        var res = true

        val saver = Saver()
        val serializer = Serializer()

        saver.save(serializer.serialize(col), "out.txt")
        val desCol = serializer.deserialize<ShapeCollector>(saver.readFile("out.txt"))

        col.getCollection().forEachIndexed { index, serShape ->
            if (serShape.borderColor != desCol.getCollection()[index].borderColor ||
                serShape.fillColor != desCol.getCollection()[index].fillColor ||
                    serShape.javaClass != desCol.getCollection()[index].javaClass)
                res = false
        }
        assert(res)
    }
}