package com.wac.readcsv.csvreader.domain.data

data class Details(
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