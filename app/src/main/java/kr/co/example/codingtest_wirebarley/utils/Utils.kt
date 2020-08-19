package kr.co.example.codingtest_wirebarley.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {


        fun dataFormat(longTime: Long?) : String {
            if (longTime == null) return ""
            return try {
                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
                formatter.format(Date(longTime))
            } catch (e: NumberFormatException) {
                e.printStackTrace()
                "-"
            }

        }

    }
}