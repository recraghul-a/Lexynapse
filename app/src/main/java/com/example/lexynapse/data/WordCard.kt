package com.example.lexynapse.data


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lexi")
data class WordCard(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var name: String = "",
    var meaning: String = "",
    var liked: Boolean = false,
    var difficultly: Difficultly = Difficultly.Easy
)

data class Word(
    var name: String = "",
    var meaning: String = "",
    var liked: Boolean = false,
    var difficultly: Difficultly = Difficultly.Easy
)

enum class Difficultly {
    Easy, Medium, Hard
}