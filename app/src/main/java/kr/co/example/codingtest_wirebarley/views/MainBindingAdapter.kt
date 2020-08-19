package kr.co.example.codingtest_wirebarley.views

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.codingtest_wirebarley.utils.Utils
import kr.co.example.codingtest_wirebarley.vo.Country
import java.math.BigInteger

@BindingAdapter(value = ["isVisibility"])
fun amountErrorVisibility(textView: TextView, amount: String) {

    if (amount.length >= 2 && amount.toCharArray()[0] == '0') {
        textView.visibility = View.VISIBLE
        return
    }

    amount.toBigIntegerOrNull()?.let {
        if (it.compareTo(BigInteger.ZERO) == -1 || it.compareTo(10000.toBigInteger()) == 1) textView.visibility = View.VISIBLE else textView.visibility = View.GONE
    }?: run{
        textView.visibility = View.VISIBLE
    }

}



@BindingAdapter(value = ["countryItems", "viewModel"])
fun setCountryItems(recyclerView: RecyclerView, items: ArrayList<Country>, vm: MainViewModel) {
    recyclerView.adapter?.run {
        if (this is CountryListAdapter) {
            this.submitList(items)
        }
    } ?: run {
        CountryListAdapter(vm).apply {
            recyclerView.adapter = this
            this.submitList(items)
        }
    }
}

@BindingAdapter(value = ["time"])
fun setTime(textView: TextView, time: Long) {
    textView.text = "조회시간 : " + Utils.dataFormat(time)
}


@BindingAdapter(value = ["viewModel", "country"])
fun setReceivedAmount(editText: EditText , vm: MainViewModel, c: Country){
    editText.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            if (editText.text.isEmpty()) return
            vm.setReceivedAmount(c)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


        }

    })
}