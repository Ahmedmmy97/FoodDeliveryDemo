package com.example.taskfood.Menu.ViewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.taskfood.NetworkLayer.FakeRepo
import com.example.taskfood.getOrAwaitValueTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest{
    private lateinit var viewModel: HomeViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setup(){
        viewModel = HomeViewModel(FakeRepo())
    }

    @Test
    fun fetchPizzaFoodList(){
        viewModel.fetchFoodList("Pizza")
        val value = viewModel.foodListResponse.getOrAwaitValueTest()
        assert(value.isNotEmpty())
    }
    @Test
    fun fetchSushiFoodList(){
        viewModel.fetchFoodList("Sushi")
        val value = viewModel.foodListResponse.getOrAwaitValueTest()
        assert(value.isNotEmpty())
    }
    @Test
    fun fetchDrinksList(){
        viewModel.fetchFoodList("Drinks")
        val value = viewModel.foodListResponse.getOrAwaitValueTest()
        assert(value.isNotEmpty())
    }
}