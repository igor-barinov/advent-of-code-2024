package org.example

import java.io.BufferedReader
import java.io.File

fun read_lines(filename: String): List<String> {

    val reader: BufferedReader = File(filename).bufferedReader()

    return reader.readLines()

}