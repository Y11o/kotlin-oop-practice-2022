package fourthLab

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object ModelSerialization {
    private val json = Json

    private fun serialization(maze: MutableList<MutableList<Model.Field>>) =
        json.encodeToString(maze)

    private fun deserialization(stringToDecoder: String) =
        json.decodeFromString<MutableList<MutableList<Model.Field>>>(stringToDecoder)

    fun serializationToFile(maze: MutableList<MutableList<Model.Field>>, fileName: String) {
        File(fileName).writeText(serialization(maze))
    }

    fun deserializationFromFile(fileName: String): MutableList<MutableList<Model.Field>> {
        val file = File(fileName)
        if (!file.exists())
            throw IllegalArgumentException("File can't be found")
        return deserialization(file.readText())
    }
}