package org.example

import java.util.UUID


class Day06 {

    enum class CellType {
        EMPTY,          // .
        OBSTACLE,       // #
        GUARD_N,        // ^
        GUARD_E,        // >
        GUARD_S,        // v
        GUARD_W,        // <
        PATH            // X
    }

    class Cell(type: CellType, north: Cell?, west: Cell?, south: Cell?, east: Cell?) {
        var type: CellType = type
            get
            set
        var north: Cell? = north
            get
            set
        var west: Cell? = west
            get
            set
        var south: Cell? = south
            get
            set
        var east: Cell? = east
            get
            set
        val id: UUID = UUID.randomUUID()

        fun rotate(): Cell {

            when (type) {
                CellType.GUARD_N -> type = CellType.GUARD_E
                CellType.GUARD_E -> type = CellType.GUARD_S
                CellType.GUARD_S -> type = CellType.GUARD_W
                CellType.GUARD_W -> type = CellType.GUARD_N
                else -> {}
            }

            return this
        }

        fun adjacentCell(): Cell? {

            return when (type) {
                CellType.GUARD_N -> this.north
                CellType.GUARD_E -> this.east
                CellType.GUARD_S -> this.south
                CellType.GUARD_W -> this.west
                else -> null
            }

        }

        fun beenVisited(): Boolean {
            return when (type) {
                CellType.EMPTY -> false
                CellType.OBSTACLE -> false
                else -> true
            }
        }
    }

    companion object {

        fun charToCell(c: Char): Cell {

            val type = when (c) {
                '.' -> CellType.EMPTY
                '#' -> CellType.OBSTACLE
                '^' -> CellType.GUARD_N
                'X' -> CellType.PATH
                else -> CellType.EMPTY
            }

            return Cell(type, null, null, null, null)
        }

        fun readInput(): Cell {

            val lines = read_lines("src/main/resources/day06.txt")
            val rows = lines.map { it.map { charToCell(it) } }

            var guardCell = rows[0][0]
            rows.forEach {
                for (i in 0..it.size-2) {
                    val c1 = it[i]
                    val c2 = it[i+1]

                    c1.east = c2
                    c2.west = c1

                    if (c1.type == CellType.GUARD_N) {
                        guardCell = c1
                    }
                }
            }

            for (i in 0..rows.size-2) {
                for (j in rows[i].indices) {
                    val c1 = rows[i][j]
                    val c2 = rows[i+1][j]

                    c1.south = c2
                    c2.north = c1
                }
            }

            return guardCell
        }

        fun moveGuard(guardCell: Cell): Cell? {

            val nextCell = guardCell.adjacentCell()

            if (nextCell == null) { return null }

            if (nextCell.type == CellType.OBSTACLE) {
                return guardCell.rotate()
            }

            val guardDir = guardCell.type
            guardCell.type = CellType.PATH
            nextCell.type = guardDir
            return nextCell
        }

        fun countPath(startCell: Cell): Int {

            val visitedCells: HashSet<UUID> = hashSetOf()
            val queue: ArrayDeque<Cell?> = ArrayDeque()
            queue.addLast(startCell)

            var count = 0
            while (!queue.isEmpty()) {
                val nextCell = queue.removeFirst()

                if (nextCell == null) { continue }

                if (!visitedCells.contains(nextCell.id)) {

                    if (nextCell.beenVisited()) {
                        count += 1
                    }

                    visitedCells.add(nextCell.id)

                    queue.addLast(nextCell.north)
                    queue.addLast(nextCell.east)
                    queue.addLast(nextCell.south)
                    queue.addLast(nextCell.west)
                }
            }
            
            return count
        }

        fun part1(): Int {

            val startCell = readInput()
            var guardCell: Cell? = startCell

            while (guardCell != null) {
                guardCell = moveGuard(guardCell)
            }

            return countPath(startCell)

        }

        fun part2(): Int {
            return 0
        }

    }

}