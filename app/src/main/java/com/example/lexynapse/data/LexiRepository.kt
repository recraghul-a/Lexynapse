package com.example.lexynapse.data

import kotlinx.coroutines.flow.Flow

interface LexiRepository {
    suspend fun insertWord(word: WordCard)
    fun getWords(): Flow<List<WordCard>>
    suspend fun deleteWord(word: WordCard)
    fun getCard(id: Int): Flow<WordCard?>
}

class OfflineLexiRepository(private val lexiDao: LexiDao): LexiRepository {
    override fun getWords(): Flow<List<WordCard>> = lexiDao.getWords()
    override suspend fun insertWord(word: WordCard) = lexiDao.insertWord(word)
    override suspend fun deleteWord(word: WordCard) = lexiDao.delete(word)
    override fun getCard(id: Int) = lexiDao.getCard(id)
}