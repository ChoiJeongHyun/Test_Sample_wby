package kr.co.example.codingtest_wirebarley.api


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kr.co.example.codingtest_wirebarley.vo.Rate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.Array

interface RateAPI {

//    @GET("/live")
//    fun getRate(@Query("access_key") access_key: String ) : Call<Rate>

    @GET("/live")
    fun getRate(@Query("access_key") access_key: String ) : Single<Rate>

}