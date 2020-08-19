package kr.co.example.codingtest_wirebarley.vo

import com.google.gson.annotations.SerializedName

data class Rate (
    @SerializedName("timestamp") val timeStamp: Long = System.currentTimeMillis(),
    @SerializedName("quotes") val quotes: Quotes
)

data class Quotes(
    @SerializedName("USDKRW") val usdKRW: Float = 0.0f,
    @SerializedName("USDJPY") val usdJPY: Float = 0.0f,
    @SerializedName("USDPHP") val usdPHP: Float = 0.0f
)