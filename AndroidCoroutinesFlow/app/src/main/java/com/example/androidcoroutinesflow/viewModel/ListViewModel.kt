package com.example.androidcoroutinesflow.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androidcoroutinesflow.model.NewsRepository

class ListViewModel: ViewModel() {

    val newsArticles = NewsRepository().getNewsArticles().asLiveData()

}