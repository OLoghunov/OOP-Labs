package lab4.view

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import lab4.control.MainController
import tornadofx.*

class ChoosingView : View() {

    private val ctrl = MainController()

    override val root = vbox(10) {
        alignment = Pos.CENTER
        style {
            fontWeight = FontWeight.EXTRA_BOLD
            fontSize = 30.px
        }
        button("Get the maze from the file") {
            action {
                ctrl.readFromFile()
                ctrl.changeToMainScreen()
            }
        }

        button("Generate the maze") {
            action {
                ctrl.generateMaze()
                ctrl.changeToMainScreen()
            }
        }
    }
}