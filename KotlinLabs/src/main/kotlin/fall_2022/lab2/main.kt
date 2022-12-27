package lab2

fun main() {
    println("enter the path to the file:")
    val filename = readln()

    println("specify the file type (\"csv\" or \"xml\")")
    when (readln()) {
        "csv" -> {
            val c = AnswerCSV()
            println("processing...")
            c.readCSV(filename)
            while (true) {
                println("replicas or floor counters (\"replicas\" or \"floorCounters\" or \"exit\")")
                when (readln()) {
                    "replicas" -> c.printReplicas()
                    "floorCounters" -> c.printFloorCounterMap()
                    "exit" -> break
                    else -> break
                }
            }
        }
        "xml" -> {
            val x = AnswerXML()
            println("processing...")
            x.readXML(filename)
            while (true) {
                println("replicas or floor counters (\"replicas\" or \"floorCounters\" or \"exit\")")
                when (readln()) {
                    "replicas" -> x.printReplicas()
                    "floorCounters" -> x.printFloorCounterMap()
                    "exit" -> break
                    else -> break
                }
            }
        }
    }
}