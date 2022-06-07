package lab4.view

import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import lab4.control.MainController
import tornadofx.*


class MainView : View("Maze") {
    private val ctrl: MainController by inject()
    private var moveCounter = SimpleIntegerProperty(0)
    private var canvas : Canvas = Canvas((ctrl.model.width * 30).toDouble(), (ctrl.model.height * 30).toDouble())

    private fun draw()
    {
        val g : GraphicsContext = this.canvas.graphicsContext2D
        g.fill = Color.SANDYBROWN
        g.fillRect(0.0,0.0,(ctrl.model.width * 30).toDouble(),(ctrl.model.height * 30).toDouble())
        g.fill = Color.DARKGREEN
        var i = 0.0
        while (i < ctrl.model.height) {
            var j = 0.0
            while (j < ctrl.model.width) {
                if (ctrl.model.field[i.toInt()][j.toInt()] == 'P') {
                    g.fill = Color.RED
                    g.fillOval(j * 30, i * 30, 30.0, 30.0)
                    g.fill = Color.DARKGREEN
                }
                if (ctrl.model.field[i.toInt()][j.toInt()] == '#' || ctrl.model.field[i.toInt()][j.toInt()] == '?')
                    g.fillRect(j * 30, i * 30, 30.0, 30.0)
                if (ctrl.model.field[i.toInt()][j.toInt()] == 'E') {
                    g.fill = Color.BLUE
                    g.fillOval(j * 30, i * 30, 30.0, 30.0)
                    g.fill = Color.DARKGREEN
                }
                j++
            }
            i++
        }
    }

    override val root = form {
        alignment = Pos.CENTER

            runLater {
                runAsync {
                    while (ctrl.model.isNotReady) {
                        ctrl.build()
                        draw()
                        Thread.sleep(20)
                    }
                    ctrl.createStartAndEnd()
                    draw()
                }
            }
        draw()
        this.setOnKeyPressed {
            if (ctrl.model.initialized)
                ctrl.processPress(it)
            draw()
            moveCounter.value++
        }
        draw()
        button {
            label("Move counter: ${moveCounter.value}")
        }
        this.getChildList()?.add(canvas)
    }
}