package com.wac.readcsv.csvreader.domain.repository

import com.wac.readcsv.csvreader.domain.data.Details
import com.wac.readcsv.util.Resource
import kotlinx.coroutines.flow.Flow


interface DetailsRepository {
fun saveData(data: List<Details>): Flow<Resource<Boolean>>
fun getData():Flow<Resource<List<Details>>>
}