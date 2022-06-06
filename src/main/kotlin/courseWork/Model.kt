package courseWork

enum class Field(private val textValue: String){
    BOMB("U+1F4A3"),
    EMPTY(" "),
    FLAG("U+1F6A9"),
    N_ONE("TODO"),
    N_TWO("TODO"),
    N_THREE("TODO"),
    N_FOUR("TODO"),
    N_FIVE("TODO"),
    N_SIX("TODO"),
    N_SEVEN("TODO"),
    N_EIGHT("TODO")
}

enum class State(private val textValue: String){
    WIN("U+1F973"),
    MOVE_MODE("U+1F600"),
    LOSE("U+1F92F"),
    FLAG_MODE("U+1F6A9")
}

const val TOTAL_BOMBS = 10
const val BOARD_SIZE = 10
private val FIRST_MOVE = State.MOVE_MODE

val GAME_NOT_FINISHED = setOf(State.MOVE_MODE, State.FLAG_MODE)

interface ModelChangeListener {
    fun onModelChanged()
}

/*class Model {
    private val _board: MutableList<MutableList<Field>> = initBoard()
    val board: List<List<Field>>
        get() = _board




    private fun initBoard(): MutableList<MutableList<Field>> {
        MutableList(BOARD_SIZE) { Field.EMPTY }
        var bombCounter = 0
        while (bombCounter < TOTAL_BOMBS){

        }

    }*/

    ///kdvgfsekgfrsl
    ///dlkfgrl
    ///

}