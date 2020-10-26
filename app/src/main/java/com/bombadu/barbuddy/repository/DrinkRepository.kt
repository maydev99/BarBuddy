package com.bombadu.barbuddy.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.bombadu.barbuddy.local.DrinkDao
import com.bombadu.barbuddy.local.LocalDrinkData
import com.bombadu.barbuddy.network.DrinkApi
import com.bombadu.barbuddy.network.DrinkData

class DrinkRepository(private val drinkDao: DrinkDao) {

    val getAllDrinks: LiveData<List<LocalDrinkData>> = drinkDao.getAllDrinks()
    //val allDrinkNames: LiveData<List<DrinkNameData>> = drinkDao.getDrinkNameData()

    fun getDataFromNetwork(drinkName: String) {
        GetDataFromNetWorkTask(drinkName, drinkDao).execute()
    }

    fun insertData(localDrinkData: LocalDrinkData) {
        InsertDataAsyncTask(
            drinkDao
        ).execute(localDrinkData)
    }


    private class GetDataFromNetWorkTask(val drinkName: String, val drinkDao: DrinkDao) : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg p0: Unit?) {
            val drinkApi = DrinkApi(drinkDao)
            drinkApi.getTheData(drinkName)

        }
    }

    private class InsertDataAsyncTask(val drinkDao: DrinkDao) : AsyncTask<LocalDrinkData, Unit, Unit>(){
        override fun doInBackground(vararg localDrinkData: LocalDrinkData?) {
            drinkDao.insertDrinks(localDrinkData[0]!!)
        }

    }
}