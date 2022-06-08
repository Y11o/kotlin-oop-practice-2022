package courseWork

import javax.swing.SwingUtilities


fun main() {
    val check: Int
    val board_size: Int
    val cnt_num: Int

    println("Enter the number that corresponds to the level you have chosen")
    println("<1> Beginner\\n<2> Amateur\\n<3> Professional")
    check = readLine()!!.toInt()
    if (check == 1) {
        board_size = 10
        cnt_num = 9
    } else if (check == 2) {
        board_size = 16
        cnt_num = 40
    } else if (check == 3) {
        board_size = 25
        cnt_num = 99
    } else throw IllegalArgumentException("Invalid value entered")


    SwingUtilities.invokeLater {
        val minesweeperUI = UI(board_size, cnt_num)
        minesweeperUI.isVisible = true
    }
}

