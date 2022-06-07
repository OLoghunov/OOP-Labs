class Address(private val index: Int, private val city: String, private val street: String, private val house: Int) {
    fun getAddress() : String {
        return "$index,$city,$street,$house"
    }
    fun getIndex(): Int = index
    fun getStreet() : String = street
}