package com.example.taskfood.Menu.Adapters

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskfood.Menu.Models.Food
import com.example.taskfood.R
import com.example.taskfood.databinding.FoodItemBinding


class FoodItemAdapter(
    private val foodList: ArrayList<Food>,
    private val listener: (Food) -> Unit,
    private val context:Context
) :
    RecyclerView.Adapter<FoodItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(foodList[position],context,listener)

    }

    override fun getItemCount(): Int {
        return foodList.size;
    }

    class ViewHolder(var itemBinding: FoodItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(food: Food, context: Context, listener: (Food) -> Unit) {
            itemBinding.titleTV.text = food.name;
            itemBinding.descTV.text = food.desc;
            itemBinding.addButton.text = food.price.toString() + "$";
            itemBinding.sizeTV.text = food.sizeText;
            Glide.with(context).load(food.photoLink).placeholder(R.drawable.loading).into(itemBinding.foodIV)
            itemBinding.addButton.setOnClickListener { it->
                listener(food)
                var backgroundColorAnimator: ObjectAnimator = ObjectAnimator.ofObject(
                    it,
                    "backgroundColor",
                    ArgbEvaluator(),
                    0x00000000,-0xfbc800,-0xf025ff, -0xef3bfd, -0xf46eff
                )


                backgroundColorAnimator.duration = 500
                backgroundColorAnimator.start()
                itemBinding.addButton.text = "added +1"
                backgroundColorAnimator= ObjectAnimator.ofObject(
                    it,
                    "backgroundColor",
                    ArgbEvaluator(),
                    -0xf46eff, -0xef3bfd, -0xf025ff,-0xfbc800, 0x00000000
                )



                backgroundColorAnimator.duration = 500
                backgroundColorAnimator.start()
                backgroundColorAnimator.doOnEnd {
                    itemBinding.addButton.text = food.price.toString() + "$"
                }


            }
        }
    }
}
