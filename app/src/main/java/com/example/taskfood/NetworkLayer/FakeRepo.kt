package com.example.taskfood.NetworkLayer

import androidx.lifecycle.MutableLiveData
import com.example.taskfood.Menu.Models.Category
import com.example.taskfood.Menu.Models.Food
import retrofit2.Callback

class FakeRepo :BaseRepository {
    private val categories = mutableListOf<Category>()
    private val foodList = mutableListOf<Food>()
    override val observCategories: MutableLiveData<List<Category>> = MutableLiveData()
        get() {
            return field
        }
    override val observFoodList: MutableLiveData<List<Food>> = MutableLiveData()
        get() {
            return field
        }
    fun fillCategories(){
        categories.add(Category("Pizza"))

    }
    fun fillMenu(){
        foodList.add(Food("Deluxe","desc",25.0,"250cm","https://i.ibb.co/tKN1Q8r/pizza-placeholder.jpg","Pizza"))
    }
    override fun fetchCategories() {
        observCategories.postValue(categories)
    }

    override fun fetchFoodList(cat: String) {
        observFoodList.postValue(foodList.filter { f->f.category.equals(cat) })
    }
    init {
        fillCategories()
        fillMenu()
    }
}