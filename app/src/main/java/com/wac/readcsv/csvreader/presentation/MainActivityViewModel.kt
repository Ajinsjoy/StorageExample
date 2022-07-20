package com.wac.readcsv.csvreader.presentation

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wac.readcsv.csvreader.domain.repository.DetailsRepository
import com.wac.readcsv.util.Resource
import com.wac.readcsv.util.convertCsvToDataObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
    private val application: Application
) : ViewModel() {
    var details = mutableStateOf(MainUiState())
        private set

    fun saveData(uri: Uri) {
        viewModelScope.launch {
            try{
                val data = application.convertCsvToDataObject(uri)
                if (data != null) {
                    detailsRepository.saveData(data).collect {
                        when (it) {
                            is Resource.Error -> Unit
                            is Resource.Loading -> Unit
                            is Resource.Success -> Log.d("inserted", "saveData:${it.value} ")
                        }
                    }
                }
            }
            catch (e:Exception){
                Log.d("Parsing Exception", "saveData: $e")
            }

        }
    }

    fun getData() {
        viewModelScope.launch {
            detailsRepository.getData().collect {
                when (it) {
                    is Resource.Error -> details.value = details.value.copy(
                        loading = false,
                        details = emptyList(),
                        error = it.error
                    )
                    is Resource.Loading -> details.value =
                        details.value.copy(loading = true, details = emptyList(), error = null)
                    is Resource.Success -> details.value =
                        details.value.copy(loading = false, details = it.value, error = null)
                }
            }
        }
    }
}