package sidev.data.quran

object Juz: DataProvider by JuzImpl

internal object JuzImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat""""
    override val fileName: String = "juz.csv"
}