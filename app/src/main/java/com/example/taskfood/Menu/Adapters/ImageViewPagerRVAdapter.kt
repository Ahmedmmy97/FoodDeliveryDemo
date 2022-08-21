package com.example.taskfood.Menu.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfood.R
import com.example.taskfood.databinding.ViewpagerItemBinding

class ImageViewPagerRVAdapter: RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
       val binding: ViewpagerItemBinding? = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
           R.layout.viewpager_item,parent,false);
       return ImageViewHolder(binding!!.root);
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        return;
    }

    override fun getItemCount(): Int {
        return 3;
    }
}
class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}