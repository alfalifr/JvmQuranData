package sidev.data.quran

import sidev.data.DataProvider
import sidev.data.dataProvider

object Juz: DataProvider by dataProvider(
    """"no";"surat";"ayat";"ayat_surat"""",
    Quran.FILE_NAME_JUZ,
    1 .. Quran.JUZ_MAX,
    Quran,
    { Source[Quran.FILE_NAME_JUZ] }
) {
    fun suratRange(juz: Int): IntRange {
        val suratStr= this["surat", juz]
        val suratRangeList= suratStr.split("-")
        return suratRangeList[0].toInt() .. suratRangeList[1].toInt()
    }
    fun ayatInSuratRange(juz: Int): List<IntRange> {
        val ayatStr= this["ayat_surat", juz]
        val ayatRangeList= ayatStr.split(",")

        val res= mutableListOf<IntRange>()
        for(ayatRangeStr in ayatRangeList){
            val rangeList= ayatRangeStr.split("-")
            val range= rangeList[0].toInt() .. rangeList[1].toInt()
            res += range
        }
        return res
    }
    fun ayatRange(juz: Int): IntRange {
        val ayatStr= this["ayat", juz]
        val ayatRangeList= ayatStr.split("-")
        return ayatRangeList[0].toInt() .. ayatRangeList[1].toInt()
    }
}
/*
internal object JuzImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat";"ayat";"ayat_surat""""
    override val fileName: String = "juz.csv"
    override val recordRange: IntRange = 1 .. Quran.JUZ_MAX
}
 */