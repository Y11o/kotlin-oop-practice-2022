package courseWork

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.GridLayout
import javax.swing.*

private const val GAP = 10

class UI : JFrame("Minesweeper"), ModelChangeListener {
    private var gameModel: Model = Model()
    private val statusLabel = JLabel("Status", JLabel.CENTER)
    private val buttons = mutableListOf<MutableList<JButton>>()

    init {
        setSize(1000, 1000)
        defaultCloseOperation = EXIT_ON_CLOSE

        updateFont(statusLabel, 20.0f)

        rootPane.contentPane = JPanel(BorderLayout(GAP, GAP)).apply {
            add(statusLabel, BorderLayout.NORTH)
            add(createBoardPanel(), BorderLayout.CENTER)
            add(createRestartButton(), BorderLayout.SOUTH)
            //add(createModeChanger(), BorderLayout.WEST)
            border = BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP)
        }
        resubscribe()
    }

    private fun createBoardPanel(): Component {
        val gamePanel = JPanel(GridLayout(BOARD_SIZE, BOARD_SIZE))

        for (i in 0 until BOARD_SIZE) {
            val buttonsRow = mutableListOf<JButton>()
            for (j in 0 until BOARD_SIZE) {
                val cellButton = JButton("")
                cellButton.addActionListener {
                    gameModel.doMove(i * BOARD_SIZE + j)
                    updateGameUI()
                }
                buttonsRow.add(cellButton)
                gamePanel.add(cellButton)
            }
            buttons.add(buttonsRow)
        }
        return gamePanel
    }

    private fun resubscribe() {
        gameModel.removeModelChangeListener(this)
        gameModel = Model()
        gameModel.addModelChangeListener(this)
        updateGameUI()
    }

//    private fun createModeChanger(): Component {
//        val modChanger = JButton(gameModel.state.textValue)
//        updateFont(modChanger, 20.0f)
//        modChanger.addActionListener{
//            if (gameModel.state == State.MOVE_MODE) {
//                gameModel.stateChanger(State.FLAG_MODE)
//                modChanger.text = gameModel.state.textValue
//                updateGameUI()
//            }
//            if (gameModel.state == State.FLAG_MODE){
//                gameModel.stateChanger(State.MOVE_MODE)
//                modChanger.text = gameModel.state.textValue
//                updateGameUI()
//            }
//        }
//        return modChanger
//    }

    private fun createRestartButton(): Component {
        val restartButton = JButton("Restart")
        updateFont(restartButton, 20.0f)
        restartButton.addActionListener {
            if (gameModel.state in GAME_NOT_FINISHED) {
                val dialogOption = JOptionPane.showConfirmDialog(
                    this,
                    "Game not finished, are you sure?",
                    "Restart",
                    JOptionPane.OK_CANCEL_OPTION
                )
                if (dialogOption == JOptionPane.OK_OPTION) {
                    resubscribe()
                }
            } else {
                resubscribe()
            }
        }
        return restartButton
    }

    private fun updateFont(component: JComponent, newFontSize: Float) {
        val font = component.font
        val derivedFont = font.deriveFont(newFontSize)
        component.font = derivedFont
    }

    override fun onModelChanged() {
        updateGameUI()
    }

    private fun updateGameUI() {
        val state = gameModel.state
        statusLabel.text = state.textValue
        if (state in GAME_NOT_FINISHED) {
            for ((i, buttonRow) in buttons.withIndex()) {
                for ((j, button) in buttonRow.withIndex()) {
                    val index = i * BOARD_SIZE + j
                    val cell = gameModel.cellOpen[index]
                    if (cell == CellState.CLOSE) {
                        button.background = Color.GRAY
                        button.text = " "
                        updateFont(button, 20.0f)
                    }
                    if (cell == CellState.OPEN) {
                        button.background = Color.LIGHT_GRAY
                        button.text = gameModel.role[index].textValue
                        updateFont(button, 20.0f)
                    }
//                    if (cell == CellState.FLAG){
//                        button.background = Color.LIGHT_GRAY
//                        button.text = State.FLAG_MODE.textValue
//                    }
                }
            }
        }
        if (state !in GAME_NOT_FINISHED) {
            for ((i, buttonRow) in buttons.withIndex()) {
                for ((j, button) in buttonRow.withIndex()) {
                    val index = i * BOARD_SIZE + j
                    val cell = gameModel.cellOpen[index]
                    button.text = cell.toString()
                    updateFont(button, 20.0f)
                    button.background = Color.LIGHT_GRAY
                    button.text = gameModel.role[index].textValue
                }
            }
        }
    }
}