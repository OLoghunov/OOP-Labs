package lab2

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class AnswerCSV {
    private val replicas : MutableMap<Item?, Int> = HashMap()
    private val floorCounter : MutableMap<String?, MutableList<Int>?> = HashMap()

    fun readCSV(filename: String) {
        val file = File(filename)
        val time = System.currentTimeMillis()
        csvReader { delimiter = ';' }.open(file) {
            readAllWithHeaderAsSequence().forEach { part ->
                val item = Item(
                    part["city"]!!,
                    part["street"]!!,
                    part["house"]!!.toInt(),
                    part["floor"]!!.toInt()
                )
                replicas.putIfAbsent(item, 0)
                replicas[item] = replicas[item]!! + 1
                floorCounter.putIfAbsent(item.city, mutableListOf(0,0,0,0,0))
                floorCounter[item.city]?.set(item.floor - 1, floorCounter[item.city]?.get(item.floor - 1)!! + 1)
            }
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