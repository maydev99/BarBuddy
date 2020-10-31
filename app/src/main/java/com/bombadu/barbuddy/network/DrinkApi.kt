package com.bombadu.barbuddy.network

import android.util.Log
import com.bombadu.barbuddy.local.DrinkDao
import com.bombadu.barbuddy.repository.DrinkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
class DrinkApi(drinkDao: DrinkDao) {

    private var repository: DrinkRepository = DrinkRepository(drinkDao)

    fun getTheData(search: String) {
        val request = ServiceBuilder.buildService(DrinkDBEndpoints::class.java)
        val call = request.getDrinks(search)

        call.enqueue(object : Callback<DrinkData> {
            override fun onResponse(call: Call<DrinkData>, response: Response<DrinkData>) {

                if (response.isSuccessful) {
                    val rawData = response.body()!!.drinks
                    Log.d("RESPONSE", rawData.toString())

                    if (rawData.isNullOrEmpty()) {
                        Log.d("ERROR", "ERROR: ${response.code()}")
                    } else {
                        for (i in rawData.indices) {
                            val ingredientList = mutableListOf<String>()
                            val measurementList = mutableListOf<String>()
                            val nullEliminatedList = mutableListOf<String>()

                            val drinkName = rawData[i].strDrink
                            val drinkInstructions = rawData[i].strInstructions
                            val drinkImageUrl = rawData[i].strDrinkThumb
                            val drinkId = rawData[i].idDrink

                            /**
                             * Gets Ingredients and Measurements from API
                             * adds to ingredient list
                             */
                            ingredientList.add(rawData[i].strIngredient1)
                            ingredientList.add(rawData[i].strIngredient2)
                            ingredientList.add(rawData[i].strIngredient3)
                            ingredientList.add(rawData[i].strIngredient4)
                            ingredientList.add(rawData[i].strIngredient5)
                            ingredientList.add(rawData[i].strIngredient6)
                            ingredientList.add(rawData[i].strIngredient7)
                            ingredientList.add(rawData[i].strIngredient8)
                            ingredientList.add(rawData[i].strIngredient9)


                            measurementList.add(rawData[i].strMeasure1)
                            measurementList.add(rawData[i].strMeasure2)
                            measurementList.add(rawData[i].strMeasure3)
                            measurementList.add(rawData[i].strMeasure4)
                            measurementList.add(rawData[i].strMeasure5)
                            measurementList.add(rawData[i].strMeasure6)
                            measurementList.add(rawData[i].strMeasure6)
                            measurementList.add(rawData[i].strMeasure7)
                            measurementList.add(rawData[i].strMeasure8)
                            measurementList.add(rawData[i].strMeasure9)

                            //Eliminating Null values
                            ingredientList.removeAll(listOf(null))
                            measurementList.removeAll(listOf(null))

                            /**
                             * Code Below takes the measurements list and
                             * combines with the ingredients list. Even though
                             * both lists may be different sizes
                             */

                            //Gets Measurement List Size
                            val measSize = measurementList.size - 1

                            //Loops through Ingredient List (same or longer than measurement list)
                            for (x in 0 until ingredientList.size) {
                                val ingredients =
                                    ingredientList[x] //gets each ingredient for recipe

                                //Check if measurement exits fot the ingredient
                                val combined = if (x <= measSize) {
                                    val measurements = measurementList[x]
                                    "$measurements $ingredients" //Concatenates measurement and ingredient if both exist
                                } else {
                                    ingredients //If no measurement, sets just ingredient to combined value
                                }

                                //Adds each ingredient/measurement to List
                                nullEliminatedList.add(combined)
                            }

                            /**
                             * Appends each ingredient for a recipe to a single string
                             * with line breaks
                             */
                            val strBuilder = StringBuilder()
                            for (index in 0 until nullEliminatedList.size) {
                                val ingr = nullEliminatedList[index]
                                strBuilder.appendLine(ingr)

                            }
                            val ingredients = strBuilder.toString()

                            //Adds all recipe data to local db model
                            val newInsert = (com.bombadu.barbuddy.local.LocalDrinkData(
                                drinkId,
                                drinkImageUrl,
                                drinkInstructions,
                                drinkName,
                                ingredients,
                                false
                            ))
                            //Inserts data in to Room Database
                            repository.insertData(newInsert)
                        }

                    }
                }

            }

            override fun onFailure(call: Call<DrinkData>, t: Throwable) {
                Log.d("ERROR", "${t.message}")
            }


        })

    }
}