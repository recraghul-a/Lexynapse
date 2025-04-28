package com.example.lexynapse.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LexiDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: WordCard)

    @Query("SELECT * FROM lexi")
    fun getWords(): Flow<List<WordCard>>

    @Delete
    suspend fun delete(wordCard: WordCard)

    @Query("SELECT * FROM lexi WHERE id = :id")
    fun getCard(id: Int): Flow<WordCard?>

    @Query("SELECT * FROM lexi WHERE liked = 1")
    fun getLikedCards(): Flow<List<WordCard>>
}