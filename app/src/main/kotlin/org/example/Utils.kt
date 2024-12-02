package org.example

import java.io.BufferedReader
import java.io.File
import kotlin.math.abs

fun read_lines(filename: String): List<String> {

    val reader: BufferedReader = File(filename).bufferedReader()

    return reader.readLines()

}

fun day02_is_safe(levels: List<Int>): Boolean {

    var isIncreasing: Boolean? = null

    for (i in 0..levels.size-2) {
        val first = levels[i]
        val second = levels[i+1]
        val diff = abs(second - first)

        if (second > first) {

            if (isIncreasing == null) {
                isIncreasing = true
            } else if (!isIncreasing) {
                // Unsafe because was decreasing, now increasing
                return false;
            }

        } else if (first > second) {

            if (isIncreasing == null) {
                isIncreasing = false
            } else if (isIncreasing) {
                // Unsafe because was increasing, now decreasing
                return false;
            }

        }

        if (diff < 1 || diff > 3) {
            // Unsafe because difference is beyond 1 to 3
            return false;
        }

    }

    return true

}
