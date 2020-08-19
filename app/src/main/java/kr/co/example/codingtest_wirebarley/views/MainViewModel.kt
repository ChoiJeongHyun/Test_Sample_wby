package kr.co.example.codingtest_wirebarley.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.co.example.codingtest_wirebarley.api.ApiManager
import kr.co.example.codingtest_wirebarley.api.RateAPI
import kr.co.example.codingtest_wirebarley.base.BaseViewModel
import kr.co.example.codingtest_wirebarley.repository.AppRepository
import kr.co.example.codingtest_wirebarley.utils.C
import kr.co.example.codingtest_wirebarley.utils.PLog
import kr.co.example.codingtest_wirebarley.vo.Country
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.math.floor
import kotlin.math.truncate

class MainViewModel @Inject constructor(private val appRepository: AppRepository) :
    BaseViewModel() {

    private val _amountInputPrice: MutableLiveData<String> = MutableLiveData("100")
    val amountInputPrice
        get() = _amountInputPrice

    private val _items: MutableLiveData<ArrayList<Country>> = MutableLiveData(arrayListOf())
    val items
        get() = _items

    private val _systemMsg: MutableLiveData<String> = MutableLiveData()
    val systemMsg
        get() = _systemMsg

    private val _country: MutableLiveData<Country> = MutableLiveData(Country("...","...",0f))
    val country
        get() = _country

    private val _receivedAmount: MutableLiveData<String> = MutableLiveData("환율정보를 가져오는 중입니다.")
    val receivedAmount
    get() = _receivedAmount

    init {
        getRate()
    }


    fun getRate() {

        addDisposable(appRepository.apiGetRate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .doOnSuccess { _systemMsg.value = "환율정보를 갱신하였습니다." }
            .doOnError { _systemMsg.value = "환율정보를 가져오지 못했습니다." }
            .subscribe({

                val ctyKRW = Country("한국(KRW)", "KRW", truncate(100 * it.quotes.usdKRW) / 100, it.timeStamp * 1000)
                val ctyJPY = Country("일본(JPY)", "JPY", truncate(100 * it.quotes.usdJPY) / 100,it.timeStamp * 1000)
                val ctyPHP = Country("필리핀(PHP)", "PHP", truncate(100 * it.quotes.usdPHP) / 100 ,it.timeStamp * 1000)

                val listItem: ArrayList<Country> = arrayListOf(ctyKRW, ctyJPY, ctyPHP)


                _items.value = listItem
                _country.value = ctyKRW

                setReceivedAmount(_country.value!!)


            }, {

            })
        )
    }


    fun itemCLick(country: Country) {
        _country.value = country
        setReceivedAmount(_country.value!!)
    }

    fun setReceivedAmount(c: Country){
        c.let {
            if (amountInputPrice.value.isNullOrEmpty()) return
            val receiveAmount = it.rate * amountInputPrice.value!!.toFloat()
            _receivedAmount.value = String.format("수치금액은 %s %s 입니다.", DecimalFormat("#,##0.00").format(receiveAmount) , it.unit)
        }
    }






}