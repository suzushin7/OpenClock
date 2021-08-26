package blog.suzushinlab.openclock

import javafx.application.Application
import javafx.application.Platform
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.text.Font
import javafx.stage.Stage
import java.text.SimpleDateFormat
import java.util.*

class OpenClock: Application() {
    private val timer = Timer()

    override fun start(primaryStage: Stage?) {
        // Initialize Stage
        with(primaryStage!!) {
            title = "OpenTimer ver 1.00"
            width = 640.0
            height = 240.0
        }

        // Setup BorderPane
        val pane = BorderPane()

        // Create Label
        val label = Label()
        with(label) {
            text = "----/--/-- --:--:--"
            font = Font.font("Monospaced", 40.0)
        }
        pane.center = label

        // Create Buttons
        val bottom = HBox(10.0)
        with(bottom) {
            alignment = Pos.CENTER
            padding = Insets(10.0)
        }
        val red = ButtonBuilder.build("Red", "#cc0000", label)
        val green = ButtonBuilder.build("Green", "#00cc00", label)
        val blue = ButtonBuilder.build("Blue", "#0000cc", label)
        bottom.children.addAll(red, green, blue)
        pane.bottom = bottom

        // Set Scene and show
        primaryStage.scene = Scene(pane)
        primaryStage.show()

        // Set TimerTask
        timer.scheduleAtFixedRate(MyTimerTask(label), 1000-(System.currentTimeMillis()%1000), 1000)

        // For canceling TimerTask
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