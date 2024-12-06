package org.example

import kotlin.math.abs

class Day02 {
    companion object {

        fun readInput(): List<List<Int>> {
            val lines = read_lines("src/main/resources/day02.txt")

            return lines.map { line: String ->
                val parts = line.split(" ")
                parts.map {
                    s: String -> s.toInt()
                }
            }
        }

        fun isSafe(levels: List<Int>): Boolean {
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

        fun part1(): Int {

            val reports = readInput()
            
            // Figure out which reports are safe
            val safeReports = (reports.map {
                levels: List<Int> -> isSafe(levels)
            }).filter { isSafe -> isSafe }


            return safeReports.size
        }

        fun part2(): Int {

            val reports = readInput()

            val safeReports = (reports.map { levels: List<Int> -> 
                // Generate permutations by excluding items
                val permutations: List<List<Int>> = (0..levels.size).map {
                    i -> levels.filterIndexed { j, _ -> j != i }
                }

                // Check if one of the permuations are safe
                permutations.map {
                    perm -> isSafe(perm)
                }.any { tf -> tf }
            }).filter { tf -> tf }

            return safeReports.size
        }

    }
}