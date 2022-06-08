package courseWork

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.GridLayout
import javax.swing.*

private const val GAP = 10

class UI: JFrame("Minesweeper"),ModelChangeListener {
    private var gameModel: Model = Model()
    private val statusLabel = JLabel("Status", JLabel.CENTER)
    private val buttons = mutableListOf<MutableList<JButton>>()

    init {
        setSize(500, 500)
        defaultCloseOperation = EXIT_ON_CLOSE

        updateFont(statusLabel, 20.0f)

        rootPane.contentPane = JPanel(BorderLayout(GAP, GAP)).apply {
            add(statusLabel, BorderLayout.NORTH)
            add(createBoardPanel(), BorderLayout.CENTER)
            add(createRestartButton(), BorderLayout.SOUTH)
            border = BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP)
        }

        resubscribe()
    }

    private fun createBoardPanel(): Component {
        val gamePanel = JPanel(GridLayout(BOARD_SIZE, BOARD_SIZE, GAP, GAP))

        for (i in 0 until BOARD_SIZE) {
            val buttonsRow = mutableListOf<JButton>()
            for (j in 0 until BOARD_SIZE) {
                val cellButton = JButton("")
                cellButton.addActionListener {
                    gameModel.emptyCell(i * j + 1)
                }
                buttonsRow.add(cellButton)
                gamePanel.add(cellButton)
                updateFont(cellButton, 30.0f)
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
        statusLabel.text = state.toString()

        for ((i, buttonRow) in buttons.withIndex()) {
            for ((j, button) in buttonRow.withIndex()) {
                val cell = gameModel.role[i * j +1]
                button.text = cell.toString()

                button.isEnabled = cell == Field.EMPTY && state in GAME_NOT_FINISHED
                button.foreground = when (cell) {
                    Field.EMPTY -> Color.GRAY
                    Field.BOMB -> Color.RED
                    Field.FLAG -> Color.CYAN
                    Field.N_ONE -> Color.BLUE
                    Field.N_TWO -> Color.GREEN
                    Field.N_THREE -> Color.RED
                    Field.N_FOUR -> Color.BLUE
                    Field.N_FIVE -> Color.MAGENTA
                    Field.N_SIX -> Color.PINK
                    Field.N_SEVEN -> Color.ORANGE
                    Field.N_EIGHT -> Color.YELLOW
                }
            }
        }
    }
}