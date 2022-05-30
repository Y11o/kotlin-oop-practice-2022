package fourthLab

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class ModelSerialization() {
    private val json = Json {
        prettyPrint = true
    }

    fun serialization(maze: MutableList<MutableList<Model.Field>>) =
        json.encodeToString(maze)

    fun deserialization(stringToDecoder: String) =
        json.decodeFromString<MutableList<MutableList<Model.Field>>>(stringToDecoder)

    fun serializationToFile(maze: MutableList<MutableList<Model.Field>>, fileName: String) {
        File(fileName).writeText(serialization(maze))
    }

}