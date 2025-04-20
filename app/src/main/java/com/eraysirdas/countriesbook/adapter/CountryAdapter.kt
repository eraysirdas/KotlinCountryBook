package com.eraysirdas.countriesbook.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eraysirdas.countriesbook.databinding.RowListBinding
import com.eraysirdas.countriesbook.model.Country
import androidx.navigation.findNavController
import com.eraysirdas.countriesbook.R
import com.eraysirdas.countriesbook.util.intoGlide
import com.eraysirdas.countriesbook.util.placeholderProgressBar
import com.eraysirdas.countriesbook.view.ListFragmentDirections

class CountryAdapter (private var countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.PostHolder>(),CountryClickListener{
    class PostHolder (val binding : RowListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        //val binding = RowListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        //data binding
        val binding = DataBindingUtil.inflate<RowListBinding>(LayoutInflater.from(parent.context),R.layout.row_list,parent,false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        //data binding
        holder.binding.country = countryList[position]
        holder.binding.listener = this

        /*holder.binding.headTv.text = countryList[position].countryName
        holder.binding.descTv.text = countryList[position].countryCapital

        holder.binding.imageView.intoGlide(countryList[position].countryImageUrl
            ,placeholderProgressBar(holder.itemView.context)
        )

        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailsFragment(countryList[position].uuid)
            it.findNavController().navigate(action)
        }*/

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()

    }

    //data binding
    override fun onCountryClicked(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(uuid)
        v.findNavController().navigate(action)
    }
}