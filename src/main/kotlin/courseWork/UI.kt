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
    private var textMode = gameModel.state.textValue
    init {
        setSize(1000, 1000)
        defaultCloseOperation = EXIT_ON_CLOSE

        updateFont(statusLabel, 20.0f)

        rootPane.contentPane = JPanel(BorderLayout(GAP, GAP)).apply {
            add(statusLabel, BorderLayout.NORTH)
            add(createBoardPanel(), BorderLayout.CENTER)
            add(createRestartButton(), BorderLayout.SOUTH)
            add(createModeChanger(), BorderLayout.WEST)
            border = BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP)
        }
        resubscribe()
    }

    private fun createBoardPanel(): Component {
        val gamePanel = JPanel(GridLayout(BOARD_SIZE, BOARD_SIZE))

        for (i in 1 until BOARD_SIZE + 1) {
            val buttonsRow = mutableListOf<JButton>()
            for (j in 1 until BOARD_SIZE + 1) {
                val cellButton = JButton("")
                cellButton.addActionListener {
                    gameModel.doMove(i * j)
                }
                buttonsRow.add(cellButton)
                gamePanel.add(cellButton)
                updateFont(cellButton, 5.0f)
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

    private fun createModeChanger(): JButton {
        val modChanger = JButton(textMode)
        updateFont(modChanger, 20.0f)
        modChanger.addActionListener{
            if (gameModel.state == State.MOVE_MODE) gameModel.state = State.FLAG_MODE
            if (gameModel.state == State.FLAG_MODE) gameModel.state = State.MOVE_MODE
            onModelChanged()
        }
        return modChanger
    }

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
        textMode = state.textValue
        if (state in GAME_NOT_FINISHED) {
            for ((i, buttonRow) in buttons.withIndex()) {
                for ((j, button) in buttonRow.withIndex()) {
                    val index = i * j
                    val cell = gameModel.cellOpen[index]
                    button.isEnabled = cell == CellState.CLOSE
                    if (cell == CellState.CLOSE) {
                        //button.background = Color.LIGHT_GRAY
                        button.text = " "
                        updateFont(button, 20.0f)
                    }
                    if (cell == CellState.FLAG || cell == CellState.OPEN) {
                       //button.background = Color.LIGHT_GRAY
                        button.text = gameModel.role[index].textValue
                        updateFont(button, 20.0f)
                    }
                }
            }
        }
        if (state !in GAME_NOT_FINISHED) {
            for ((i, buttonRow) in buttons.withIndex()) {
                for ((j, button) in buttonRow.withIndex()) {
                    val index = i * j
                    val cell = gameModel.cellOpen[index]
                    button.text = cell.toString()
                    updateFont(button, 20.0f)
                    button.isEnabled = false
                    //button.background = Color.LIGHT_GRAY
                    button.text = gameModel.role[index].textValue
                }
            }
        }
    }
}