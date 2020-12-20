package sidev.data.quran

object Ayat: DataProvider by AyatImpl

internal object AyatImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat";"ayat";"lafadz";"arti""""
    override val fileName: String = "ayat.csv"
}