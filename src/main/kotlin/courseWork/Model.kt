package courseWork

fun getEmoji(unicode: Int): String {
    return String(Character.toChars(unicode))
}

enum class Field(val textValue: String) {
    BOMB( getEmoji(0x1F60A)),
    EMPTY(" "),
    N_ONE("1"),
    N_TWO("2"),
    N_THREE("3"),
    N_FOUR("4"),
    N_FIVE("5"),
    N_SIX("6"),
    N_SEVEN("7"),
    N_EIGHT("8")
}

enum class CellState(val index: Int){
    CLOSE(0),
    OPEN(1),
    //FLAG(2)
}

enum class State(val textValue: String) {
    WIN("WIN"), //0x1F973
    MOVE_MODE("MOVE"), //0x1F600
    LOSE("LOSE"), //0x1F92F
    //FLAG_MODE("FLAG") //0x1F6A9
}

const val TOTAL_BOMBS = 10
const val BOARD_SIZE = 10
interface ModelChangeListener {
    fun onModelChanged()
}

val GAME_NOT_FINISHED = setOf(State.MOVE_MODE/*, State.FLAG_MODE*/)

private val FIRST_MOVE = State.MOVE_MODE

class Model {

    var role = Array(BOARD_SIZE * BOARD_SIZE + 1) { Field.EMPTY }
        private set
    var cellOpen = Array(BOARD_SIZE * BOARD_SIZE + 1) { CellState.CLOSE }
        private set
    var state: State = FIRST_MOVE
        private set

//    fun stateChanger(newState: State){
//        state = newState
//    }

    private var flagsCounter = 0
    init {
        mine()
    }

    private val listeners: MutableSet<ModelChangeListener> = mutableSetOf()

    fun addModelChangeListener(listener: ModelChangeListener) {
        listeners.add(listener)
    }

