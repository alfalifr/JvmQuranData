package sidev.data.quran

import sidev.data.quran.src.Source
import sidev.lib.console.prine
import sidev.lib.exception.IllegalArgExc
import sidev.lib.property.mutableLazy
import java.io.File
import java.util.*

interface DataProvider {
    val headerList: List<String>

//    fun cacheToList()
    fun clearCache()

    operator fun get(header: String, rowIndex: Int): String {
        val index= headerList.indexOf(header.toLowerCase())
        if(index !in headerList.indices)
            throw IllegalArgExc(paramExcepted = arrayOf("header"), detailMsg = "Param `header` ($header) memiliki `index` ($index) di luar `headerList.indices` (${Surat.headerList.indices})")
        val row= this[rowIndex]
        return row[index]
    }
    operator fun get(index: Int): List<String>
}

internal abstract class DataProviderImpl: DataProvider {
//    abstract val valStr: String
    abstract val headerStr: String
    abstract val fileName: String

    private lateinit var scanner: Scanner
    private var currPointer= 0
    private var list: List<List<String>>?= null
    override val headerList: List<String> by lazy { headerStr.split(";").map { it.replace("\"", "") } }
/*
    override fun cacheToList(){
        if(list != null) return
        list= valStr.split("\n").map { it.split(";").map { it.replace("\"", "") } }
    }
 */
    private fun initScanner() {
        val fileName= if(fileName.endsWith(".csv")) fileName else "$fileName.csv"
        val file= Source[fileName] //File("_src/$fileName")
        prine(file.absolutePath)
        prine(file.exists())
//        val file= File(File("src/$fileName").absolutePath)
        scanner= Scanner(file, Charsets.UTF_8)
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

    private fun splitLineToColumn(line: String): List<String> =
        line.split(";").map { it.replace("\"", "") }

    override operator fun get(header: String, rowIndex: Int): String {
        val index= headerList.indexOf(header.toLowerCase())
        if(index !in headerList.indices)
            throw IllegalArgExc(paramExcepted = arrayOf("header"), detailMsg = "Param `header` ($header) memiliki `index` ($index) di luar `headerList.indices` (${Surat.headerList.indices})")
        val row= this[rowIndex]
        return row[index]
    }
    override operator fun get(index: Int): List<String> {
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
}