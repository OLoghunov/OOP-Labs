package lab4

import javafx.scene.image.Image
import javafx.stage.Stage
import lab4.view.HelloView
import tornadofx.App

class MainApp: App(HelloView::class, Styles::class) {
    override fun start(stage: Stage) {
        val img = Image("azazen.png")
        stage.icons.add(img)
        with(stage) {
            width = 1200.0
            height = 800.0
        }
        super.start(stage)
    }
}