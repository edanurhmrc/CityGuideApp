package com.example.cityguideapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cityguideapp.data.CityGuideDatabase
import com.example.cityguideapp.model.Categories
import com.example.cityguideapp.repository.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CategoriesViewModel(application: Application) : AndroidViewModel(application) {

    val readAllDataCategory: LiveData<List<Categories>>

    private val repository: CategoriesRepository

    init {
        val categoriesDao = CityGuideDatabase.getDatabase(application).categoriesDao()

        repository = CategoriesRepository(categoriesDao)
        readAllDataCategory = repository.readAllDataCategory
    }

    fun addCategory(categories: Categories){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(categories)
        }
    }

}