package com.example.myapplication.ui

import androidx.compose.ui.graphics.Color
import kotlin.reflect.KProperty0

val someHighOrderFunction: (String) -> Int = {
    it.length * 2
}

data class SomeLambdaWrapper(
    val leWrapper: NestedLambdaWrapper
)

data class NestedLambdaWrapper(
    val leLambda: ((CustomerColorScheme) -> Color)
)

object SomeObject {
    fun getInt(string: String) = string.length * 8
}

object CustomerColorScheme {
    internal val color: Color = Color.Red
}
