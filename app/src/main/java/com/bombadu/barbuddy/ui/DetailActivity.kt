package com.bombadu.barbuddy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bombadu.barbuddy.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar = supportActionBar
        actionBar!!.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.gradient))

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)

    }
}