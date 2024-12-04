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

fun day04_get_se_diag_from(lines: List<String>, x: Int, y: Int): String {
    var r = x
    var c = y
    
    var str = ""
    while (r < lines.size && c < lines[r].length) {
        str += lines[r][c]
        r += 1
        c += 1
    }

    return str
}

fun day04_get_sw_diag_from(lines: List<String>, x: Int, y: Int): String {
    var r = x
    var c = y
    
    var str = ""
    while (r < lines.size && c >= 0) {
        str += lines[r][c]
        r += 1
        c -= 1
    }

    return str
}

fun day04_count_matches(matches: Sequence<MatchResult>): Int {
    var n = 0
    for (m in matches) {
        if (m.value.equals("XMASAMX")) { n += 2 }
        else { n += 1 }
    }

    return n
}