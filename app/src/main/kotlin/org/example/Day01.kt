package org.example

import kotlin.math.abs

class Day01 {

    companion object {

        fun readInput(): List<Pair<Int, Int>> {
            val lines = read_lines("src/main/resources/day01.txt")

            // Convert lines to pairs of ints
            return lines.map { line: String ->
                val parts = line.split("   ")
                val n1 = parts[0].toInt()
                val n2 = parts[1].toInt()

                Pair(n1, n2)
            }
        }

        fun part1(): Int {
            
            val pairs = readInput()

            // Split up pairs into their own lists and sort them
            val listA = pairs.map { pair -> pair.first }.sorted()
            val listB = pairs.map { pair -> pair.second }.sorted()

            // Get the sum of distances
            var sum = 0
            for (i in listA.indices) {
                sum += abs(listA[i] - listB[i])
            }

            return sum
        }

        fun part2(): Int {
            
            val pairs = readInput()

            // Split up pairs into their own lists
            val listA = pairs.map { pair -> pair.first }
            val listB = pairs.map { pair -> pair.second }

            // Build frequency map from right list
            var freqMap = HashMap<Int, Int>()
            listB.forEach { n -> 
                if (freqMap.containsKey(n)) {
                    val old = freqMap.getValue(n)
                    freqMap.replace(n, old+1)
                } else {
                    freqMap.put(n, 1)
                }
            }

            // Calculate similarity using frequency map
            var similarity = 0
            listA.forEach { n ->
                if (freqMap.containsKey(n)) {
                    val occurences = freqMap.getValue(n)
                    similarity += n * occurences
                }
            }

            return similarity
        }

    }

}