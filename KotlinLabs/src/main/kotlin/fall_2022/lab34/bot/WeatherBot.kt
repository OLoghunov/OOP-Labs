package lab34.bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.entities.ChatAction
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.github.kotlintelegrambot.logging.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lab34.local.SQLHandler
import lab34.remote.WEATHER_API_KEY
import lab34.remote.repository.WeatherRepository

private const val IS_DAY = 1

private const val BOT_TOKEN = "5733349852:AAFBZ7ahNXO4m-c_Cm9iNyuEO59P5fWjGdg"
private const val TIMEOUT_TIME = 30

class WeatherBot(private val weatherRepository: WeatherRepository) {

    private lateinit var country: String
    private var _chatId: ChatId.Id? = null
    private val chatId by lazy { requireNotNull(_chatId) }

    fun createBot(): Bot {
        return bot {
            token = BOT_TOKEN
            timeout = TIMEOUT_TIME
            logLevel = LogLevel.Network.Body

            dispatch {
                setUpCommands()
                setUpCallbacks()
            }
        }
    }

    private fun Dispatcher.setUpCallbacks() {
        callbackQuery(callbackData = "getMyLocation") {
            bot.sendMessage(chatId = chatId, text = "Отправь мне свою локацию.")
            location {
                CoroutineScope(Dispatchers.IO).launch {
                    val userCountryName = weatherRepository.getCountryNameByCoordinates(
                        latitude = location.latitude.toString(),
                        longitude = location.longitude.toString(),
                        format = "json"
                    ).address.state

                    val inlineKeyboardMarkup = InlineKeyboardMarkup.create(
                        listOf(
                            InlineKeyboardButton.CallbackData(
                                text = "Да, верно.",
                                callbackData = "yes_label"
                            )
                        )
                    )
                    country = userCountryName

                    bot.sendMessage(
                        chatId = chatId,
                        text = "Твой город - ${country}, верно? \nЕсли неверно, скинь локацию ещё раз",
                        replyMarkup = inlineKeyboardMarkup
                    )
                }
            }
        }

        callbackQuery(callbackData = "enterManually") {
            bot.sendMessage(chatId = chatId, text = "Хорошо, введи свой город.")
            message(Filter.Text) {
                val inlineKeyboardMarkup = InlineKeyboardMarkup.create(
                    listOf(
                        InlineKeyboardButton.CallbackData(
                            text = "Да, верно.",
                            callbackData = "yes_label"
                        )
                    )
                )
                country = message.text.toString()
                bot.sendMessage(
                    chatId = chatId,
                    text = "Твой город — ${message.text}, верно? \nЕсли неверно, введи свой город ещё раз.",
                    replyMarkup = inlineKeyboardMarkup
                )
            }
        }

        callbackQuery(callbackData = "yes_label") {
            bot.apply {
                sendMessage(chatId = chatId, text = "Уже смотрю погоду...")
                sendChatAction(chatId = chatId, action = ChatAction.TYPING)
            }
            CoroutineScope(Dispatchers.IO).launch {
                val currentWeather = weatherRepository.getCurrentWeather(
                    apiKey = WEATHER_API_KEY,
                    queryCountry = country,
                    isAqiNeeded = "no"
                )
                bot.sendMessage(
                    chatId = chatId,
                    text = """
                            ☁ Облачность: ${currentWeather.current.cloud}
                            🌡 Температура (градусы): ${currentWeather.current.tempDegrees}
                            🙎 ‍Ощущается как: ${currentWeather.current.feelsLikeDegrees}
                            💧 Влажность: ${currentWeather.current.humidity}
                            🌪 Направление ветра: ${currentWeather.current.windDirection}
                            🧭 Давление: ${currentWeather.current.pressureIn}
                            🌓 Сейчас день? ${if (currentWeather.current.isDay == IS_DAY) "Да" else "Нет"}
                        """.trimIndent()
                )
                bot.sendMessage(
                    chatId = chatId,
                    text = "Если хочешь узнать погоду ещё раз, \nвоспользуйся командой /weather"
                )
                SQLHandler(country,
                    currentWeather.current.cloud.toString(),
                    currentWeather.current.tempDegrees.toString(),
                    currentWeather.current.feelsLikeDegrees.toString(),
                    currentWeather.current.humidity.toString(),
                    currentWeather.current.windDirection,
                    currentWeather.current.pressureIn.toString())
                country = ""
            }
        }
    }

    private fun Dispatcher.setUpCommands() {
        command("start") {
            _chatId = ChatId.fromId(message.chat.id)
            bot.sendMessage(
                chatId = chatId,
                text = "Привет. Я бот, умеющий отображать погоду. \nДля запуска бота введи команду /weather"
            )
        }

        command("weather") {
            _chatId = ChatId.fromId(message.chat.id)
            val inlineKeyboardMarkup = InlineKeyboardMarkup.create(
                listOf(
                    InlineKeyboardButton.CallbackData(
                        text = "Определить мой город\n(для мобильных устройств)",
                        callbackData = "getMyLocation"
                    )
                ),
                listOf(
                    InlineKeyboardButton.CallbackData(
                        text = "Ввести город вручную",
                        callbackData = "enterManually"
                    )
                )
            )
            bot.sendMessage(
                chatId = chatId,
                text = "Для того, чтобы я смог отправить тебе погоду, \nмне нужно знать твой город.",
                replyMarkup = inlineKeyboardMarkup
            )
        }
    }
}