class Address(val index: Int, val city: String, val street: String, val house: Int) {
    override fun toString(): String {
        return "$index,$city,$street,$house"
    }
}