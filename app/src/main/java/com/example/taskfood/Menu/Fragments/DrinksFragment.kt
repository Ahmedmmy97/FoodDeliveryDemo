package com.example.taskfood.Menu.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskfood.CustomUIElements.SwipeableFrameLayout
import com.example.taskfood.Menu.Adapters.CustomAdapter
import com.example.taskfood.Menu.Adapters.FoodItemAdapter
import com.example.taskfood.Menu.Models.Category
import com.example.taskfood.Menu.Models.Food
import com.example.taskfood.Menu.ViewModels.HomeViewModel
import com.example.taskfood.NetworkLayer.Repository
import com.example.taskfood.databinding.HomeFragmentBinding


class DrinksFragment : Fragment() , SwipeableFrameLayout.onSwipeEventDetected {


    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private val cats = arrayListOf(Category("Drinks"), Category("Pizza"), Category("Sushi"));
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        binding.frameLayout.registerToSwipeEvents(this)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Categories will be hardcoded static data
       /* viewModel.fetchCategoryList()
        viewModel.categoryListResponse.observe(viewLifecycleOwner) { cats ->

        }*/
        binding.barRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )

        cats[0].clicked = true
        binding.barRecycler.adapter = CustomAdapter(cats, requireContext(),object : CustomAdapter.OnItemClickListener {
            override fun onClick(category: Category) {
                category.title!!.let {
                    if (it.equals(cats[1].title)){
                        val action = DrinksFragmentDirections.actionDrinksFragmentToHomeFragment()
                        findNavController().navigate(action)
                    }else if (it.equals(cats[2].title)){
                        val action = DrinksFragmentDirections.actionDrinksFragmentToSushiFragment()
                        findNavController().navigate(action)
                    }

                }
            }
        })
        viewModel.fetchFoodList(cats[0].title!!)




        viewModel.foodListResponse.observe(viewLifecycleOwner) { foodList ->
            if (foodList!=null) {
                binding.Recycler.layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL, false
                )
                binding.Recycler.adapter = FoodItemAdapter(foodList as ArrayList<Food>, { food->

                    Repository.cartSize.postValue(Repository.cartSize.value?.plus(1));
                    viewModel.cart.value!!.add(food)
                }, requireContext())
            }
        }


    }

    override fun swipeLeftDetected() {
        if (viewModel.isToolBarCollapsed){
            val action =
                DrinksFragmentDirections.actionDrinksFragmentToHomeFragment()
            findNavController().navigate(action)
        }

    }

    override fun swipeRightDetected() {
        if (viewModel.isToolBarCollapsed){
           findNavController().popBackStack();
        }
    }

}

