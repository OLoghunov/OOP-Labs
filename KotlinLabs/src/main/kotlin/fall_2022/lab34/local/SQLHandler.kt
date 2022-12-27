package lab34.local

import java.sql.Connection
import java.sql.DriverManager
import java.text.SimpleDateFormat
import java.util.*

class SQLHandler(
    country: String,
    cloud: String,
    tem: String,
    feelsLike: String,
    humidity: String,
    windDir: String,
    pressure: String
) {
    private val connection: Connection

    init {
        val connectionUrl =
            "jdbc:sqlserver://localhost:1433;encrypt=true;database=WeatherStamps;trustServerCertificate=true;"
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val dateStamp = formatter.format(date)
        connection = DriverManager.getConnection(connectionUrl, "bot", "1")
        connection
            .prepareStatement("INSERT INTO Stamps " + "VALUES ('$country', '$cloud', '$tem', '$feelsLike', '$humidity', '$windDir', '$pressure', '$dateStamp')")
            .execute()
        connection.close()
    }
}