package com.wac.readcsv.csvreader.data.repository

import android.util.Log
import com.wac.readcsv.csvreader.data.data.local.dao.DetailsDao
import com.wac.readcsv.csvreader.data.data.mapper.toDetails
import com.wac.readcsv.csvreader.data.data.mapper.toDetailsEntity
import com.wac.readcsv.csvreader.domain.data.Details
import com.wac.readcsv.csvreader.domain.repository.DetailsRepository
import com.wac.readcsv.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(private val detailsDao: DetailsDao) :
    DetailsRepository {
    override fun saveData(data: List<Details>): Flow<Resource<Boolean>> = flow {
        try {
            saveDetails(data)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Success(false))
        }
    }

    override fun getData(): Flow<Resource<List<Details>>> = flow {
        try {
            detailsDao.getDetails().collect { data ->
                emit(Resource.Success(data.map { it.toDetails() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong"))
        }


    }

    private suspend fun saveDetails(data: List<Details>?) {
        val dataList = data?.map { it.toDetailsEntity() }
        Log.d("inserted", "saveData:$dataList ")
        detailsDao.insertData(dataList)
    }


}
