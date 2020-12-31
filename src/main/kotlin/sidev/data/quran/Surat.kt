package sidev.data.quran

import sidev.data.DataProvider
import sidev.data.dataProvider

object Surat: DataProvider by dataProvider(
    """"no";"nama";"asma";"arti";"tipe";"ayat";"ayat_start";"halaman"""",
    Quran.FILE_NAME_SURAT,
    1 .. Quran.SURAT_MAX,
    Quran,
    { Source[Quran.FILE_NAME_SURAT] }
) {
    fun pageRange(surat: Int): IntRange {
        val suratStr= this["halaman", surat]
        val suratRangeList= suratStr.split("-")
        return suratRangeList[0].toInt() .. suratRangeList[1].toInt()
    }
    fun name(surat: Int): String = this["nama", surat]
    fun asma(surat: Int): String = this["asma", surat]
    fun meaning(surat: Int): String = this["arti", surat]
    fun type(surat: Int): String = this["tipe", surat]
    fun ayatCount(surat: Int): Int = this["ayat", surat].toInt()
    fun ayatStart(surat: Int): Int = this["ayat_start", surat].toInt()
}
/*
internal object SuratImpl: DataProviderImpl(){
    override val headerStr: String = """"no";"nama";"asma";"arti";"tipe";"ayat";"ayat_start";"halaman""""
    override val fileName: String = "surat.csv"
    override val recordRange: IntRange = 1 .. Quran.SURAT_MAX
}
 */