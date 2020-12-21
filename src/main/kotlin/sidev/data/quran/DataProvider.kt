package sidev.data.quran

import sidev.lib.collection.ReadOnlyList
import sidev.lib.collection.asReadOnly
import sidev.lib.console.prine
import sidev.lib.exception.IllegalArgExc
import java.util.*

/**
 * Interface umum bagi objek yang menyediakan record dari file.
 */
interface DataProvider: Iterable<List<String>> {
    val headerList: ReadOnlyList<String>

    /**
     * Range index yang berisi record, tidak termasuk header.
     */
    val recordRange: IntRange

//    fun cacheToList()
    fun clearCache()

    operator fun get(header: String, rowIndex: Int): String {
        val index= headerList.indexOf(header.toLowerCase())
        if(index !in headerList.indices)
            throw IllegalArgExc(paramExcepted = arrayOf("header"), detailMsg = "Param `header` ($header) memiliki `index` ($index) di luar `headerList.indices` (${Surat.headerList.indices})")
        val row= this[rowIndex]
        return row[index]
    }
    operator fun get(index: Int): ReadOnlyList<String>
}

internal abstract class DataProviderImpl: DataProvider {
//    abstract val valStr: String
    abstract val headerStr: String
    abstract val fileName: String

    private lateinit var scanner: Scanner
    private var currPointer= 0
    private var list: List<ReadOnlyList<String>>?= null
    override val headerList: ReadOnlyList<String> by lazy { headerStr.split(";").map { it.replace("\"", "") }.asReadOnly(false) }
/*
    override fun cacheToList(){
        if(list != null) return
        list= valStr.split("\n").map { it.split(";").map { it.replace("\"", "") } }
    }
 */
    private fun initScanner() {
        val fileName= if(fileName.endsWith(".csv")) fileName else "$fileName.csv"
        val stream= Source[fileName] //File("_src/$fileName")
//        prine(file.absolutePath)
//        prine(file.exists())
//        val file= File(File("src/$fileName").absolutePath)
        scanner= Scanner(stream, Charsets.UTF_8)
    }
    private fun initCache(){
        if(list != null) return
        list= mutableListOf()
        initScanner()
        currPointer= 0
    }
    override fun clearCache(){
        list= null
    }

    private fun splitLineToColumn(line: String): ReadOnlyList<String> =
        line.split(";").map { it.replace("\"", "") }.asReadOnly(false)

    fun checkRecordRange(index: Int) {
        if(index !in recordRange)
            throw IllegalArgExc(paramExcepted = arrayOf("index"), detailMsg = "Param `index` ($index) tidak berada di antara nilai $recordRange")
    }

    override operator fun get(header: String, rowIndex: Int): String {
        val index= headerList.indexOf(header.toLowerCase())
        if(index !in headerList.indices)
            throw IllegalArgExc(paramExcepted = arrayOf("header"), detailMsg = "Param `header` ($header) memiliki `index` ($index) di luar `headerList.indices` (${Surat.headerList.indices})")
        val row= this[rowIndex]
        return row[index]
    }
    override operator fun get(index: Int): ReadOnlyList<String> {
        initCache()
        val mutList= list as MutableList
        prine("currPointer <= index => ${currPointer <= index} scanner.hasNextLine() => ${scanner.hasNextLine()} scanner.hasNext() => ${scanner.hasNext()}")
        while(currPointer <= index && scanner.hasNextLine()) {
            mutList += splitLineToColumn(scanner.nextLine())
            currPointer++
        }
        if(currPointer <= index)
            throw IllegalArgExc(paramExcepted = arrayOf("index"), detailMsg = "Param `index` ($index) melebihi panjang data ($currPointer)")
        return mutList[index]
    }

    override fun iterator(): Iterator<List<String>> = object: Iterator<List<String>> {
        val mutList: MutableList<ReadOnlyList<String>> by lazy {
            initCache()
            list as MutableList
        }
        var i= 0

        override fun hasNext(): Boolean = i < mutList.size || scanner.hasNextLine()
        override fun next(): List<String> {
            val next= if(i < mutList.size) mutList[i]
            else splitLineToColumn(scanner.nextLine()).also {
                mutList += it
                currPointer++
            }
            i++
            return next
        }
    }
}