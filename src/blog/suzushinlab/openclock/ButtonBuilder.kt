package blog.suzushinlab.openclock

import javafx.scene.control.Label
import javafx.scene.text.Font
import javafx.scene.control.Button as Button

class ButtonBuilder {
    companion object {
        fun build(text: String, fill: String, label: Label): Button {
            val button = Button(text)
            with(button) {
                font = Font.font("Monospaced", 24.0)
            }
            button.setOnAction {
                label.style = String.format("-fx-text-fill: %s;-font-family: Monospaced;-fx-size: 24.0;", fill)
            }
            return button
        }
    }
}