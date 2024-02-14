package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val data:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0
)
