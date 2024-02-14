package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        Contact::class,
        Note::class,
               ],
    version=3
)
abstract class MyDatabase: RoomDatabase() {
    abstract val contactDao : ContactDao
    abstract val noteDao : NoteDao
}