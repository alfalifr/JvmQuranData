package sidev.data.quran

object Juz: DataProvider by JuzImpl {
    fun suratRange(juz: Int): IntRange {
        JuzImpl.checkRecordRange(juz)
        val suratStr= this["surat", juz]
        val suratRangeList= suratStr.split("-")
        return suratRangeList[0].toInt() .. suratRangeList[1].toInt()
    }
}

internal object JuzImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat""""
    override val fileName: String = "juz.csv"
    override val recordRange: IntRange = 1 .. QuranConst.JUZ_MAX
}