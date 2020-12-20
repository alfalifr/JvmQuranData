package sidev.data.quran.src

import java.io.File
import java.io.InputStream

internal object Source {
    operator fun get(fileName: String): File = //this::class.java.getResourceAsStream("src/main/kotlin/sidev/data/quran/src/$fileName")
        File("src/main/kotlin/sidev/data/quran/src/$fileName")
}