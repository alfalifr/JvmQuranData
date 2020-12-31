package sidev.data.quran

import sidev.data.DataProvider
import sidev.data.dataProvider

object Ayat: DataProvider by dataProvider(
    """"no";"surat";"ayat";"lafadz";"arti"""",
    Quran.FILE_NAME_AYAT,
    1 .. Quran.AYAT_MAX,
    Quran,
    { Source[Quran.FILE_NAME_AYAT] }
) {
    fun surat(ayat: Int): Int = this["surat", ayat].toInt()
    fun ayatInSurat(ayat: Int): Int = this["ayat", ayat].toInt()
    @JvmOverloads
    fun lafadz(ayat: Int, withoutAyatEndMark: Boolean= true, useSingleEndMark: Boolean = true): String {
        val lafadz= this["lafadz", ayat]
        val endMark= if(withoutAyatEndMark) "" else " " +Quran.getAyatEndMark(ayatInSurat(ayat), useSingleEndMark)
        return "$lafadz$endMark"
    }
    fun meaning(ayat: Int): String = this["arti", ayat]
}
/*
internal object AyatImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat";"ayat";"lafadz";"arti""""
    override val fileName: String = "ayat.csv"
    override val recordRange: IntRange = 1 .. Quran.AYAT_MAX
}
 */