package com.example.taskfood.NetworkLayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taskfood.Menu.Models.Category
import com.example.taskfood.Menu.Models.Food
import com.example.taskfood.NetworkLayer.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository() :BaseRepository {
    companion object{
        val cartSize : MutableLiveData<Int> =MutableLiveData(0)
        val cart : MutableLiveData<ArrayList<Food>> =MutableLiveData(ArrayList())
    }

    override val observCategories: MutableLiveData<List<Category>> = MutableLiveData()
        get() {
            return field
        }
    override val observFoodList: MutableLiveData<List<Food>> = MutableLiveData()
        get() {
            return field
        }
    override fun fetchCategories() {
        //val category = arrayListOf<Category>(Category("Pizza"),Category("Sushi"),Category("Drinks"))
        val client = ApiService.ApiCall()
        val call: Call<List<Category>?>? = client.fetchCategories()
        call!!.enqueue(object : Callback<List<Category>?>{
            override fun onResponse(
                call: Call<List<Category>?>,
                response: Response<List<Category>?>
            ) {
                observCategories.value = response.body()
            }

            override fun onFailure(call: Call<List<Category>?>, t: Throwable) {
                observCategories.value =null;
            }
        })
    }
    override fun fetchFoodList(cat:String) {
        val client = ApiService.ApiCall()
        val call: Call<List<Food>?>? = client.fetchFoodList(cat)
        call!!.enqueue(object : Callback<List<Food>?>{
            override fun onResponse(
                call: Call<List<Food>?>,
                response: Response<List<Food>?>
            ) {
                observFoodList.value = response.body()
            }

            override fun onFailure(call: Call<List<Food>?>, t: Throwable) {
                observFoodList.value =null;
            }
        })
    }

}