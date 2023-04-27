package com.example.android101.KotlinMaps

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KotlinPlaceDao {
    @Query("SELECT * FROM Place")
    fun getAll() : List<Place>

    @Insert
    fun insert(place : Place)
    @Delete
    fun delete(place : Place )


}