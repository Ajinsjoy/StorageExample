package com.wac.readcsv.csvreader.data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wac.readcsv.csvreader.data.data.local.dao.DetailsDao
import com.wac.readcsv.csvreader.data.data.local.entity.DetailsEntity

@Database(
    entities = [DetailsEntity::class],
    version = 1
)
abstract class CSVAppDatabase : RoomDatabase() {
    abstract val detailsDao: DetailsDao
}