package com.example.coroutineswithretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutineswithretrofit.model.CountriesService
import com.example.coroutineswithretrofit.model.Country
import kotlinx.coroutines.*
import retrofit2.Response

class ListViewModel:ViewModel() {

    val countriesService = CountriesService.getCountriesService()
    var job : Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception :  ${throwable.localizedMessage}")
    }

     val countries = MutableLiveData<List<Country>>()
     val countryLoadError = MutableLiveData<String?>()
     val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

       job = CoroutineScope(Dispatchers.IO + exceptionHandler ).launch {
            val response :Response<List<Country>> = countriesService.getCountries()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    countries.value = response.body()
                    countryLoadError.value = null
                    loading.value = false
                }else{
                    onError("error : ${response.message()}" )
                }
            }
        }

    }



    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}