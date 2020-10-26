package com.bombadu.barbuddy.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [LocalDrinkData::class], version = 1, exportSchema = false)
abstract class DrinkDatabase: RoomDatabase() {
    abstract fun drinkDao(): DrinkDao

    private class DrinkDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { _ ->
                scope.launch { }
            }
        }
    }


    companion object {

        @Volatile
        private var INSTANCE: DrinkDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): DrinkDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    DrinkDatabase::class.java,
                    "drink_database"
                ).addCallback(DrinkDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}