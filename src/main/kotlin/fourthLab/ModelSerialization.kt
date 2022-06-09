package fourthLab

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class ModelSerialization() {
    private val json = Json {
        prettyPrint = true
    }

    fun serialization(maze: List<List<Model.Field>>) =
        json.encodeToString(maze)

    fun deserialization(stringToDecoder: String) =
        json.decodeFromString<List<List<Model.Field>>>(stringToDecoder)

    fun serializationToFile(maze: List<List<Model.Field>>, fileName: String) {
        File(fileName).writeText(serialization(maze))
    }

}