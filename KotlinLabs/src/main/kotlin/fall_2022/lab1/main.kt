package lab1

import com.google.gson.JsonObject
import java.net.URI
import java.net.URLEncoder
import java.net.http.*
import com.google.gson.*
import java.awt.Desktop

class Result(private val pageTitle: String, val pageId: String) {
    companion object {
        fun convertJson(obj: JsonObject): Result {
            return Result(obj.getAsJsonPrimitive("title").asString, obj.getAsJsonPrimitive("pageid").asString)
        }
    }
    override fun toString(): String {
        return "$pageId: \"$pageTitle\""
    }
}

class Request(private val requestString: String) {
    private val client = HttpClient.newBuilder().build()
    private val requestLink = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch="
    private val resultLink = "https://ru.wikipedia.org/w/index.php?curid="
    val results: List<Result>

    init {
        val requestString = getResponse()
        results = getResults(requestString)
    }

    @Suppress("DEPRECATION")
    private fun getResponse(): String {
        val fullRequestLink = requestLink + URLEncoder.encode("\"$requestString\"")

        val request = HttpRequest.newBuilder().uri(URI.create(fullRequestLink)).build()

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body()
    }

    private fun getResults(jsonString: String): List<Result> {
        val jsonArray = Gson()
            .fromJson(jsonString, JsonObject::class.java)
            .getAsJsonObject("query")
            .getAsJsonArray("search")

        val results = emptyList<Result>().toMutableList()

        jsonArray.forEach {
            results.add(
                Result.convertJson(it.asJsonObject)
            )
        }
        return results
    }

    fun openPage(id: Int) {
        Desktop.getDesktop().browse(URI(resultLink + results[id].pageId))
    }
}

fun main() {
    println("Input your search phrase:")
    val input = readln()
    val request = Request(input)

    println("Search \"$input\" results are:")
    request.results.forEach { result ->
        println("$result")
    }

    println("\nInput index to open:")
    val inputtedIndex = readln().toInt()
    request.openPage(inputtedIndex)
}