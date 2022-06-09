package lab6.jsonHandling

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import lab6.SerShape
import lab6.shapes.Circle
import lab6.shapes.Rectangle
import lab6.shapes.Square
import lab6.shapes.Triangle

class Serializer {
    val json = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(SerShape::class) {
                subclass(Square::class, Square.serializer())
                subclass(Rectangle::class, Rectangle.serializer())
                subclass(Circle::class, Circle.serializer())
                subclass(Triangle::class, Triangle.serializer())
            }
        }
    }

    inline fun <reified T> serialize(shapeCollector: T): String {
        return json.encodeToString(shapeCollector)
    }

    inline fun <reified T> deserialize(shapeCollectorJSON: String): T {
        return json.decodeFromString(shapeCollectorJSON)
    }
}