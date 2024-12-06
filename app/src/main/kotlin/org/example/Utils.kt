package org.example

import java.io.BufferedReader
import java.io.File
import kotlin.math.abs

fun read_lines(filename: String): List<String> {

    val reader: BufferedReader = File(filename).bufferedReader()

    return reader.readLines()

}

fun read_text(filename: String): String {

    val reader: BufferedReader = File(filename).bufferedReader()

    return reader.readText()
}