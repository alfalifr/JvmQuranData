import org.junit.Test
import sidev.data.quran.*
import sidev.lib.collection.common.getElementAt
import sidev.lib.console.prin
import sidev.lib.jvm.tool.util.FileUtil
import sidev.lib.text.Charset
import java.io.File

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
    @Test
    fun processAyatTest(){
        processAyat()
    }

    @Test
    fun ayatEndMarkTest(){
        val i= 0xEF
        val i2= 0xEf
        val u= 0x0000EF
        val o= 0xFD3F
        val o2= 0xFD3F
        val b= 0xbf
        val e= "EF".toInt(16)
        prin(i)
        prin(i2)
        prin(i == i2)
        prin(u)
        prin(i == u)
        prin(o)
        prin(e)
        prin(b)
        prin(127.toString(16))

        prin(Character.digit('E', 16))
        prin(Character.digit('F', 16))
        prin(0xff)
        prin(-128+255)
        prin(0xff-128)
        prin(0x61.toChar())

        prin(0xEF.toChar())

        prin(127.toByte())
        prin(128.toByte())
        prin(129.toByte())
//        123.toByte()

        val m= Quran.getAyatEndMark(553, false)
        prin(m, Charset.UTF_8)

        FileUtil.saveln(File("_output/tes.txt"), m, charset = Charsets.UTF_8, inSameFile = false)

        val ayat1= Ayat.lafadz(5, false, false, true)
        val ayat2= Ayat.lafadz(2, reverseMark = true)

        val end1= Quran.getAyatEndMark(1)
        val end2= Quran.getAyatEndMark(2, reverseMark = true)

        val laf= "$ayat1 $end1 $ayat2 $end2"
        prin(laf)
        FileUtil.saveln(File("_output/tes.txt"), laf, charset = Charsets.UTF_8)

        val arabInt1= Quran.toArabInt(142, false)
        prin(arabInt1, Charset.UTF_8)
        FileUtil.saveln(File("_output/tes.txt"), arabInt1, charset = Charsets.UTF_8)
        val arabInt2= Quran.toArabInt(1910, false)
        prin(arabInt2, Charset.UTF_8)
        FileUtil.saveln(File("_output/tes.txt"), arabInt2, charset = Charsets.UTF_8)

        FileUtil.saveln(File("_output/tes.txt"), m, charset = Charsets.UTF_8)
    }

    @Test
    fun dataProviderTest(){
        prin(Ayat)
        prin(Surat)
    }

    @Test
    fun intTest(){
        val c1= '1'
        prin(c1.toInt())
    }

    @Test
    fun lafadzTest(){
        prin(Ayat.lafadz(1, false, false), Charset.UTF_8)
    }
}