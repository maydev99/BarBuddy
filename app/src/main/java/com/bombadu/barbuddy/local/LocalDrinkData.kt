package com.bombadu.barbuddy.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "data_table", indices = [Index(value = ["drink_id", "drink_image_url"], unique = true)])
data class LocalDrinkData(
    @ColumnInfo(name = "drink_id") var drink_id: String,
    @ColumnInfo(name = "drink_image_url") var drink_image_url: String,
    @ColumnInfo(name = "drink_instructions") var drink_instructions: String,
    @ColumnInfo(name = "drink_name") var drink_name: String,
    @ColumnInfo(name = "drink_ingredients") var drink_ingredients: String,
    @ColumnInfo(name = "drink_favorite") var drink_favorite: Boolean


) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

