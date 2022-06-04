package coursework.model

import javafx.collections.ObservableList
import tornadofx.ViewModel
import tornadofx.observableListOf
import java.util.Random
import kotlin.properties.Delegates

class MainModel: ViewModel() {
    var field = observableListOf<ObservableList<Boolean>>()
    var height by Delegates.notNull<Double>()
    var width by Delegates.notNull<Double>()

    private var cols = 0
    private var rows = 0

    fun initiate(res : Double, dens : Int) {
        var i = 0.0
        var index = 0
        while (i <= height - res) {
            field.add(observableListOf())
            var u = 0.0
            while (u <= width - res) {
                field[index].add(Random().nextInt(dens) == 0)
                u+=res
            }
            index++
            i+=res
            cols = (u/res).toInt()
        }
        rows = (i/res).toInt()
    }

    private fun countNeighbours(x : Int, y : Int) : Int {
        var count = 0
        for (i in -1..1)
        {
            for (j in -1..1)
            {
                val col : Int = (x + i + cols) % cols
                val row : Int = (y + j + rows) % rows
                val isSelfCheck = (col == x && row == y)

                val isHasLife: Boolean = field[row][col]

                if (isHasLife && !isSelfCheck)
                    count++
            }
        }
        return count
    }

    fun createCycle() {
        val newField = observableListOf<ObservableList<Boolean>>()
        var i = 0
        while (i < rows) {
            newField.add(observableListOf())
            var u = 0
            while (u < cols) {
                newField[i].add(false)
                u++
            }
            i++
        }

        field.forEachIndexed { lineIndex, line->
            line.forEachIndexed { itemIndex, _ ->
                val count = countNeighbours(itemIndex, lineIndex)
                val hasLife = field[lineIndex][itemIndex]

                if (!hasLife && (count == 3))
                    newField[lineIndex][itemIndex] = true
                else if (hasLife && (count == 2 || count == 3))
                    newField[lineIndex][itemIndex] = field[lineIndex][itemIndex]
            }
        }
        field.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { itemIndex, _ ->
                field[lineIndex][itemIndex] = newField[lineIndex][itemIndex]
            }
        }
    }

    fun clear() {
        field.forEach {
            it.clear()
        }
    }
}