    fun removeModelChangeListener(listener: ModelChangeListener) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        listeners.forEach { it.onModelChanged() }
    }

    private fun checkNumN(check: Int): Boolean {
        return check == 1 || check == 2 || check == 3 || check == 4 || check == 5 || check == 6 || check == 7 || check == 8
    }

    private fun checkNumF(check: Field): Boolean {
        return check == Field.N_ONE || check == Field.N_TWO || check == Field.N_THREE || check == Field.N_FOUR
                || check == Field.N_FIVE || check == Field.N_SIX || check == Field.N_SEVEN || check == Field.N_EIGHT
    }

    private fun num(check: Int): Field {
        if (check == 1) return Field.N_ONE
        if (check == 2) return Field.N_TWO
        if (check == 3) return Field.N_THREE
        if (check == 4) return Field.N_FOUR
        if (check == 5) return Field.N_FIVE
        if (check == 6) return Field.N_SIX
        if (check == 7) return Field.N_SEVEN
        if (check == 8) return Field.N_EIGHT
        return Field.EMPTY
    }

    private fun mine() { //функция закладывания бомб и подсчёта рядом лежащих бомб
        var cntMine = 0
        var positionMine: Int

        while (cntMine < TOTAL_BOMBS) {
            positionMine = (1 until BOARD_SIZE * BOARD_SIZE).random()
            if (role[positionMine] != Field.BOMB) {
                role[positionMine] = Field.BOMB
                cntMine++
            }
        }

        var MinesNearby = 0

        for (index in 0 until BOARD_SIZE * BOARD_SIZE) {
            if (role[index] != Field.BOMB) {

                if (index - BOARD_SIZE >= 0) { //верхняя полоса
                    if (role[index - BOARD_SIZE] == Field.BOMB)
                        MinesNearby++
                    if (index - BOARD_SIZE - 1 > 0 && role[index - BOARD_SIZE - 1] == Field.BOMB && (index - BOARD_SIZE - 1) % BOARD_SIZE != BOARD_SIZE - 1)
                        MinesNearby++
                    if (index - BOARD_SIZE + 1 < BOARD_SIZE * BOARD_SIZE && role[index - BOARD_SIZE + 1] == Field.BOMB && (index - BOARD_SIZE + 1) % BOARD_SIZE != 0)
                        MinesNearby++
                }

                if (index + BOARD_SIZE < BOARD_SIZE * BOARD_SIZE) { //нижняя полоса
                    if (role[index + BOARD_SIZE] == Field.BOMB)
                        MinesNearby++
                    if (index + BOARD_SIZE - 1 > 0 && role[index + BOARD_SIZE - 1] == Field.BOMB && (index + BOARD_SIZE - 1) % BOARD_SIZE != BOARD_SIZE - 1)
                        MinesNearby++
                    if (index + BOARD_SIZE + 1 < BOARD_SIZE * BOARD_SIZE && role[index + BOARD_SIZE + 1] == Field.BOMB && (index + BOARD_SIZE + 1) % BOARD_SIZE != 0)
                        MinesNearby++
                }

                if (index - 1 > 0 && (index - 1) % BOARD_SIZE != BOARD_SIZE - 1) { //левая полоса
                    if (role[index - 1] == Field.BOMB) MinesNearby++
                }

                if (index + 1 < BOARD_SIZE * BOARD_SIZE && (index + 1) % BOARD_SIZE != 0) { //правая полоса
                    if (role[index + 1] == Field.BOMB) MinesNearby++
                }

                var bufMinesNearby = Field.EMPTY

                if (checkNumN(MinesNearby)) bufMinesNearby = num(MinesNearby)

                role[index] = bufMinesNearby
                MinesNearby = 0
            }
        }
    }

    private fun emptyCell(index: Int) {
        cellOpen[index] = CellState.OPEN
        if (index - BOARD_SIZE >= 0 && role[index] == Field.EMPTY) { //верхняя полоса
            if (role[index - BOARD_SIZE] != Field.BOMB && (cellOpen[index - BOARD_SIZE] == CellState.OPEN)) { //если не бомба и не открыта
                if (checkNumF(role[index - BOARD_SIZE])) //если число
                    cellOpen[index - BOARD_SIZE] = CellState.OPEN
                else {
                    if (role[index - BOARD_SIZE] == Field.EMPTY && cellOpen[index - BOARD_SIZE] == CellState.CLOSE) //если пустая клетка и она ещё не открыта
                        cellOpen[index - BOARD_SIZE] = CellState.OPEN //открываем клетку
                    emptyCell(index - BOARD_SIZE)
                }
            }

            if (index - BOARD_SIZE - 1 > 0 && (index - BOARD_SIZE - 1) % BOARD_SIZE != BOARD_SIZE - 1 && role[index - BOARD_SIZE - 1] != Field.BOMB && cellOpen[index - BOARD_SIZE - 1] == CellState.CLOSE) { //если не бомба и не открыта
                if (checkNumF(role[index - BOARD_SIZE - 1])) //если число
                    cellOpen[index - BOARD_SIZE - 1] = CellState.OPEN;
                else {
                    if (role[index - BOARD_SIZE - 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index - BOARD_SIZE - 1] = CellState.OPEN; //открываем клетку
                    emptyCell(index - BOARD_SIZE - 1);
                }
            }

            if (role[index - BOARD_SIZE + 1] != Field.BOMB && index - BOARD_SIZE + 1 < BOARD_SIZE * BOARD_SIZE && cellOpen[index - BOARD_SIZE + 1] == CellState.CLOSE && (index - BOARD_SIZE + 1) % BOARD_SIZE != 0) { //если не бомба и не открыта
                if (checkNumF(role[index - BOARD_SIZE + 1])) //если число
                    cellOpen[index - BOARD_SIZE + 1] = CellState.OPEN;
                else {
                    if (role[index - BOARD_SIZE + 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index - BOARD_SIZE + 1] = CellState.OPEN; //открываем клетку
                    emptyCell(index - BOARD_SIZE + 1);
                }
            }
        }

        if (index + BOARD_SIZE < BOARD_SIZE * BOARD_SIZE && role[index] == Field.EMPTY) { //нижняя полоса
            if (role[index + BOARD_SIZE] != Field.BOMB && cellOpen[index + BOARD_SIZE] == CellState.CLOSE) { //если не бомба и не открыта
                if (checkNumF(role[index + BOARD_SIZE])) //если число
                    cellOpen[index + BOARD_SIZE] = CellState.OPEN;
                else {
                    if (role[index + BOARD_SIZE] == Field.EMPTY) //если пустая клетка
                        cellOpen[index + BOARD_SIZE] = CellState.OPEN; //открываем клетку
                    emptyCell(index + BOARD_SIZE);
                }
            }
            if (role[index + BOARD_SIZE - 1] != Field.BOMB && index + BOARD_SIZE - 1 > 0 && (index + BOARD_SIZE - 1) % BOARD_SIZE != BOARD_SIZE - 1 && cellOpen[index + BOARD_SIZE - 1] == CellState.CLOSE) { //если не бомба и не открыта
                if (checkNumF(role[index + BOARD_SIZE - 1])) //если число
                    cellOpen[index + BOARD_SIZE - 1] = CellState.OPEN;
                else {
                    if (role[index + BOARD_SIZE - 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index + BOARD_SIZE - 1] = CellState.OPEN; //открываем клетку
                    emptyCell(index + BOARD_SIZE - 1);
                }
            }
            if (role[index + BOARD_SIZE + 1] != Field.BOMB && index + BOARD_SIZE + 1 < BOARD_SIZE * BOARD_SIZE && (index + BOARD_SIZE + 1) % BOARD_SIZE != 0 && cellOpen[index + BOARD_SIZE + 1] == CellState.CLOSE) { //если не бомба и не октрыта
                if (checkNumF(role[index + BOARD_SIZE + 1])) //если число
                    cellOpen[index + BOARD_SIZE + 1] = CellState.OPEN;
                else {
                    if (role[index + BOARD_SIZE + 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index + BOARD_SIZE + 1] = CellState.OPEN; //открываем клетку
                    emptyCell(index + BOARD_SIZE + 1);
                }
            }
        }
        if (index - 1 > 0 && (index - 1) % BOARD_SIZE != BOARD_SIZE - 1 && role[index] == Field.EMPTY) { //левая полоса
            if (role[index - 1] != Field.BOMB && cellOpen[index - 1] == CellState.CLOSE) //если не бомба и не открыта
            {
                if (checkNumF(role[index - 1])) //если число
                    cellOpen[index - 1] = CellState.OPEN;
                else {
                    if (role[index - 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index - 1] = CellState.OPEN; //открываем клетку
                    emptyCell(index - 1);
                }
            }
        }
        if (index + 1 < BOARD_SIZE * BOARD_SIZE && (index + 1) % BOARD_SIZE != 0 && role[index] == Field.EMPTY) { //правая полоса
            if (role[index + 1] != Field.BOMB && cellOpen[index + 1] == CellState.CLOSE) //если не бомба и не открыта
            {
                if (checkNumF(role[index + 1])) //если число
                    cellOpen[index + 1] = CellState.OPEN;
                else {
                    if (role[index + 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index + 1] = CellState.OPEN; //открываем клетку
                    emptyCell(index + 1);
                }
            }
        }
    }

    private fun checkState(){
        var emptyCounter = 0
        for (index in 0 until BOARD_SIZE* BOARD_SIZE){
            if (cellOpen[index] == CellState.CLOSE){
                if (role[index] != Field.BOMB) emptyCounter++
            }
        }
        if (emptyCounter == 0) state = State.WIN
    }

    fun doMove(index: Int){
        if (state == State.MOVE_MODE){
            if (cellOpen[index] == CellState.CLOSE){
                if (role[index] == Field.BOMB){
                    state = State.LOSE
                }else{
                    if (role[index] == Field.EMPTY) emptyCell(index)
                    else cellOpen[index] = CellState.OPEN
                }
            }
            checkState()
        }
//        if (state == State.FLAG_MODE){
//            if(cellOpen[index] == CellState.CLOSE) {
//                cellOpen[index] = CellState.FLAG
//                if (role[index] == Field.BOMB) flagsCounter++
//                if (flagsCounter == TOTAL_BOMBS) state = State.WIN
//            }
//            if (cellOpen[index] == CellState.FLAG){
//                cellOpen[index] = CellState.CLOSE
//                if (role[index] == Field.BOMB) flagsCounter--
//            }
//        }
        notifyListeners()
    }
}