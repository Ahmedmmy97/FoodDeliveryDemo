package com.example.taskfood.Menu.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfood.Menu.Models.Category
import com.example.taskfood.R
import com.example.taskfood.databinding.ItemRecyclerBinding

class CustomAdapter(
    private val titleList: ArrayList<Category>,
    private val context:Context,
    private val onClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(titleList[position],context)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(titleList[position])
        }
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    class ViewHolder(var itemBinding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(category: Category, context: Context) {
            itemBinding.titleTV.text = category.title;
            if (category.clicked!=null && category.clicked!!){
                itemBinding.titleTV.setTextColor(  context.getColor(R.color.black))
            }else{
                itemBinding.titleTV.setTextColor(  context.getColor(R.color.unactiveTab))
            }
        }
    }
    interface OnItemClickListener{
        fun onClick(category: Category);
    }
}
