package com.eraysirdas.countriesbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.eraysirdas.countriesbook.R
import com.eraysirdas.countriesbook.databinding.FragmentDetailsBinding
import com.eraysirdas.countriesbook.databinding.FragmentListBinding
import com.eraysirdas.countriesbook.util.intoGlide
import com.eraysirdas.countriesbook.util.placeholderProgressBar
import com.eraysirdas.countriesbook.viewmodel.DetailsViewModel


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var countryUuid = 0
    private lateinit var viewModel : DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { countryUuid = DetailsFragmentArgs.fromBundle(it).countryUuid }

        viewModel= ViewModelProvider(this)[DetailsViewModel::class.java]
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {country->
            country?.let {
                //data binding
                binding.country = it

                /*binding.countryName.text = country.countryName
                binding.countryCapital.text = country.countryCapital
                binding.countryRegion.text = country.countryRegion
                binding.countryCurrency.text = country.countryCurrency
                binding.countryLanguage.text = country.countryLanguage

                binding.countryFlagIv.intoGlide(country.countryImageUrl
                    ,placeholderProgressBar(requireContext()))
                 */
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}