package sidev.data.quran

import sidev.lib.console.prin
import sidev.lib.jvm.tool.util.FileUtil
import sidev.lib.jvm.tool.util.TimeUtil
import sidev.lib.text.Charset
import sidev.lib.text.removeSurroundingWhitespace
import sidev.lib.text.removeWhitespace
import java.io.File

internal fun processJuz(){
    val timestamp= TimeUtil.timestamp(pattern = "dd-MM-yyyy_HH-mm-ss")
    val file= File("_output/juz/juz_$timestamp.csv")
    val header= "\"juz\";\"surat\";\"ayat\";\"ayat_surat\""

    FileUtil.saveln(file, header, charset = Charsets.UTF_8)

    for((i, e) in Juz.withIndex()){
        if(i == 0) continue
        var row= ""//e.joinToString(";") { "\"$it\"" }
        for(u in 0 until 2){
            row += "\"${e[u]}\";"
        }
        val suratRange= Juz.suratRange(i)
        val ayatStartSurat0= Surat.ayatStart(suratRange.first)
        val ayatStartSurat1= Surat.ayatStart(suratRange.last)

        val ayatStrList= e.last().split("-")
        val ayatStartSurat= ayatStrList.first().toInt()
        val ayatEndSurat= ayatStrList.last().toInt()

        val ayatStart= ayatStartSurat + ayatStartSurat0
        val ayatEnd= ayatEndSurat + ayatStartSurat1

        val ayatRangeStr= "\"$ayatStart-$ayatEnd\""

        row += ayatRangeStr
        row += ";\"${ e.last() }\""

        prin("newLine= $row")
        FileUtil.saveln(file, row, charset = Charsets.UTF_8)
    }
}

internal fun processPage(){
    val timestamp= TimeUtil.timestamp(pattern = "dd-MM-yyyy_HH-mm-ss")
    val file= File("_output/page/page_$timestamp.csv")
    val header= "\"page\";\"surat\";\"ayat\";\"ayat_surat\""

    FileUtil.saveln(file, header, charset = Charsets.UTF_8)

    for((i, e) in Page.withIndex()){
        if(i == 0) continue
        var row= ""//e.joinToString(";") { "\"$it\"" }
        for(u in 0 until 2){
            row += "\"${e[u]}\";"
        }
        val suratRange= Page.suratRange(i)
        val ayatStartSurat0= Surat.ayatStart(suratRange.first)
        val ayatStartSurat1= Surat.ayatStart(suratRange.last)

        val ayatStrList= e.last().split("-")
        val ayatStartSurat= ayatStrList.first().toInt()
        val ayatEndSurat= ayatStrList.last().toInt()

        val ayatStart= ayatStartSurat + ayatStartSurat0
        val ayatEnd= ayatEndSurat + ayatStartSurat1

        val ayatRangeStr= "\"$ayatStart-$ayatEnd\""

        row += ayatRangeStr
        row += ";\"${ e.last() }\""

        prin("newLine= $row")
        FileUtil.saveln(file, row, charset = Charsets.UTF_8)
    }
}

internal fun processAyat(){
    val timestamp= TimeUtil.timestamp(pattern = "dd-MM-yyyy_HH-mm-ss")
    val file= File("_output/ayat/ayat_$timestamp.csv")
    val header= "\"no\";\"surat\";\"ayat\";\"lafadz\";\"arti\""

    FileUtil.saveln(file, header, charset = Charsets.UTF_8)

    for((i, e) in Ayat.withIndex()){
        if(i == 0) continue
        val no= e[0]
        val surat= e[1]
        val ayat= e[2].removeSurroundingWhitespace() //.removePrefix(" ").removeSuffix(" ")
        val lafadz= e[3]
        val arti= e[4]

        val row= "\"$no\";\"$surat\";\"$ayat\";\"$lafadz\";\"$arti\""
//        "".removeWhitespace()
//        ".".removeSurroundingWhitespace()
        prin("newLine= $row", Charset.UTF_8)
        FileUtil.saveln(file, row, charset = Charsets.UTF_8)
    }
}