package coursework.control

import coursework.model.MainModel
import tornadofx.Controller


class MainController: Controller() {
    val model: MainModel by inject()
    var gameIsRunning : Boolean = false
    var gameIsInitiated : Boolean = false


    fun startGame(res: Double, dens: Int) {
        gameIsRunning = true
        model.clear()
        model.initiate(res, dens)
        gameIsInitiated = true
    }

    fun createCycle() {
        model.createCycle()
    }

    fun endGame() {
        gameIsRunning = false
        gameIsInitiated = false
    }

    fun pauseGame() {
        gameIsRunning = false
    }

    fun setAlive(x : Int, y : Int) {
        model.field[y][x] = true
    }

    fun modelContinue() {
        gameIsRunning = true
    }
}