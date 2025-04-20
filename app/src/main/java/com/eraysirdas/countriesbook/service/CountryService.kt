package com.eraysirdas.countriesbook.service

import com.eraysirdas.countriesbook.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryService {

    //https://raw.githubusercontent.com/
    // atilsamancioglu/IA19-DataSetCountries/refs/heads/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/refs/heads/master/countrydataset.json")
    fun getCountries() : Single<List<Country>>


}