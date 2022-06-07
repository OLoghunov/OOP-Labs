
fun parseAddresses(addresses: List<String>) : List<Address> {
    val result : ArrayList<Address> = arrayListOf()
    addresses.forEach{ str->
        val addressList = str.split(",")
        val newAddress = Address(addressList[0].toInt(),addressList[1],addressList[2],addressList[3].toInt())
        result.add(newAddress)
    }
    return result
}

fun main() {
    val list = arrayListOf(
        "234234,Saint-Petersburg,Aptekarskaya,44",
        "252525,Moscow,Pushkina,24",
        "454533,Sochi,Voikova,74",
        "242553,Yekaterinburg,Pobedy,29")
    val newList = parseAddresses(list)
    var minIndex = newList[0].getIndex()
    var maxIndex = newList[0].getIndex()
    var numMin = 0
    var numMax = 0
    var maxStreet = newList[0].getStreet()
    var numStreet = 0
    println(message = "read addresses:")

    newList.forEach { address ->
        println(address.getAddress())
        if (address.getIndex() < minIndex) {
            minIndex = address.getIndex()
            numMin = newList.indexOf(address)
        }
        if (address.getIndex() > maxIndex) {
            maxIndex = address.getIndex()
            numMax = newList.indexOf(address)
        }
        if (address.getStreet() > maxStreet) {
            maxStreet = address.getStreet()
            numStreet = newList.indexOf(address)
        }
    }

    println("minimal index $minIndex has address:")
    println(newList[numMin].getAddress())
    println("maximum index $maxIndex has address:")
    println(newList[numMax].getAddress())
    println("the longest street name $maxStreet has an address:")
    println(newList[numStreet].getAddress())
}