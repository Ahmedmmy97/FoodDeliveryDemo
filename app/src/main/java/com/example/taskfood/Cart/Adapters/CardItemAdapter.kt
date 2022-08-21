package com.example.taskfood.Cart.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskfood.Menu.Models.Category
import com.example.taskfood.Menu.Models.Food
import com.example.taskfood.R
import com.example.taskfood.databinding.CartItemLayoutBinding



class CardItemAdapter(
    private val foodList: ArrayList<Food>,
    private val context:Context,
    private val deleteListener:OnItemClickListener
) :
    RecyclerView.Adapter<CardItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = CartItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(foodList[position],context,deleteListener)

    }

    override fun getItemCount(): Int {
        return foodList.size;
    }

    class ViewHolder(var itemBinding: CartItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(food: Food, context: Context,listener: OnItemClickListener) {
            Glide.with(context).load(food.photoLink).placeholder(R.drawable.loading).into(itemBinding.foodIV)
            itemBinding.titleTV.text = food.name;
            itemBinding.price.text = food.price.toString() + " usd";
            itemBinding.deleteIV.setOnClickListener { it->

                listener.onClick(food)

            }
        }
    }
    interface OnItemClickListener{
        fun onClick(item:Food);
    }
}
