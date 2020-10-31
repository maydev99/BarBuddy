package com.bombadu.barbuddy.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bombadu.barbuddy.local.DrinkDatabase
import com.bombadu.barbuddy.local.LocalDrinkData
import com.bombadu.barbuddy.repository.DrinkRepository

class DrinkViewModel(application: Application) : AndroidViewModel(application) {


    private var repository: DrinkRepository
    private val allDrinks: LiveData<List<LocalDrinkData>>
    private val allFavoriteDrinks: LiveData<List<LocalDrinkData>>


    init {
        val drinkDao = DrinkDatabase.getDatabase(application, viewModelScope).drinkDao()
        repository = DrinkRepository(drinkDao)
        allDrinks = repository.getAllDrinks
        allFavoriteDrinks = repository.getFavoriteDrinks

    }


    fun insertDrinkData(drinkData: LocalDrinkData) {
        repository.insertData(drinkData)
    }


    fun getDrinkDataFromNetwork(drinkName: String) {
        repository.getDataFromNetwork(drinkName)
    }

    fun getAllMyDrinks(): LiveData<List<LocalDrinkData>> {
        Log.d("DATAVM", allDrinks.toString())
        return allDrinks
    }

    fun getFavoriteDrinks(): LiveData<List<LocalDrinkData>> {
        return allFavoriteDrinks
    }

}