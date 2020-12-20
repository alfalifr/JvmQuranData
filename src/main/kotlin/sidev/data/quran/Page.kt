package sidev.data.quran

object Page: DataProvider by PageImpl

internal object PageImpl: DataProviderImpl() {
    override val headerStr: String = """"no";"surat";"ayat""""
    override val fileName: String = "page_ustmani.csv"
}