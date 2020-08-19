package kr.co.example.codingtest_wirebarley.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.co.example.codingtest_wirebarley.api.ApiManager
import kr.co.example.codingtest_wirebarley.api.RateAPI
import kr.co.example.codingtest_wirebarley.utils.C
import kr.co.example.codingtest_wirebarley.utils.PLog
import kr.co.example.codingtest_wirebarley.vo.Rate
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepository @Inject constructor(private val apiManager: ApiManager) {


    fun apiGetRate() : Single<Rate> = apiManager.getRetrofitService(RateAPI::class.java).getRate(C.API_KEY)


}