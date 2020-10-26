package com.bombadu.barbuddy.network

import com.google.gson.annotations.SerializedName

data class DrinkData(

    var drinks: List<Drink>
) {
    data class Drink(
        var dateModified: String?,
        var idDrink: String,
        var strAlcoholic: String,
        var strDrink: String,
        var strDrinkThumb: String,
        var strIngredient1: String,
        var strIngredient2: String,
        var strIngredient3: String,
        var strIngredient4: String,
        var strIngredient5: String,
        var strIngredient6: String,
        var strIngredient7: String,
        var strIngredient8: String,
        var strIngredient9: String,
        var strInstructions: String,
        var strInstructionsDE: String?,
        var strInstructionsES: Any?,
        var strInstructionsFR: Any?,
        @SerializedName("strInstructionsZH-HANS")
        var strInstructionsZHHANS: Any?,
        @SerializedName("strInstructionsZH-HANT")
        var strInstructionsZHHANT: Any?,
        var strMeasure1: String,
        var strMeasure2: String,
        var strMeasure3: String,
        var strMeasure4: String,
        var strMeasure5: String,
        var strMeasure6: String,
        var strMeasure7: String,
        var strMeasure8: String,
        var strMeasure9: String
    )
}