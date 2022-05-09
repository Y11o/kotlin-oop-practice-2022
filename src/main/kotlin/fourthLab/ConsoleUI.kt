package fourthLab

class ConsoleUI(private val mazeModel: Model) {
    init {
        val listener = object : ModelChangeListener {
            override fun onModelChanged() {
                repaint()
            }
        }
        mazeModel.addModelChangeListener(listener)
        repaint()
    }

    private fun repaint() {
        println(mazeModel)
        if (mazeModel.state)
            println("Congratulations! You have reached the exit!")
    }
}