package lab6.jsonHandling
import java.io.File

class Saver {
    fun save(str: String, path: String) {
        File(path).writeText(str)
    }

    fun readFile(path: String): String {
        return File(path).readText()
    }
}