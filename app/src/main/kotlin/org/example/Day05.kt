package org.example

class Day05 {

    companion object {

        fun readInput(): Pair<List<String>, List<String>> {
            // Split up input into the 2 sections
            val lines = read_lines("src/main/resources/day05.txt")
            val i = lines.indexOfFirst { it.equals("") }
            val section1 = lines.subList(0, i)
            val section2 = lines.subList(i+1, lines.size)

            return Pair(section1, section2)
        }

        fun verifyUpdate(update: List<Int>, orderingMap: HashMap<Int, HashSet<Int>>): Int {
            for (i in (update.size-1).downTo(0)) {
                val k = update[i]
        
                val succeedingNums = orderingMap.getValue(k)
        
                if (update.subList(0, i).any { succeedingNums.contains(it) }) { // Check if preceding numbers are allowed to precede
                    return 0 // If not return 0
                }
        
            }
        
            val middleIndex = update.size / 2
            return update[middleIndex] // Otherwise return middle number
        }


        fun correctUpdate(update: List<Int>, orderingMap: HashMap<Int, HashSet<Int>>): Int {
            val sets = update.map { orderingMap.getValue(it) }

            // Score each number by the number of times it appears in a set for a different number
            // A higer score means it will appear later

            val positionScores = update.map { n: Int ->
                val score = sets.map { if (it.contains(n)) { 1 } else { 0 } }.reduce { sum, x -> sum + x }
                Pair(n, score)
            }

            val sortedPairs = positionScores.sortedBy { it.second }

            val middleIndex = update.size / 2
            return sortedPairs[middleIndex].first

        }

        fun part1(): Int {

            val sections = readInput()
            val section1 = sections.first
            val section2 = sections.first

            // Build ordering map from section 1
            // [ number ] -> set of numbers that must follow
            val orderingMap: HashMap<Int, HashSet<Int>> = hashMapOf()

            section1.forEach { str: String ->
                val nums = str.split("|").map { it.toInt() }

                val k = nums[0]

                if (orderingMap.contains(k)) {
                    val set = orderingMap.getValue(k)
                    set.add(nums[1])
                    orderingMap.replace(k, set)
                } else {
                    orderingMap.put(k, hashSetOf(nums[1]))
                }
            }

            // Parse section 2 into list of int arrays
            val updates = section2.map { line: String -> line.split(",").map { it.toInt() } }

            // Sum the middle numbers
            val result = updates.map { verifyUpdate(it, orderingMap) }.reduce { sum, n -> sum + n }

            return result
        }

        fun part2(): Int {
            val sections = readInput()
            val section1 = sections.first
            val section2 = sections.second


            // Build ordering map from section 1
            // [ number ] -> set of numbers that must follow
            val orderingMap: HashMap<Int, HashSet<Int>> = hashMapOf()

            section1.forEach { str: String ->
                val nums = str.split("|").map { it.toInt() }

                val k = nums[0]

                if (orderingMap.contains(k)) {
                    val set = orderingMap.getValue(k)
                    set.add(nums[1])
                    orderingMap.replace(k, set)
                } else {
                    orderingMap.put(k, hashSetOf(nums[1]))
                }
            }

            // Parse section 2 into list of int arrays
            val updates = section2.map { line: String -> line.split(",").map { it.toInt() } }

            // Find the incorrect updates and get the corrected middle numbers
            val result = updates.map { update: List<Int> ->
                if (verifyUpdate(update, orderingMap) == 0) {
                    correctUpdate(update, orderingMap)
                } else { 0 }
            }.reduce { sum, n -> sum + n }

            return result
        }

    }

}