package sidev.data.quran

import java.text.NumberFormat
import java.util.*
import kotlin.math.absoluteValue

/**
 * Objek yang berisi fungsi template yang berkaitan dengan Quran.
 */
object Quran {
    internal const val FILE_NAME_AYAT = "ayat.csv"
    internal const val FILE_NAME_JUZ = "juz.csv"
    internal const val FILE_NAME_PAGE_USTMANI = "page_ustmani.csv"
    internal const val FILE_NAME_SURAT = "surat.csv"
    const val JUZ_MAX = 30
    const val SURAT_MAX = 114
    const val AYAT_MAX = 6236
    const val PAGE_USTMANI_MAX = 604

    val ORNATE_RIGHT_PARENTHESIS_INT_ARRAY = intArrayOf(0xEF, 0xB4, 0xBF)
    val ORNATE_LEFT_PARENTHESIS_INT_ARRAY = intArrayOf(0xEF, 0xB4, 0xBE)
    val AYAT_END_MARK_INT_ARRAY = intArrayOf(0xDB, 0x9D)

    val ORNATE_RIGHT_PARENTHESIS_BYTE_ARRAY= ORNATE_RIGHT_PARENTHESIS_INT_ARRAY.let { ls -> ByteArray(ls.size){ ls[it].toByte() } }
    val ORNATE_LEFT_PARENTHESIS_BYTE_ARRAY= ORNATE_LEFT_PARENTHESIS_INT_ARRAY.let { ls -> ByteArray(ls.size){ ls[it].toByte() } }
    val AYAT_END_MARK_BYTE_ARRAY= AYAT_END_MARK_INT_ARRAY.let { ls -> ByteArray(ls.size){ ls[it].toByte() } }

    val ORNATE_RIGHT_PARENTHESIS_STR = ORNATE_RIGHT_PARENTHESIS_BYTE_ARRAY.toString(Charsets.UTF_8)
    val ORNATE_LEFT_PARENTHESIS_STR = ORNATE_LEFT_PARENTHESIS_BYTE_ARRAY.toString(Charsets.UTF_8)
    val AYAT_END_MARK_STR = AYAT_END_MARK_BYTE_ARRAY.toString(Charsets.UTF_8)

    val NUMBER_CODE_INT_ARRAY = listOf(
        intArrayOf(0xD9, 0xA0), //0
        intArrayOf(0xD9, 0xA1),
        intArrayOf(0xD9, 0xA2),
        intArrayOf(0xD9, 0xA3),
        intArrayOf(0xD9, 0xA4), //4
        intArrayOf(0xD9, 0xA5),
        intArrayOf(0xD9, 0xA6),
        intArrayOf(0xD9, 0xA7),
        intArrayOf(0xD9, 0xA8), //8
        intArrayOf(0xD9, 0xA9),
    )
    val NUMBER_CODE_STR_ARRAY: Array<String> by lazy {
        NUMBER_CODE_INT_ARRAY.map { ls -> ByteArray(ls.size){ ls[it].toByte() } }
            .let { ls -> Array(ls.size){ ls[it].toString(Charsets.UTF_8) } }
    }

    /**
     * Mengambil string yang merupakan tanda akhir dari ayat dengan nomor [ayatNo].
     */
    @JvmOverloads
    fun getAyatEndMark(ayatNo: Int, useSingleEndMark: Boolean = true, reverseMark: Boolean = false): String {
//        return ORNATE_LEFT_PARENTHESIS_STR + ayatAr + ORNATE_RIGHT_PARENTHESIS_STR
        val ayatAr= toArabInt(ayatNo)
        return if(!reverseMark){
            if(useSingleEndMark) AYAT_END_MARK_STR + ayatAr
            else ORNATE_LEFT_PARENTHESIS_STR + ayatAr + ORNATE_RIGHT_PARENTHESIS_STR
        } else {
            if(useSingleEndMark) ayatAr + AYAT_END_MARK_STR
            else ORNATE_RIGHT_PARENTHESIS_STR + ayatAr + ORNATE_LEFT_PARENTHESIS_STR
        }
    }

    /**
     * Mengubah angka [int] menjadi angka Arab.
     * [useFormatter] == `false`, maka konversi akan dilakukan scr iteratif dan menggunakan konkatenasi [NUMBER_CODE_STR_ARRAY].
     * [autoCorrect] == `true`, maka jika [useFormatter] == `true` dan hasil return sama dg [int].toString(), maka
     *   fungsi ini akan dipanggil lagi namun dg [useFormatter] == `false`
     */
    @JvmOverloads
    fun toArabInt(int: Int, useFormatter: Boolean = true, autoCorrect: Boolean = true): String = toArabLong(int.toLong(), useFormatter, autoCorrect)
    /**
     * Mengubah angka [long] menjadi angka Arab.
     * [useFormatter] == `false`, maka konversi akan dilakukan scr iteratif dan menggunakan konkatenasi [NUMBER_CODE_STR_ARRAY].
     * [autoCorrect] == `true`, maka jika [useFormatter] == `true` dan hasil return sama dg [int].toString(), maka
     *   fungsi ini akan dipanggil lagi namun dg [useFormatter] == `false`
     */
    @JvmOverloads
    fun toArabLong(long: Long, useFormatter: Boolean = true, autoCorrect: Boolean = true): String {
        return if(useFormatter){
            val nf= NumberFormat.getInstance(Locale.forLanguageTag("AR"))
            val str= nf.format(long)
            if(autoCorrect && str == long.toString()) toArabLong(long, false) else str
        } else {
            val arArray= NUMBER_CODE_STR_ARRAY
            var intItr= long.absoluteValue
            val signumStr= if(long < 0) "-" else ""
            var res= if(long == 0L) arArray[0] else ""
            while(intItr > 0){
                val rear= (intItr % 10).toInt()
                res= "${arArray[rear]}$res"
                intItr /= 10
            }
            "$signumStr$res"
        }
    }
}