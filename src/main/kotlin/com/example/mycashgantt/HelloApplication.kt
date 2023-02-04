package com.example.mycashgantt

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class HelloApplication : Application() {
    lateinit var x: Item


    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load())
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()

        var y = null
        var x = lazy { Item("2", 11.1, LocalDate.MAX, LocalDate.MIN, mutableListOf((CustomMonth.Apr)) )}

        println("after coroutine")
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}