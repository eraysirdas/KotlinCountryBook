package com.eraysirdas.countriesbook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eraysirdas.countriesbook.adapter.CountryAdapter
import com.eraysirdas.countriesbook.databinding.FragmentListBinding
import com.eraysirdas.countriesbook.viewmodel.ListViewModel


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewmodel : ListViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(this)[ListViewModel::class.java]
        viewmodel.refreshData()

        binding.apply {
            recyclerView.layoutManager=LinearLayoutManager(context)
            recyclerView.adapter=countryAdapter

            swipeRefreshLayout.setOnRefreshListener {
                recyclerView.visibility = View.GONE
                countryErrorTv.visibility=View.GONE
                countryLoadingPb.visibility=View.VISIBLE
                viewmodel.refreshFromAPI()
                swipeRefreshLayout.isRefreshing=false
            }
        }



        observeLiveData()


        /*binding.changeBtn.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailsFragment(10)
            it.findNavController().navigate(action)
        }*/
    }

    private fun observeLiveData() {

        viewmodel.countries.observe(viewLifecycleOwner,Observer{countries->
            countries?.let {
                binding.recyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)

            }
        })

        viewmodel.countryError.observe(viewLifecycleOwner,Observer { error ->

            error?.let {
                if(it){
                    binding.countryErrorTv.visibility=View.VISIBLE
                }else{
                    binding.countryErrorTv.visibility=View.GONE
                }
            }
        })

        viewmodel.countryLoading.observe(viewLifecycleOwner,Observer{loading->

            loading?.let {
                if(it){
                    binding.countryLoadingPb.visibility=View.VISIBLE
                    binding.recyclerView.visibility=View.GONE
                    binding.countryErrorTv.visibility=View.GONE
                }else{
                    binding.countryLoadingPb.visibility=View.GONE
                }
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}