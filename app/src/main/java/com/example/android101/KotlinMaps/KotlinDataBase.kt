package com.example.android101.KotlinMaps

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Place::class], version = 1)
abstract class KotlinDataBase : RoomDatabase() {
    abstract fun KotlinPlaceDao(): KotlinPlaceDao

}