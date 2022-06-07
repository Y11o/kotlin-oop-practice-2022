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

    private fun checkNum(check: Int): Boolean{
        return check == 1 || check == 2 || check == 3 || check == 4 || check == 5 || check == 6 || check == 7 || check == 8
    }

    private fun num(check: Int) : Field{
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

                if (checkNum(MinesNearby) == true) bufMinesNearby = Num(MinesNearby)

                role[index] = bufMinesNearby
                MinesNearby = 0
            }
        }
    }

    private fun emptyCell(index: Int){
        cellOpen[index] = true
        if (index - BOARD_SIZE >= 0 && role[index] == Field.EMPTY) { //верхняя полоса
            if (role[index - BOARD_SIZE] != Field.BOMB && cellOpen[index - BOARD_SIZE] == true) { //если не бомба и не открыта
                if (checkNum(role[index - BOARD_SIZE])) //если число
                    cellOpen[index - BOARD_SIZE] = true
                else {
                    if (role[index - width_sq] == empty_sq and cellOpen[index - width_sq] == close_cell) //если пустая клетка и она ещё не открыта
                        cellOpen[index - width_sq] = open_cell //открываем клетку
                    emptyCell(width_sq, height_sq, index - width_sq)
                }
            }
            if (index - width_sq - 1 > 0 and (index - width_sq - 1) % width_sq != width_sq - 1 and arr_check[index - width_sq - 1] != sp_mine and arr_check_open[index - width_sq - 1] == close_cell) { //если не бомба и не открыта
                if (arr_check[index - width_sq - 1] >= one and arr_check[index - width_sq - 1] <= eight) //если число
                    arr_check_open[index - width_sq - 1] = open_cell;
                else {
                    if (arr_check[index - width_sq - 1] == empty_sq) //если пустая клетка
                        arr_check_open[index - width_sq - 1] = open_cell; //открываем клетку
                    empty_cell(width_sq, height_sq, index - width_sq - 1);
                }
            }
            if (arr_check[index - width_sq + 1] != sp_mine and index - width_sq + 1 < width_sq * height_sq and arr_check_open[index - width_sq + 1] == close_cell and (index - width_sq + 1) % width_sq != 0) { //если не бомба и не открыта
                if (arr_check[index - width_sq + 1] >= one and arr_check[index - width_sq + 1] <= eight) //если число
                    arr_check_open[index - width_sq + 1] = open_cell;
                else {
                    if (arr_check[index - width_sq + 1] == empty_sq) //если пустая клетка
                        arr_check_open[index - width_sq + 1] = open_cell; //открываем клетку
                    empty_cell(width_sq, height_sq, index - width_sq + 1);
                }
            }
        }
        if (index + width_sq < width_sq * height_sq and arr_check[index] == empty_sq) { //нижняя полоса
            if (arr_check[index + width_sq] != sp_mine and arr_check_open[index + width_sq] == close_cell) { //если не бомба и не открыта
                if (arr_check[index + width_sq] >= one and arr_check[index + width_sq] <= eight) //если число
                    arr_check_open[index + width_sq] = open_cell;
                else {
                    if (arr_check[index + width_sq] == empty_sq) //если пустая клетка
                        arr_check_open[index + width_sq] = open_cell; //открываем клетку
                    empty_cell(width_sq, height_sq, index + width_sq);
                }
            }
            if (arr_check[index + width_sq - 1] != sp_mine and index + width_sq - 1 > 0 and (index + width_sq - 1) % width_sq != width_sq - 1 and arr_check_open[index + width_sq - 1] == close_cell) { //если не бомба и не открыта
                if (arr_check[index + width_sq - 1] >= one and arr_check[index + width_sq - 1] <= eight) //если число
                    arr_check_open[index + width_sq - 1] = open_cell;
                else {
                    if (arr_check[index + width_sq - 1] == empty_sq) //если пустая клетка
                        arr_check_open[index + width_sq - 1] = open_cell; //открываем клетку
                    empty_cell(width_sq, height_sq, index + width_sq - 1);
                }
            }
            if (arr_check[index + width_sq + 1] != sp_mine and index + width_sq + 1 < width_sq * height_sq and (index + width_sq + 1) % width_sq != 0 and arr_check_open[index + width_sq + 1] == close_cell) { //если не бомба и не октрыта
                if (arr_check[index + width_sq + 1] >= one and arr_check[index + width_sq + 1] <= eight) //если число
                    arr_check_open[index + width_sq + 1] = open_cell;
                else {
                    if (arr_check[index + width_sq + 1] == empty_sq) //если пустая клетка
                        arr_check_open[index + width_sq + 1] = open_cell; //открываем клетку
                    empty_cell(width_sq, height_sq, index + width_sq + 1);
                }
            }
        }
        if (index - 1 > 0 and (index - 1) % width_sq != width_sq - 1 and arr_check[index] == empty_sq) { //левая полоса
            if (arr_check[index - 1] != sp_mine and arr_check_open[index - 1] == close_cell) //если не бомба и не открыта
            {
                if (arr_check[index - 1] >= one and arr_check[index - 1] <= eight) //если число
                    arr_check_open[index - 1] = open_cell;
                else {
                    if (arr_check[index - 1] == empty_sq) //если пустая клетка
                        arr_check_open[index - 1] = open_cell; //открываем клетку
                    empty_cell(width_sq, height_sq, index - 1);
                }
            }
        }
        if (index + 1 < width_sq * height_sq and (index + 1) % width_sq != 0 and arr_check[index] == empty_sq) { //правая полоса
            if (arr_check[index + 1] != sp_mine and arr_check_open[index + 1] == close_cell) //если не бомба и не открыта
            {
                if (arr_check[index + 1] >= one and arr_check[index + 1] <= eight) //если число
                    arr_check_open[index + 1] = open_cell;
                else {
                    if (arr_check[index + 1] == empty_sq) //если пустая клетка
                        arr_check_open[index + 1] = open_cell; //открываем клетку
                    empty_cell(width_sq, height_sq, index + 1);
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