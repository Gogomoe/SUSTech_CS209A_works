package project

import javafx.concurrent.Worker
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.web.WebView
import javafx.stage.Screen
import javafx.stage.Stage
import netscape.javascript.JSObject

class App(val primaryStage: Stage) {

    val screen = Screen.getPrimary()

    val browser = WebView()
    val engine = browser.engine

    init {
        val visual = screen.visualBounds
        browser.setPrefSize(visual.width * 0.9, visual.height * 0.9)

        engine.loadWorker.stateProperty()
                .addListener { obs, oldValue, newValue ->
                    if (newValue == Worker.State.SUCCEEDED) {
                        val window = engine.executeScript("window") as JSObject
                    }
                }

        engine.loadContent("<h1>Hello WebView!</h1>")

        val root = Group()
        val scene = Scene(root)

        root.children.add(browser)

        primaryStage.scene = scene
        primaryStage.show()
    }

}