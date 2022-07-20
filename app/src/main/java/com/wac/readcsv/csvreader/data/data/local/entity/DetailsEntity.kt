package com.wac.readcsv.csvreader.data.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class DetailsEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int=0,
    val Industry_aggregation_NZSIOC: String,
    val Industry_code_ANZSIC06: String,
    val Industry_code_NZSIOC: String,
    val Industry_name_NZSIOC: String,
    val Units: String,
    val Value: String,
    val Variable_category: String,
    val Variable_code: String,
    val Variable_name: String,
    val Year: String
)
