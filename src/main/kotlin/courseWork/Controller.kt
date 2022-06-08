package courseWork

class Controller (private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        while (model.state == State.MOVE_MODE) {
            val input = readln()
            try {
                val col = input.substringBefore(" ").toInt() - 1
                val row = input.substringAfter(" ").toInt() - 1
                model.doMove(col * row )
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}