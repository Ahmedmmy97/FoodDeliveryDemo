package com.example.taskfood.Cart.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskfood.Cart.Adapters.CardItemAdapter
import com.example.taskfood.Cart.CartActivityArgs
import com.example.taskfood.Cart.Viewmodels.CartViewModel
import com.example.taskfood.Menu.Models.Food
import com.example.taskfood.Menu.ViewModels.HomeViewModel
import com.example.taskfood.NetworkLayer.Repository
import com.example.taskfood.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    private val viewModel: CartViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater)

        init()
        return binding.root
    }

    private fun init() {
        binding.cartRV.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        viewModel.getCart().observe(viewLifecycleOwner){
            if (it!=null) {
                val foodItems = it;
                binding.cartRV.adapter = context?.let { CardItemAdapter(foodItems, it,object :CardItemAdapter.OnItemClickListener{
                    override fun onClick( item: Food) {
                        viewModel.getCart().value?.remove(item)
                        viewModel.getCart().postValue(viewModel.getCart().value)
                        Repository.cartSize.postValue(Repository.cartSize.value?.minus(1));
                    }
                }) }
                var total = 0.0;
                foodItems.forEach {
                    total += it.price!!
                }
                binding.totalTV.text = "$total usd"
            }
        }
        //val cartItems :ArrayList<Food> = (CartActivityArgs.fromBundle(activity?.intent?.extras!!).cartItems).toCollection(ArrayList())


    }


}