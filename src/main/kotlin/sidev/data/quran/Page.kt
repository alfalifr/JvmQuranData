package sidev.data.quran

object Page: DataProvider by PageImpl {
    fun suratRange(page: Int): IntRange {
        PageImpl.checkRecordRange(page)
        val suratStr= this["surat", page]
        val suratRangeList= suratStr.split("-")
        return suratRangeList[0].toInt() .. suratRangeList[1].toInt()
    }
    fun ayatInSuratRange(page: Int): List<IntRange> {
        PageImpl.checkRecordRange(page)
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
        PageImpl.checkRecordRange(page)
        val ayatStr= this["ayat", page]
        val ayatRangeList= ayatStr.split("-")
        return ayatRangeList[0].toInt() .. ayatRangeList[1].toInt()
    }
}

internal object PageImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat";"ayat";"ayat_surat""""
    override val fileName: String = "page_ustmani.csv"
    override val recordRange: IntRange = 1 .. QuranConst.PAGE_USTMANI_MAX
}