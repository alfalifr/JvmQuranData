package sidev.data.quran

object Ayat: DataProvider by AyatImpl {
    fun surat(ayat: Int): Int {
        AyatImpl.checkRecordRange(ayat)
        return this["surat", ayat].toInt()
    }
    fun ayatInSurat(ayat: Int): Int {
        AyatImpl.checkRecordRange(ayat)
        return this["ayat", ayat].toInt()
    }
    fun lafadz(ayat: Int): String {
        AyatImpl.checkRecordRange(ayat)
        return this["lafadz", ayat]
    }
    fun meaning(ayat: Int): String {
        AyatImpl.checkRecordRange(ayat)
        return this["arti", ayat]
    }
}

internal object AyatImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat";"ayat";"lafadz";"arti""""
    override val fileName: String = "ayat.csv"
    override val recordRange: IntRange = 1 .. QuranConst.AYAT_MAX
}