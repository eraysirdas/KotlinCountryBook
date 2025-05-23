package com.eraysirdas.countriesbook.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import kotlinx.coroutines.internal.synchronized

class CustomSharedPreferences {

    companion object{

        private const val PREFERENCES_TIME=""
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : CustomSharedPreferences? = null

        private val lock = Any()

        operator fun  invoke(context: Context) = instance ?: kotlin.synchronized(lock){
            instance ?: makeCustomSharedPreferences(context).also {
                instance = it
            }
        }

        private fun makeCustomSharedPreferences(context: Context) : CustomSharedPreferences{

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime(time : Long){
       sharedPreferences?.edit(true){
            putLong(PREFERENCES_TIME,time)
       }
    }

    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TIME,0)
}