package com.wac.readcsv.csvreader.data.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wac.readcsv.csvreader.data.data.local.entity.DetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(details: List<DetailsEntity>?)
//
    @Query("SELECT * FROM data_table")
    fun getDetails() : Flow<List<DetailsEntity>>

}