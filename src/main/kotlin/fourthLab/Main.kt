package fourthLab

fun main(){
    val fileName = "D:\\My projects\\IDEA\\kotlin-oop-practice-2022\\src\\main\\kotlin\\fourthLab\\Maze.txt"
    val maze = Model(fileName)
    ConsoleUI(maze)
    Controller(maze)
}