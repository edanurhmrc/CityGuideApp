package com.example.cityguideapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cityguideapp.model.Place

@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlace(place: Place)

    @Query("SELECT * FROM place_table ORDER BY uid ASC")
    fun readAllDataPlace(): LiveData<List<Place>>

    @Query("SELECT * FROM place_table WHERE feature=1")
    fun readAllDatPlaceWithFeature() : LiveData<List<Place>>

    @Query("SELECT * FROM place_table WHERE category_id =:categoryId")
    fun readAllPlaceByCategory(categoryId: Int) : LiveData<List<Place>>

    @Query("SELECT * FROM place_table WHERE place_name LIKE '%' || :search || '%'")
    fun searchPlace(search: String): LiveData<List<Place>>


}