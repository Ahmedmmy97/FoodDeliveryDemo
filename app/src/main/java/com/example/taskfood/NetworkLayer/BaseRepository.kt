package com.example.taskfood.NetworkLayer

import androidx.lifecycle.MutableLiveData
import com.example.taskfood.Menu.Models.Category
import com.example.taskfood.Menu.Models.Food
import retrofit2.Callback

interface BaseRepository {
    fun fetchCategories()
    fun fetchFoodList(cat:String)
    val observCategories: MutableLiveData<List<Category>>
    val observFoodList: MutableLiveData<List<Food>>
}