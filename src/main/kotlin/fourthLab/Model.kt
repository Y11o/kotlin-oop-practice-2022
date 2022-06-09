package fourthLab

import kotlinx.serialization.Serializable

interface ModelChangeListener {
    fun onModelChanged()
}

enum class Movement(private val textValue: String) {
    UP("Up"),
    DOWN("Down"),
    LEFT("Left"),
    RIGHT("Right"),
    DO_NOTHING("");

    override fun toString(): String = textValue
}


@Serializable
class Model(private val mazeModel: List<MutableList<Field>>) {
    @Serializable
    enum class Field(private val textValue: String) {
        WALL("#"),
        EMPTY("-"),
        PLAYER("P"),
        FINISH("E");

        override fun toString(): String = textValue
    }

    var state: Boolean
        private set

    private var currY: Int
    private var currX: Int
    private var mazeMap: List<MutableList<Field>> = mazeModel
        private set

    init {
        currY = -1
        currX = -1
        state = true
        for (i in 0 until mazeMap.size) {
            for (j in 0 until mazeMap[0].size) {
                if (mazeMap[i][j] == Field.PLAYER) {
                    currY = i
                    currX = j
                }
                if (mazeMap[i][j] == Field.FINISH)
                    state = false
            }
        }
        if (currX == -1 || currY == -1) throw IllegalArgumentException("Maze should have player")
    }

    private val listeners: MutableSet<ModelChangeListener> = mutableSetOf()
    fun writeMazeToFile(fileName: String) = ModelSerialization().serializationToFile(mazeMap, fileName)
    fun addModelChangeListener(listener: ModelChangeListener) = listeners.add(listener)
    private fun notifyListeners() = listeners.forEach { it.onModelChanged() }
    fun removeModelChangeListener(listener: ModelChangeListener) = listeners.remove(listener)


    private fun isCellEmpty(i: Int, j: Int) =
        i >= 0 && j >= 0 && i < mazeMap.size && j < mazeMap[0].size && (mazeMap[i][j] == Field.EMPTY || mazeMap[i][j] == Field.FINISH)

    private fun moveFlag(i: Int, j: Int, prevCell: Movement) {

        if (isCellEmpty(i, j)) {
            var prevY = currY
            var prevX = currX
            when (prevCell) {
                Movement.UP -> (prevY++)
                Movement.DOWN -> (prevY--)
                Movement.LEFT -> (prevX++)
                Movement.RIGHT -> (prevX--)
            }
            state = (mazeMap[i][j] == Field.FINISH)
            mazeMap[prevY][prevX] = Field.EMPTY
            mazeMap[i][j] = Field.PLAYER
            currY = i
            currX = j
        } else
            throw IllegalArgumentException("End of the map or the wall! Try to move in another direction")

    }

    fun doMove(currMove: Movement) {
        require(!state) { "Game was finished" }
        when (currMove) {
            Movement.UP -> moveFlag(--currY, currX, Movement.UP)
            Movement.DOWN -> moveFlag(++currY, currX, Movement.DOWN)
            Movement.LEFT -> moveFlag(currY, --currX, Movement.LEFT)
            Movement.RIGHT -> moveFlag(currY, ++currX, Movement.RIGHT)
            Movement.DO_NOTHING -> moveFlag(currY, currX, Movement.DO_NOTHING)
        }
        notifyListeners()
    }

    override fun toString(): String {
        return buildString {
            mazeMap.forEach { line ->
                line.forEach {
                    if (it == Field.PLAYER) append(it)
                    else
                        append(it)
                }
                appendLine()
            }
        }
    }
}
