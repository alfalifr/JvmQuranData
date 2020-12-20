package sidev.data.quran

object Juz: DataProvider by JuzImpl {
    fun suratRange(juz: Int): IntRange {
        JuzImpl.checkRecordRange(juz)
        val suratStr= this["surat", juz]
        val suratRangeList= suratStr.split("-")
        return suratRangeList[0].toInt() .. suratRangeList[1].toInt()
    }
    fun ayatRangeList(juz: Int): List<IntRange> {
        JuzImpl.checkRecordRange(juz)
        val ayatStr= this["ayat", juz]
        val ayatRangeList= ayatStr.split(",")

        val res= mutableListOf<IntRange>()
        for(ayatRangeStr in ayatRangeList){
            val rangeList= ayatRangeStr.split("-")
            val range= rangeList[0].toInt() .. rangeList[1].toInt()
            res += range
        }
        return res
    }
}

internal object JuzImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat";"ayat""""
    override val fileName: String = "juz.csv"
    override val recordRange: IntRange = 1 .. QuranConst.JUZ_MAX
}