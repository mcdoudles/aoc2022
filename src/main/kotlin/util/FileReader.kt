package util

import java.io.File

class FileReader {
    companion object {
        fun readFile(fileName: String) = File("src/main/resources/$fileName")
    }
}