package kr.co.example.codingtest_wirebarley.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kr.co.example.codingtest_wirebarley.R
import kr.co.example.codingtest_wirebarley.base.BaseViewHolder
import kr.co.example.codingtest_wirebarley.databinding.ItemCountryBinding
import kr.co.example.codingtest_wirebarley.vo.Country

class CountryListAdapter(val vm : MainViewModel): ListAdapter<Country, CountryListAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.vm = vm
        holder.binding.country = getItem(position)
    }

    inner class ItemViewHolder(view: View): BaseViewHolder<ItemCountryBinding>(view)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>(){
            override fun areItemsTheSame(oldItem: Country, newItem: Country) = oldItem.rate == newItem.rate

            override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem
        }
    }


}