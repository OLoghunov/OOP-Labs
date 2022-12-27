package lab2

data class Item(val city: String, val street: String,
                val house: Int, val floor: Int
                ) {

    override fun toString(): String {
        return "'$city', '$street', $house, $floor"
    }
}
