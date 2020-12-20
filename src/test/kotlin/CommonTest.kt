import org.junit.Test
import sidev.data.quran.Ayat
import sidev.data.quran.Page
import sidev.data.quran.Surat
import sidev.lib.console.prin
import sidev.lib.text.Charset

class CommonTest {
    @Test
    fun printTest(){
        prin(Surat[0])
        prin(Surat[0])
        prin(Surat[3])
        prin(Surat[2])
        prin(Surat[1])
        prin(Surat[1], Charset.UTF_8)
        prin(Surat[1], endWithNewLine = false, charset = Charset.UTF_8)

        prin("\n\n ======== Ayat ========= \n\n")
        prin(Ayat[3])
        prin(Ayat[400])
        prin(Ayat[6236])
        prin(Ayat["lafadz", 6236], Charset.UTF_8)
//        prin(Ayat[6237])

        prin("\n\n ======== Page ========= \n\n")
        prin(Page[3])
        prin(Page[604])
    }
}