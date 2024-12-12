package org.example

class Day07 {

    class Equation(testValue: Long, args: List<Long>) {
        val testValue = testValue
            get
        val args = args
            get


        fun isPossible1(): Boolean {
            return `_isPossibleHelper1`(this.args[0], 1)
        }

        fun isPossible2(): Boolean {
            return `_isPossibleHelper2`(this.args[0], 1)
        }

        fun _isPossibleHelper1(accValue: Long, index: Int): Boolean {

            if (index >= this.args.size) {
                return accValue == this.testValue
            }

            val possibleWithSum = `_isPossibleHelper1`(accValue + this.args[index], index+1)
            val possibleWithMult = `_isPossibleHelper1`(accValue * this.args[index], index+1)

            return possibleWithSum || possibleWithMult
        }

        fun _isPossibleHelper2(accValue: Long, index: Int): Boolean {

            if (index >= this.args.size) {
                return accValue == this.testValue
            }

            val possibleWithSum = `_isPossibleHelper2`(accValue + this.args[index], index+1)
            val possibleWithMult = `_isPossibleHelper2`(accValue * this.args[index], index+1)
            val possibleWithConcat = `_isPossibleHelper2`(concatLongs(accValue, this.args[index]), index+1)

            return possibleWithSum || possibleWithMult || possibleWithConcat
        }
    }

    companion object {

        fun concatLongs(a: Long, b: Long): Long {

            val aStr = a.toString()
            val bStr = b.toString()

            return (aStr + bStr).toLong()

        }

        fun readInput(): List<Equation> {

            val lines = read_lines("src/main/resources/day07.txt")

            return lines.map {
                val parts = it.split(": ")
                val testVal = parts[0].toLong()
                val args = parts[1].split(" ").map { it.toLong() }

                Equation(testVal, args)
            }

        }

        fun part1(): Long {

            val eqs = readInput()

            return eqs.filter { it.isPossible1() }.map { it.testValue }.reduce { sum, n -> sum + n }

        }

        fun part2(): Long {
            val eqs = readInput()

            return eqs.filter { it.isPossible2() }.map { it.testValue }.reduce { sum, n -> sum + n }
        }



    }

}