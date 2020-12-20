package sidev.data.quran

object Surat: DataProvider by SuratImpl

internal object SuratImpl: DataProviderImpl(){
    override val headerStr: String = """"no";"nama";"asma";"arti";"tipe";"ayat";"ayat_start";"halaman""""
    override val fileName: String = "surat.csv"
}