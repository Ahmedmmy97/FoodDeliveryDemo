package com.example.taskfood.NetworkLayer

import com.example.taskfood.Menu.Models.Category
import com.example.taskfood.Menu.Models.Food
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodApi {
    //API
    @GET("api/getCategories")
    fun fetchCategories(): Call<List<Category>?>?

    @GET("api/getFoodList")
    fun fetchFoodList(
        @Query("category") cat: String,
    ): Call<List<Food>?>?


    companion object {

        const val BASE_URL = "https://google.com/"

    }
}
