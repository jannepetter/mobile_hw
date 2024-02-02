package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val name:String,
    val email:String,
    val phone:String,
    val image:Int,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0
)
