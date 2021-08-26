package blog.suzushinlab.openclock

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.text.Font
import javafx.stage.Stage
import java.text.SimpleDateFormat
import java.util.*

class OpenClock: Application() {
    private val timer = Timer()

    override fun start(primaryStage: Stage?) {
        with(primaryStage!!) {
            title = "OpenTimer ver 1.00"
            width = 640.0
            height = 480.0
        }

        val pane = BorderPane()
        val label = Label()
        with(label) {
            text = "----/--/-- --:--:--"
            font = Font.font("Monospaced", 40.0)
        }
        pane.center = label

        primaryStage.scene = Scene(pane)
        primaryStage.show()

        timer.scheduleAtFixedRate(MyTimerTask(label), 1000-(System.currentTimeMillis()%1000), 1000)
        primaryStage.setOnCloseRequest { timer.cancel() }
    }

    private class MyTimerTask(private val label: Label): TimerTask() {
        private val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        override fun run() {
            Platform.runLater {
                label.text = sdf.format(Calendar.getInstance().time)
            }
        }
    }
}

fun main() {
    Application.launch(OpenClock::class.java)
}