package lab4.control

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.input.KeyCode
import lab4.model.MainModel
import lab4.view.ChoosingView
import lab4.view.HelloView
import lab4.view.MainView
import tornadofx.Controller
import tornadofx.alert
import tornadofx.runLater
import java.io.File
import java.util.Optional
import kotlin.system.exitProcess


enum class Direction {
    UP,DOWN,RIGHT,LEFT
}

class MainController: Controller() {
    val model: MainModel by inject()
    private var generated = false
    private var isExitReached = false

    fun readFromFile() {
        try {
            val input = File("maze.txt")
                .readLines()
            pushDataToModel(input)
            model.isNotReady = false
        } catch (e : Exception) {
            print(e.message)
        }
    }

    private fun pushDataToModel(strList : List<String>) {
        strList.forEachIndexed { strIndex, str ->
            model.height++
            model.field.add(mutableListOf())
            var w = 0
            str.forEachIndexed { itemIndex, item ->
                if (item == 'P')
                {
                    model.playerX = itemIndex
                    model.playerY = strIndex
                }
                w++
                model.field.last().add(item)
            }
            if (w >= model.width) model.width = w
        }
        model.initialized = true
    }

    fun processPress(event : javafx.scene.input.KeyEvent) {
        when(event.code) {
            KeyCode.UP -> moveTo(Direction.UP)
            KeyCode.W -> moveTo(Direction.UP)
            KeyCode.DOWN -> moveTo(Direction.DOWN)
            KeyCode.S -> moveTo(Direction.DOWN)
            KeyCode.LEFT -> moveTo(Direction.LEFT)
            KeyCode.A -> moveTo(Direction.LEFT)
            KeyCode.RIGHT -> moveTo(Direction.RIGHT)
            KeyCode.D -> moveTo(Direction.RIGHT)
            KeyCode.ESCAPE -> {
                val alert : Alert = alert(Alert.AlertType.CONFIRMATION, "Exit the game?")
                event.consume()
                val result : Optional<ButtonType> = alert.showAndWait()
                if (result.isPresent && result.get() == ButtonType.OK) {
                    exitProcess(0)
                }
                if (result.isPresent && result.get() == ButtonType.CANCEL) {
                    print("")
                }
            }

            else -> {}
        }
    }

    private fun moveTo(to: Direction) {
        if (to == Direction.DOWN) {
            if (model.playerY < model.height - 1 && (model.field[model.playerY + 1][model.playerX] == '-' || model.field[model.playerY + 1][model.playerX] == 'E')) {
                if (model.field[model.playerY + 1][model.playerX] == 'E')
                    isExitReached = true
                model.field[model.playerY + 1][model.playerX] = 'P'
                model.field[model.playerY][model.playerX] = '-'
                model.playerY++
                runLater {
                    if (isExitReached)
                        alertOnWin()
                }
            }
        }
        if (to == Direction.UP) {
            if (model.playerY > 0 && (model.field[model.playerY - 1][model.playerX] == '-' || model.field[model.playerY - 1][model.playerX] == 'E')) {
                if (model.field[model.playerY - 1][model.playerX] == 'E')
                    isExitReached = true
                model.field[model.playerY - 1][model.playerX] = 'P'
                model.field[model.playerY][model.playerX] = '-'
                model.playerY--
                runLater {
                    if (isExitReached)
                        alertOnWin()
                }
            }
        }
        if (to == Direction.RIGHT) {
            if (model.playerX < model.width - 1 && (model.field[model.playerY][model.playerX + 1] == '-' || model.field[model.playerY][model.playerX + 1] == 'E')) {
                if (model.field[model.playerY][model.playerX + 1] == 'E')
                    isExitReached = true
                model.field[model.playerY][model.playerX + 1] = 'P'
                model.field[model.playerY][model.playerX] = '-'
                model.playerX++
                runLater {
                    if (isExitReached)
                        alertOnWin()
                }
            }
        }
        if (to == Direction.LEFT) {
            if (model.playerX > 0 && (model.field[model.playerY][model.playerX - 1] == '-' || model.field[model.playerY][model.playerX - 1] == 'E')) {
                if (model.field[model.playerY][model.playerX - 1] == 'E')
                    isExitReached = true
                model.field[model.playerY][model.playerX - 1] = 'P'
                model.field[model.playerY][model.playerX] = '-'
                model.playerX--
                runLater {
                    if (isExitReached)
                        alertOnWin()
                }
            }
        }
    }

    private fun alertOnWin()
    {
        val result: Optional<ButtonType> = alert(Alert.AlertType.CONFIRMATION, "Finish!", "You reached the end of the labyrinth. Exit the game?").showAndWait()
        if (result.isPresent && result.get() == ButtonType.OK) {
            exitProcess(0)
        }
        if (result.isPresent && result.get() == ButtonType.CANCEL) {
            print("")
        }
    }

    fun changeToChoosingScreen()
    {
        find(HelloView::class).replaceWith(ChoosingView::class)
    }

    fun changeToMainScreen()
    {
        find(ChoosingView::class).replaceWith(MainView::class)
    }

    fun generateMaze() {
        generated = true
        model.initiateLabyrinth()
        model.initialized = true
    }

    fun build() {
        model.build()
    }

    fun createStartAndEnd() {
        if (model.isGenerated)
            model.createStartAndEnd()
    }
}