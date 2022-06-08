package courseWork

class Controller (private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        while (model.state in GAME_NOT_FINISHED) {
            val input = readln()
            try {
                val col = input.substringBefore(" ").toInt() - 1
                val row = input.substringAfter(" ").toInt() - 1
                model.emptyCell(col * row + 1)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}