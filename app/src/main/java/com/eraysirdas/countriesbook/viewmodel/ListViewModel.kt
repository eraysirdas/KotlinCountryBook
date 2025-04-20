package com.eraysirdas.countriesbook.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.eraysirdas.countriesbook.db.database.CountryDatabase
import com.eraysirdas.countriesbook.model.Country
import com.eraysirdas.countriesbook.service.CountryAPIService
import com.eraysirdas.countriesbook.util.CustomSharedPreferences
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L //10 minute  nanoTime

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {

        val updateTime = customSharedPreferences.getTime()
        if(updateTime!=null && updateTime !=0L && System.nanoTime()-updateTime<refreshTime){
            getDataFromSQLite()
        }else{
            getDataFromAPI()

        }
    }

    fun refreshFromAPI(){
        getDataFromAPI()
    }

    private fun getDataFromSQLite() {
        countryLoading.value=true
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            //Toast.makeText(getApplication(), "Countries From SQLite", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataFromAPI(){

        countryLoading.value=true

        disposable.add(
            countryAPIService.getData()
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(succesList: List<Country>) {

                        storeInSQLite(succesList)
                        //Toast.makeText(getApplication(), "Countries From API", Toast.LENGTH_SHORT).show()

                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value=false
                        countryError.value=true
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showCountries(countryList : List<Country>){
        countries.value=countryList
        countryError.value=false
        countryLoading.value=false
    }

    private fun storeInSQLite(countryList: List<Country>) {

        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong= dao.insertAll(*countryList.toTypedArray())
            for(i in countryList.indices){
              countryList[i].uuid = listLong[i].toInt()
            }
            showCountries(countryList)
        }

        customSharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}