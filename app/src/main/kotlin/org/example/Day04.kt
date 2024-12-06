package org.example

class Day04 {

    companion object {

        fun getSEDiagFrom(lines: List<String>, x: Int, y: Int): String {
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

        fun getSWDiagFrom(lines: List<String>, x: Int, y: Int): String {
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

        fun countMatches(matches: Sequence<MatchResult>): Int {
            var n = 0
            for (m in matches) {
                if (m.value.equals("XMASAMX") || m.value.equals("SAMXMAS")) { n += 2 }
                else { n += 1 }
            }

            return n
        } 

        fun part1(): Int {
            val rows = read_lines("src/main/resources/day04.txt")
            val cols: List<String> = rows[0].indices.map { y: Int ->
                rows.indices.map { x -> rows[x][y] }.joinToString("")
            }

            val LastCol = cols.size-1
            val LastRow = rows.size-1
            val seDiags: List<String> = (0..LastCol).map { c: Int -> getSEDiagFrom(rows, 0, c) } + (1..LastRow).map { r: Int -> getSEDiagFrom(rows, r, 0) }
            val swDiags: List<String> = (LastCol.downTo(0)).map { c: Int -> getSWDiagFrom(rows, 0, c) } + (LastRow.downTo(1)).map { r: Int -> getSWDiagFrom(rows, r, LastCol) }

            val rgx = """(XMASAMX)|(SAMXMAS)|(XMAS)|(SAMX)""".toRegex()
            val horizCounts = rows.map { str: String -> countMatches(rgx.findAll(str)) }.reduce { sum, n -> sum + n }
            val vertCounts = cols.map { str: String -> countMatches(rgx.findAll(str)) }.reduce { sum, n -> sum + n }
            val seDiagCounts = seDiags.map { str: String -> countMatches(rgx.findAll(str)) }.reduce { sum, n -> sum + n }
            val swDiagCounts = swDiags.map { str: String -> countMatches(rgx.findAll(str)) }.reduce { sum, n -> sum + n }

            return horizCounts + vertCounts + seDiagCounts + swDiagCounts
        }

        fun part2(): Int {
            // TODO

            return 0
        }

    }

}