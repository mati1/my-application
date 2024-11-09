package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            GameScreen()
        }

        val someLambdaWrapper = SomeLambdaWrapper(
            NestedLambdaWrapper { CustomerColorScheme.color }
        )
        println("$someLambdaWrapper")
    }
}

