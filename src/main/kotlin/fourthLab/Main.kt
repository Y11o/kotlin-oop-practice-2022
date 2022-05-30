package fourthLab

import java.io.File

fun main(){
    val fileName = "D:\\My projects\\IDEA\\kotlin-practice\\src\\main\\kotlin\\fourthLab\\Maze.txt"
    val mazeList = ModelSerialization().deserialization(File(fileName).readText())
    val maze = Model(mazeList)
    ConsoleUI(maze)
    Controller(maze)
}