package com.example.taskfood.Cart.Viewmodels

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


class CartViewModel : ViewModel() {

    private val cart:MutableLiveData<ArrayList<Food>> = Repository.cart

    fun getCart():MutableLiveData<ArrayList<Food>>{
        return cart
    }


}