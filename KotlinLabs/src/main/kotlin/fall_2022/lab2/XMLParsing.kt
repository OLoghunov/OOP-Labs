package lab2

import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class AnswerXML {
    private val replicas : MutableMap<Item?, Int> = HashMap()
    private val floorCounter : MutableMap<String?, MutableList<Int>?> = HashMap()

    fun readXML(filename: String) {
        val file = File(filename)

        val rootChildren = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(file)
            .getElementsByTagName("item")

        val time = System.currentTimeMillis()
        for (i in 0 until rootChildren.length) {
            val item: Item = with(rootChildren.item(i).attributes) {
                Item(
                    getNamedItem("city").textContent,
                    getNamedItem("street").textContent,
                    getNamedItem("house").textContent.toInt(),
                    getNamedItem("floor").textContent.toInt()
                )
            }
            replicas.putIfAbsent(item, 0)
            replicas[item] = replicas[item]!! + 1
            floorCounter.putIfAbsent(item.city, mutableListOf(0,0,0,0,0))
            floorCounter[item.city]?.set(item.floor - 1, floorCounter[item.city]?.get(item.floor - 1)!! + 1)
        }
        println("processing duration :  ${System.currentTimeMillis() - time} milliseconds")
    }

    fun printReplicas() {
        replicas.forEach { entry ->
            if (entry.value > 1)
            println("${entry.key} : ${entry.value}")
        }
    }

    fun printFloorCounterMap() {
        floorCounter.forEach { entry ->
            println("${entry.key} : ${entry.value}")
        }
    }
}