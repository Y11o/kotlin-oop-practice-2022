package fourthLab

import java.io.File

fun main() {
    val fileName = "D:\\My projects\\IDEA\\kotlin-oop-practice-2022\\src\\main\\kotlin\\fourthLab\\Maze.txt"
    val mazeList = ModelSerialization().deserialization(File(fileName).readText())
    val maze = Model(mazeList as List<MutableList<Model.Field>>)
    ConsoleUI(maze)
    Controller(maze)
}