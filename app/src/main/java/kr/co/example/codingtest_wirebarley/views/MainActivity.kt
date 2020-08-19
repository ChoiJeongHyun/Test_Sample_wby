package kr.co.example.codingtest_wirebarley.views

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.co.example.codingtest_wirebarley.R
import kr.co.example.codingtest_wirebarley.api.ApiManager
import kr.co.example.codingtest_wirebarley.api.RateAPI
import kr.co.example.codingtest_wirebarley.base.BaseActivity
import kr.co.example.codingtest_wirebarley.base.BaseViewModelFactory
import kr.co.example.codingtest_wirebarley.databinding.ActivityMainBinding
import kr.co.example.codingtest_wirebarley.utils.C
import kr.co.example.codingtest_wirebarley.utils.PLog
import kr.co.example.codingtest_wirebarley.vo.Rate
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var factory: BaseViewModelFactory

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModel() = binding.vm as MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = ViewModelProvider(this , factory).get(MainViewModel::class.java)
        binding.vm = v
        binding.lifecycleOwner = this

        getViewModel().systemMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })


    }


}