package courseWork

import javax.swing.SwingUtilities


fun main(){
    SwingUtilities.invokeLater {
        val minesweeperUI = UI()
        minesweeperUI.isVisible = true
    }
}

