package com.example.taskfood.Menu.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.taskfood.Menu.Models.Category
import com.example.taskfood.Menu.Models.Food
import com.example.taskfood.NetworkLayer.BaseRepository
import com.example.taskfood.NetworkLayer.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel @ViewModelFactoryDsl constructor(
    private val repository: BaseRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
     val categoryListResponse:MutableLiveData<List<Category>> = repository.observCategories
    val foodListResponse:MutableLiveData<List<Food>> = repository.observFoodList
    var isToolBarCollapsed = false
    val cart:MutableLiveData<ArrayList<Food>> = Repository.cart
    fun fetchCategoryList(){
        repository.fetchCategories()
    }

    fun fetchFoodList(category: String){
        repository.fetchFoodList(category)
    }


}