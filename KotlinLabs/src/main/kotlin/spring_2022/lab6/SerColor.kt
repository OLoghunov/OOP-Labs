package lab6

import kotlinx.serialization.Serializable

@Serializable
data class SerColor(val r: Float, val g: Float, val b: Float) {
    init {
        require(r >= 0 && g >= 0 && b >= 0)
    }
}