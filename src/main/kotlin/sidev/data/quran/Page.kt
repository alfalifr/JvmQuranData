package sidev.data.quran

import sidev.data.DataProvider
import sidev.data.dataProvider

object Page: DataProvider by dataProvider(
    """"no";"surat";"ayat";"ayat_surat"""",
    Quran.FILE_NAME_PAGE_USTMANI,
    1 .. Quran.PAGE_USTMANI_MAX,
    Quran,
    { Source[Quran.FILE_NAME_PAGE_USTMANI] }
) {
    fun suratRange(page: Int): IntRange {
        val suratStr= this["surat", page]
        val suratRangeList= suratStr.split("-")
        return suratRangeList[0].toInt() .. suratRangeList[1].toInt()
    }
    fun ayatInSuratRange(page: Int): List<IntRange> {
        val ayatStr= this["ayat_surat", page]
        val ayatRangeList= ayatStr.split(",")

        val res= mutableListOf<IntRange>()
        for(ayatRangeStr in ayatRangeList){
            val rangeList= ayatRangeStr.split("-")
            val range= rangeList[0].toInt() .. rangeList[1].toInt()
            res += range
        }
        return res
    }
    fun ayatRange(page: Int): IntRange {
        val ayatStr= this["ayat", page]
        val ayatRangeList= ayatStr.split("-")
        return ayatRangeList[0].toInt() .. ayatRangeList[1].toInt()
    }
}
/*
internal object PageImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat";"ayat";"ayat_surat""""
    override val fileName: String = "page_ustmani.csv"
    override val recordRange: IntRange = 1 .. Quran.PAGE_USTMANI_MAX
}
 */