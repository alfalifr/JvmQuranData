package sidev.data.quran

object Surat: DataProvider by SuratImpl {
    fun pageRange(surat: Int): IntRange {
        SuratImpl.checkRecordRange(surat)
        val suratStr= this["halaman", surat]
        val suratRangeList= suratStr.split("-")
        return suratRangeList[0].toInt() .. suratRangeList[1].toInt()
    }
    fun name(surat: Int): String {
        SuratImpl.checkRecordRange(surat)
        return this["nama", surat]
    }
    fun asma(surat: Int): String {
        SuratImpl.checkRecordRange(surat)
        return this["asma", surat]
    }
    fun meaning(surat: Int): String {
        SuratImpl.checkRecordRange(surat)
        return this["arti", surat]
    }
    fun type(surat: Int): String {
        SuratImpl.checkRecordRange(surat)
        return this["tipe", surat]
    }
    fun ayatCount(surat: Int): Int {
        SuratImpl.checkRecordRange(surat)
        return this["ayat", surat].toInt()
    }
    fun ayatStart(surat: Int): Int {
        SuratImpl.checkRecordRange(surat)
        return this["ayat_start", surat].toInt()
    }
}

internal object SuratImpl: DataProviderImpl(){
    override val headerStr: String = """"no";"nama";"asma";"arti";"tipe";"ayat";"ayat_start";"halaman""""
    override val fileName: String = "surat.csv"
    override val recordRange: IntRange = 1 .. QuranConst.SURAT_MAX
}