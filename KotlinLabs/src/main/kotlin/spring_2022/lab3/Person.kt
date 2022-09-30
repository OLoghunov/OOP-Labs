package lab3

class Person(private val firstName: String, private val lastName : String) {
    override fun toString(): String {
        return "$firstName $lastName"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Person
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        return result
    }
}