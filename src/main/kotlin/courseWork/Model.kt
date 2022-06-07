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

enum class GameState(private val textValue: String){
    WIN("U+1F973"),
    MOVE_MODE("U+1F600"),
    LOSE("U+1F92F"),
    FLAG_MODE("U+1F6A9")
}

const val TOTAL_BOMBS = 10
const val BOARD_SIZE = 10
private val FIRST_MOVE = GameState.MOVE_MODE

val GAME_NOT_FINISHED = setOf(GameState.MOVE_MODE, GameState.FLAG_MODE)

interface ModelChangeListener {
    fun onModelChanged()
}

class Model {
    private val _board: MutableList<MutableList<Field>> = initBoard()
    val board: List<List<Field>>
        get() = _board
    private val listeners:  MutableSet<ModelChangeListener> = mutableSetOf()

    fun addModelChangeListener(listener: ModelChangeListener) {
        listeners.add(listener)
    }

    fun removeModelChangeListener(listener: ModelChangeListener) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        listeners.forEach { it.onModelChanged() }
    }

    private val role = Array(BOARD_SIZE* BOARD_SIZE+1) { Field.EMPTY }
    private val cellOpen = Array(BOARD_SIZE* BOARD_SIZE + 1) {false}

    private fun checkNumN(check: Int): Boolean{
        return check == 1 || check == 2 || check == 3 || check == 4 || check == 5 || check == 6 || check == 7 || check == 8
    }

    private fun checkNumF(check: Field): Boolean {
        return check == Field.N_ONE || check == Field.N_TWO || check == Field.N_THREE || check == Field.N_FOUR
                || check == Field.N_FIVE || check == Field.N_SIX || check == Field.N_SEVEN|| check == Field.N_EIGHT
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
    }

    private fun mine(){ //функция закладывания бомб и подсчёта рядом лежащих бомб
        var cntMine = 0
        var positionMine: Int

        while (cntMine < TOTAL_BOMBS){
            positionMine = (1 until BOARD_SIZE* BOARD_SIZE).random()
            if (role[positionMine] != Field.BOMB)
            {
                role[positionMine] = Field.BOMB
                cntMine++
            }
        }

        var MinesNearby = 0

        for(index in 0 until BOARD_SIZE* BOARD_SIZE){
            if (role[index] != Field.BOMB){

                if (index - BOARD_SIZE >= 0) { //верхняя полоса
                    if (role[index - BOARD_SIZE] == Field.BOMB)
                        MinesNearby++
                    if (index - BOARD_SIZE - 1 > 0 && role[index - BOARD_SIZE - 1] == Field.BOMB && (index - BOARD_SIZE - 1) % BOARD_SIZE != BOARD_SIZE-1)
                        MinesNearby++
                    if (index - BOARD_SIZE + 1 < BOARD_SIZE* BOARD_SIZE && role[index - BOARD_SIZE + 1] == Field.BOMB && (index - BOARD_SIZE + 1) % BOARD_SIZE != 0)
                        MinesNearby++
                }

                if (index + BOARD_SIZE < BOARD_SIZE* BOARD_SIZE) { //нижняя полоса
                    if (role[index + BOARD_SIZE] == Field.BOMB)
                        MinesNearby++
                    if (index + BOARD_SIZE - 1 > 0 && role[index + BOARD_SIZE - 1] == Field.BOMB && (index + BOARD_SIZE - 1) % BOARD_SIZE != BOARD_SIZE-1)
                        MinesNearby++
                    if (index + BOARD_SIZE + 1 < BOARD_SIZE* BOARD_SIZE && role[index + BOARD_SIZE + 1] == Field.BOMB && (index + BOARD_SIZE + 1) % BOARD_SIZE != 0)
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

    private fun emptyCell(index: Int){
        cellOpen[index] = true
        if (index - BOARD_SIZE >= 0 && role[index] == Field.EMPTY) { //верхняя полоса
            if (role[index - BOARD_SIZE] != Field.BOMB && cellOpen[index - BOARD_SIZE]) { //если не бомба и не открыта
                if (checkNumF(role[index - BOARD_SIZE])) //если число
                    cellOpen[index - BOARD_SIZE] = true
                else {
                    if (role[index - BOARD_SIZE] == Field.EMPTY && !cellOpen[index - BOARD_SIZE]) //если пустая клетка и она ещё не открыта
                        cellOpen[index - BOARD_SIZE] = true //открываем клетку
                    emptyCell(index - BOARD_SIZE)
                }
            }

            if (index - BOARD_SIZE - 1 > 0 && (index - BOARD_SIZE - 1) % BOARD_SIZE != BOARD_SIZE - 1 && role[index - BOARD_SIZE - 1] != Field.BOMB && !cellOpen[index - BOARD_SIZE - 1]) { //если не бомба и не открыта
                if (checkNumF(role[index - BOARD_SIZE - 1])) //если число
                    cellOpen[index - BOARD_SIZE - 1] = true;
                else {
                    if (role[index - BOARD_SIZE - 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index - BOARD_SIZE - 1] = true; //открываем клетку
                    emptyCell(index - BOARD_SIZE - 1);
                }
            }

            if (role[index - BOARD_SIZE + 1] != Field.BOMB && index - BOARD_SIZE + 1 < BOARD_SIZE * BOARD_SIZE && !cellOpen[index - BOARD_SIZE + 1] && (index - BOARD_SIZE + 1) % BOARD_SIZE != 0) { //если не бомба и не открыта
                if (checkNumF(role[index - BOARD_SIZE + 1])) //если число
                    cellOpen[index - BOARD_SIZE + 1] = true;
                else {
                    if (role[index - BOARD_SIZE + 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index - BOARD_SIZE + 1] = true; //открываем клетку
                    emptyCell(index - BOARD_SIZE + 1);
                }
            }
        }

        if (index + BOARD_SIZE < BOARD_SIZE * BOARD_SIZE && role[index] == Field.EMPTY) { //нижняя полоса
            if (role[index + BOARD_SIZE] != Field.BOMB && !cellOpen[index + BOARD_SIZE]) { //если не бомба и не открыта
                if (checkNumF(role[index + BOARD_SIZE])) //если число
                    cellOpen[index + BOARD_SIZE] = true;
                else {
                    if (role[index + BOARD_SIZE] == Field.EMPTY) //если пустая клетка
                        cellOpen[index + BOARD_SIZE] = true; //открываем клетку
                    emptyCell(index + BOARD_SIZE);
                }
            }
            if (role[index + BOARD_SIZE - 1] != Field.BOMB && index + BOARD_SIZE - 1 > 0 && (index + BOARD_SIZE - 1) % BOARD_SIZE != BOARD_SIZE - 1 && !cellOpen[index + BOARD_SIZE - 1]) { //если не бомба и не открыта
                if (checkNumF(role[index + BOARD_SIZE - 1])) //если число
                    cellOpen[index + BOARD_SIZE - 1] = true;
                else {
                    if (role[index + BOARD_SIZE - 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index + BOARD_SIZE - 1] = true; //открываем клетку
                    emptyCell(index + BOARD_SIZE - 1);
                }
            }
            if (role[index + BOARD_SIZE + 1] != Field.BOMB && index + BOARD_SIZE + 1 < BOARD_SIZE * BOARD_SIZE && (index + BOARD_SIZE + 1) % BOARD_SIZE != 0 && !cellOpen[index + BOARD_SIZE + 1]) { //если не бомба и не октрыта
                if (checkNumF(role[index + BOARD_SIZE + 1])) //если число
                    cellOpen[index + BOARD_SIZE + 1] = true;
                else {
                    if (role[index + BOARD_SIZE + 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index + BOARD_SIZE + 1] = true; //открываем клетку
                    emptyCell(index + BOARD_SIZE + 1);
                }
            }
        }
        if (index - 1 > 0 && (index - 1) % BOARD_SIZE != BOARD_SIZE - 1 && role[index] == Field.EMPTY) { //левая полоса
            if (role[index - 1] != Field.BOMB && !cellOpen[index - 1]) //если не бомба и не открыта
            {
                if (checkNumF(role[index - 1])) //если число
                    cellOpen[index - 1] = true;
                else {
                    if (role[index - 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index - 1] = true; //открываем клетку
                    emptyCell(index - 1);
                }
            }
        }
        if (index + 1 < BOARD_SIZE * BOARD_SIZE && (index + 1) % BOARD_SIZE != 0 && role[index] == Field.EMPTY) { //правая полоса
            if (role[index + 1] != Field.BOMB && !cellOpen[index + 1]) //если не бомба и не открыта
            {
                if (checkNumF(role[index + 1])) //если число
                    cellOpen[index + 1] = true;
                else {
                    if (role[index + 1] == Field.EMPTY) //если пустая клетка
                        cellOpen[index + 1] = true; //открываем клетку
                    emptyCell(index + 1);
                }
            }
        }
    }
}

    private fun initBoard() {
        MutableList(BOARD_SIZE) { Field.EMPTY }
        var bombCounter = 0
      /*  while (bombCounter < TOTAL_BOMBS){

        }*/

    }

}