package com.eraysirdas.countriesbook.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eraysirdas.countriesbook.model.Country

@Dao
interface CountryDao {

    @Query("Select * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("Select * FROM country WHERE uuid IN(:countryId)")
    suspend fun getCountry(countryId : Int) : Country

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}