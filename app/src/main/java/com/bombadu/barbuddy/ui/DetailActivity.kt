package com.bombadu.barbuddy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bombadu.barbuddy.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.extras
        val drinkName = bundle!!.getString("drink_name")
        val drinkIngredients = bundle.getString("drink_ingredients")
        val drinkInstructions = bundle.getString("drink_instructions")
        val drinkImageUrl = bundle.getString("drink_image_url")
        val drinkFavorite = bundle.getString("drink_favorite")
        val drinkId = bundle.getString("drink_id")

        val drinkImageView = findViewById<ImageView>(R.id.detail_image_view)

        Picasso.get().load(drinkImageUrl).into(drinkImageView)
        detail_drink_name_text_view.text = drinkName
        detail_ingredients_text_view.text = drinkIngredients
        detail_instructions_text_view.text = drinkInstructions


    }
}