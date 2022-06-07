package lab4

import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val leftBox by cssclass()
        val rightBox by cssclass()
    }
    init {
        leftBox {
            prefWidth = 200.px
            prefHeight = 400.px
        }

        rightBox {
            borderColor += box(Color.GRAY)
        }
    }
}