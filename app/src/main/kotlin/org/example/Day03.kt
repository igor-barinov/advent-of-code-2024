package org.example

class Day03 {

    companion object {

        fun execMul(stmt: String): Int {
            val argStr = stmt.substring(4, stmt.length - 1) // a,b
            val args = argStr.split(",").map { s: String -> s.toInt() } // [a, b]
            return args[0] * args[1]
        }

        fun part1(): Int {
            val str = read_text("src/main/resources/day03.txt")

            val regex = """mul\(\d+,\d+\)""".toRegex()

            val matches = regex.findAll(str)

            return matches.map { execMul(it.value) }.reduce { sum, n -> sum + n }
        }

        fun part2(): Int {
            val str = read_text("src/main/resources/day03.txt")

            val regex = """(mul\(\d+,\d+\))|(don't\(\))|(do\(\))""".toRegex()

            val matches = regex.findAll(str)

            var sum = 0
            var doMul = true
            for (match in matches) {

                val stmt = match.value

                if (stmt.equals("do()")) { // do()
                    doMul = true
                } else if (stmt.equals("don't()")) { // don't()
                    doMul = false
                } else if (doMul) { // mul(a,b)
                    sum += execMul(stmt)
                }

            }

            return sum
        }

    }

}