package sidev.data.quran

object Juz: DataProvider by JuzImpl {
    fun suratRange(juz: Int): IntRange {
        JuzImpl.checkRecordRange(juz)
        val suratStr= this["surat", juz]
        val suratRangeList= suratStr.split("-")
        return suratRangeList[0].toInt() .. suratRangeList[1].toInt()
    }
    fun ayatInSuratRangeList(juz: Int): List<IntRange> {
        JuzImpl.checkRecordRange(juz)
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
    fun ayatRangeList(juz: Int): IntRange {
        JuzImpl.checkRecordRange(juz)
        val ayatStr= this["ayat", juz]
        val ayatRangeList= ayatStr.split("-")
        return ayatRangeList[0].toInt() .. ayatRangeList[1].toInt()
    }
}

internal object JuzImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat";"ayat";"ayat_surat""""
    override val fileName: String = "juz.csv"
    override val recordRange: IntRange = 1 .. QuranConst.JUZ_MAX
}