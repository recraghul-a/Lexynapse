package com.example.lexynapse.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordCard::class], version = 1, exportSchema = false)
abstract class LexiDatabase: RoomDatabase() {
    abstract fun lexiDao(): LexiDao

    companion object {
        @Volatile
        private var Instance: LexiDatabase? = null

        fun getDatabase(context: Context): LexiDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    LexiDatabase::class.java,
                    "lexi_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}