package fourthLab

class Controller(private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        var saveGameFlag = false
        while (!model.getState() && !saveGameFlag) {
            val input = readln()
            for (i in input.indices) {
                try {
                    when (input[i]) {
                        'w' -> model.doMove(Movement.UP)
                        's' -> model.doMove(Movement.DOWN)
                        'a' -> model.doMove(Movement.LEFT)
                        'd' -> model.doMove(Movement.RIGHT)
                        'e' -> {
                            println("Name file to save progress")
                            val fileName = readln()
                            model.writeMazeToFile(fileName)
                            saveGameFlag = true
                            break
                        }
                        else -> Movement.DO_NOTHING
                    }
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    }
}