package coursework.view

import coursework.Styles
import coursework.control.MainController
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("Game of life") {
    private val ctrl: MainController by inject()
    private var bResolution = ctrl.model.bind { SimpleDoubleProperty() }
    private var bDensity = ctrl.model.bind { SimpleIntegerProperty() }

    private var canvas: Canvas = Canvas(800.0, 500.0)

    private fun manualDraw(event: javafx.scene.input.MouseEvent) {
        val mouseX = event.x.toInt()
        val mouseY = event.y.toInt()
        try {
            ctrl.setAlive(mouseX / bResolution.intValue(), mouseY / bResolution.intValue())
            draw()
        } catch (e: java.lang.Exception) {
            print("")
        }
    }

    private fun clearBoard() {
        val g: GraphicsContext = this.canvas.graphicsContext2D
        g.fill = Color.WHITE
        g.fillRect(0.0, 0.0, 800.0, 500.0)
    }

    private fun draw() {
        val g: GraphicsContext = this.canvas.graphicsContext2D
        g.fill = Color.WHITE
        g.fillRect(0.0, 0.0, 800.0, 500.0)

        g.fill = Color.BLACK
        var i = 0.0
        while (i * bResolution.value <= ctrl.model.height - bResolution.value) {
            var j = 0.0
            while (j * bResolution.value <= ctrl.model.width - bResolution.value) {
                if (ctrl.model.field[i.toInt()][j.toInt()])
                    g.fillRect(j * bResolution.value, i * bResolution.value, bResolution.value, bResolution.value)
                j++
            }
            i++
        }
    }

    override val root = form {
        ctrl.model.height = canvas.height
        ctrl.model.width = canvas.width
        canvas.setOnMouseDragged { manualDraw(it) }
        canvas.setOnMouseClicked { manualDraw(it) }

        hbox(50) {
            fieldset("Configuration") {
                vbox(10) {
                    addClass(Styles.leftBox)
                    vbox {
                        label("Resolution")
                        textfield(bResolution).required()
                    }
                    vbox {
                        label("Density")
                        textfield(bDensity).required()
                    }
                    button("Create life") {
                        enableWhen(ctrl.model.valid)
                        action {
                            ctrl.startGame(bResolution.value, bDensity.value)
                            runAsync {
                                while (ctrl.gameIsRunning) {
                                    runLater {
                                        ctrl.createCycle()
                                        draw()
                                    }
                                    Thread.sleep(100)
                                }
                            }
                        }
                    }
                    button("Step") {
                        action {
                            if (!ctrl.gameIsInitiated)
                                ctrl.startGame(bResolution.value, bDensity.value)
                            ctrl.createCycle()
                            draw()
                        }
                    }
                    button("Pause") {
                        action {
                            ctrl.pauseGame()
                        }
                    }
                    button("Continue") {

                        action {
                            if (ctrl.gameIsInitiated) {
                                ctrl.modelContinue()
                                runAsync {
                                    while (ctrl.gameIsRunning) {
                                        runLater {
                                            ctrl.createCycle()
                                            draw()
                                        }
                                        Thread.sleep(100)
                                    }
                                }
                            }
                        }
                    }
                    button("Stop") {
                        action {
                            ctrl.endGame()
                            clearBoard()
                            ctrl.model.clear()
                        }
                    }
                }
            }
            hbox {
                addClass(Styles.rightBox)
                this.getChildList()?.add(canvas)
            }
        }
    }
}
