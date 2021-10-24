package com.c2p.javafxApp

import javafx.beans.value.ObservableValue

import javafx.event.EventHandler

import javafx.fxml.FXML
import javafx.fxml.Initializable

import javafx.scene.control.*

import java.lang.Exception
import java.net.URL
import java.util.*

const val C_TO_F_TEXT = "Celsius to Fahrenheit"
const val F_TO_C_TEXT = "Fahrenheit to Celsius"

private var isC_TO_F = true

class Controller : Initializable {
    @FXML
    lateinit var choiceBox: ChoiceBox<String>

    @FXML
    lateinit var userInputField: TextField

    @FXML
    lateinit var convertBtn: Button

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        choiceBox.items.add(C_TO_F_TEXT)
        choiceBox.items.add(F_TO_C_TEXT)

        choiceBox.value = C_TO_F_TEXT

        choiceBox.selectionModel.selectedItemProperty()
            .addListener { _: ObservableValue<out String>?, _: String?, newValue: String ->
                isC_TO_F = newValue == C_TO_F_TEXT
            }

        convertBtn.onAction = EventHandler {
            // System.out.println("Button Clicked.");
            convert()
        }
    }

    private fun convert() {
        val input = userInputField.text


        val inputTemp: Float = try {
            input.toFloat()
        } catch (e1: Exception) {
            warnUser()
            return
        }

        display(resultTemp =
            if (isC_TO_F) inputTemp * 9 / 5 + 32
            else (inputTemp - 32) * 5 / 9
        )
    }

    private fun warnUser() {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "Error Occurred."
        alert.headerText = "Invalid Temperature Entered"
        alert.contentText = "Please enter a valid temperature."
        alert.show()
    }

    private fun display(resultTemp: Float) {
        val resultTempString = "$resultTemp ${if (isC_TO_F) 'F' else 'C'}"
        val currentTemp = "${userInputField.text} ${if (isC_TO_F) 'C' else 'F'}"

        userInputField.text = "$currentTemp is $resultTempString"
    }

    fun newConversion() {
        isC_TO_F = true
        choiceBox.value = C_TO_F_TEXT
        userInputField.text = null
    }
}