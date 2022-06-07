package lab4.model

import tornadofx.ViewModel

class Cell(val x: Int, val y: Int)
class PairCells(val key: Cell, val value: Cell)

class MainModel: ViewModel() {

    private var startCell = Cell(1,1)
    private val needToBeRemoved = mutableListOf<PairCells>()
    private val roadCells = mutableMapOf<Cell, MutableList<Cell>>()
    var isNotReady = true
    var isGenerated = false

    fun initiateLabyrinth() {
        height = 19
        width = 35

        /** firstly the field is filled with walls
         * */
        for (i in 0 until height) {
            field.add(mutableListOf())  // initiate labyrinth with walls
            for (j in 0 until width)
                field.last().add('#')
        }

        /** the point where the generation starts from
         * */
        val x = /*(0 until width).random()*/ 19
        val y = /*(0 until height).random()*/ 9
        field[y][x] = '-'
        startCell = Cell(x,y)

        /** the coordinates of the roads are stored here
         *  arrays of their potential neighbors are stored in pairs with them
         */
        roadCells[startCell] = mutableListOf()

        /** hash symbols are walls
         *  dash symbols are roads
         *  question marks are potential neighbours
         */
        roadCells[startCell]?.addAll(findValidNeighbours(startCell))
        findValidNeighbours(startCell).forEach {
            field[it.y][it.x] = '?'
        }
    }

    /**
     * this is one iteration of the maze generation
     */
    fun build() {

        /**
        *   a random existing road is selected, a random potential neighbor is selected from it
        */
        val curCell = roadCells.asSequence().shuffled().first().key
        val neighbour = roadCells[curCell]!!.asSequence().shuffled().first()

        /**
         *   a new road is being created
         */
        createRoad(curCell, neighbour)

        /**
         *   the list of roads is being updated
         */
        roadCells[neighbour] = mutableListOf()

        /**
         *   the list of potential neighbors is updated
         *   if the road has no potential neighbors, it is removed from the list
         */
        if (findValidNeighbours(neighbour).isNotEmpty()) {
            roadCells[neighbour]?.addAll(findValidNeighbours(neighbour))
            findValidNeighbours(neighbour).forEach {
                field[it.y][it.x] = '?'
            }
        } else roadCells.remove(neighbour)

        /**
         *   a potential neighbor has become the road
         */
        field[neighbour.y][neighbour.x] = '-'
        roadCells[curCell]?.remove(neighbour)
        if (roadCells[curCell]!!.isEmpty())
            roadCells.remove(curCell)

        /**
         *   after updating the road the neighbors will double-check
         *   the neighbor cell of the potential neighbor cannot be a road
         */
        roadCells.forEach { (key, value) ->
            value.forEach { cell ->
                if (!checkNeighbourhood(cell.x, cell.y)) {
                    field[cell.y][cell.x] = '#'
                    needToBeRemoved.add(PairCells(key, cell))
                }
            }
        }

        /**
         *   invalid neighbors are removed
         */
        needToBeRemoved.forEach { delCell ->
            roadCells[delCell.key]!!.remove(delCell.value)
            if (roadCells[delCell.key]!!.isEmpty())
                roadCells.remove(delCell.key)
        }
        needToBeRemoved.clear()

        if (roadCells.isEmpty())
            isNotReady = false
        isGenerated = true
    }

    fun createStartAndEnd() {
        for (i in 0 until height) {
            if (field[i][1] == '-')
                playerY = i
        }
        field[playerY][playerX] = 'P'
        for (i in 0 until height)
            if (field[i][width - 2] == '-') {
                field[i][width - 1] = 'E'
                break
            }
    }

    private fun createRoad(curCell: Cell, neighbour: Cell) {
        field[neighbour.y][neighbour.x] = '-'
        val newY = curCell.y + (neighbour.y - curCell.y)/2
        val newX = curCell.x + (neighbour.x - curCell.x)/2
        field[newY][newX] = '-'
    }

    private fun findValidNeighbours(curCell : Cell): Collection<Cell> {
        val c = mutableListOf<Cell>()
        if (curCell.x > 1 && field[curCell.y][curCell.x - 2] == '#' && checkNeighbourhood(curCell.x - 2, curCell.y)) // left neighbour
            c.add(Cell(curCell.x - 2, curCell.y))
        if (curCell.x < width - 2 && field[curCell.y][curCell.x + 2] == '#' && checkNeighbourhood(curCell.x + 2, curCell.y)) // right neighbour
            c.add(Cell(curCell.x + 2, curCell.y))
        if (curCell.y > 1 && field[curCell.y - 2][curCell.x] == '#' && checkNeighbourhood(curCell.x, curCell.y - 2)) // top neighbour
            c.add(Cell(curCell.x, curCell.y - 2))
        if (curCell.y < height - 2 && field[curCell.y + 2][curCell.x] == '#' && checkNeighbourhood(curCell.x, curCell.y + 2)) // bottom neighbour
            c.add(Cell(curCell.x, curCell.y + 2))
        return c
    }

    private fun checkNeighbourhood(x: Int, y: Int): Boolean {
        for (i in -1..1)
            for (j in -1..1)
                if (x + i >= 0 && y + j >= 0 &&
                    x + i <= width - 1 && y + j <= height - 1 &&
                    field[y + j][x + i] == '-')
                    return false
        return true
    }

    var height = 0
    var width = 0
    var playerX = 0
    var playerY = 0
    var initialized = false

    var field = mutableListOf<MutableList<Char>>()
}