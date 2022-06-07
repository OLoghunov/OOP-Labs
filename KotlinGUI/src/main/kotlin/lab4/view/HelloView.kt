package lab4.view

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import lab4.control.MainController
import tornadofx.*

class HelloView : View() {

    private val ctrl = MainController()

    override val root = vbox(10) {
        alignment = Pos.CENTER
        label("Maze game")
        style {
            fontWeight = FontWeight.EXTRA_BOLD
            fontSize = 30.px
        }
        button("Start") {
            action {
                ctrl.changeToChoosingScreen()
            }
        }
    }
}