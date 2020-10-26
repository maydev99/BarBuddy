package com.bombadu.barbuddy.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bombadu.barbuddy.network.DrinkData

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrinks(localDrinkData: LocalDrinkData)

    @Update
    fun updateDrinks(localDrinkData: LocalDrinkData)

    @Delete
    fun deleteDrink(localDrinkData: LocalDrinkData)

    @Query("DELETE FROM data_table")
    fun deleteAllDrinks()

    /*@Query("SELECT * FROM data_table WHERE drink_name = :drink_name")
    fun getDrinkByDrinkName(drink_name: String?) : LiveData<List<DrinkData>>*/

    @Query("SELECT * FROM data_table ORDER BY drink_name ASC")
    fun getAllDrinks(): LiveData<List<LocalDrinkData>>

    /*@Query("SELECT DISTINCT drink_name FROM data_table ORDER BY drink_name ASC")
    fun getDrinkNameData() : LiveData<List<DrinkNameData>>*/
}