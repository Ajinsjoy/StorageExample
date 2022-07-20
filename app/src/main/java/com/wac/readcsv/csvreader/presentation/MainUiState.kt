package com.wac.readcsv.csvreader.presentation

import com.wac.readcsv.csvreader.domain.data.Details

data class MainUiState(
    val loading : Boolean = false,
    val details : List<Details> = emptyList(),
    val error : String? = null
)
