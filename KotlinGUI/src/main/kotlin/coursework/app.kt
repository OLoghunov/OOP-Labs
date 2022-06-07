package coursework

import coursework.view.MainView
import javafx.scene.image.Image
import javafx.stage.Stage
import tornadofx.App

class MainApp: App(MainView::class, Styles::class) {
    override fun start(stage: Stage) {
        val img = Image("azazen.png")
        stage.icons.add(img)

        with(stage) {
            width = 1100.0
            height = 620.0
        }
        super.start(stage)
    }
}