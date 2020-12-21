import org.junit.Test
import sidev.data.quran.*
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
        prin("Surat.asma(114)= ${Surat.asma(114)}")
        prin("Surat.name(114)= ${Surat.name(114)}")

        prin("\n\n ======== Ayat ========= \n\n")
        prin(Ayat[3])
        prin(Ayat[400])
        prin(Ayat[6236])
        prin(Ayat["lafadz", 6236], Charset.UTF_8)

        prin("Ayat.ayatInSurat(9)= ${Ayat.ayatInSurat(9)}")
        prin("Ayat.lafadz(9)= ${Ayat.lafadz(9)}")
        prin("Ayat.meaning(6236)= ${Ayat.meaning(6236)}")
//        prin(Ayat[6237])

        prin("\n\n ======== Page ========= \n\n")
        prin(Page[3])
        prin(Page[604])
        prin("Page.suratRange(604)= ${Page.suratRange(604)}")
        prin("Page.ayatRange(604)= ${Page.ayatRange(604)}")

        prin("\n\n ======== Juz ========= \n\n")
        prin(Juz[3])
        prin(Juz[30])
        prin("Juz.suratRange(30)= ${Juz.suratRange(30)}")
        prin("Juz.ayatRange(30)= ${Juz.ayatRange(30)}")
        prin("Juz.ayatInSuratRange(30)= ${Juz.ayatInSuratRange(30)}")
//        prin("Juz.suratRange(300)= ${Juz.suratRange(300)}")
    }

    @Test
    fun juzProcessTest(){
        processJuz()
    }
    @Test
    fun pageProcessTest(){
        processPage()
    }
}