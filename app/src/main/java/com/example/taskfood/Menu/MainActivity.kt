package com.example.taskfood.Menu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.taskfood.Listeners.AppBarStateChangeListener
import com.example.taskfood.Menu.ViewModels.HomeViewModel
import com.example.taskfood.Menu.Adapters.ImageViewPagerRVAdapter
import com.example.taskfood.Menu.Fragments.HomeFragmentDirections
import com.example.taskfood.NetworkLayer.Repository
import com.example.taskfood.R
import com.example.taskfood.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.pager.adapter = ImageViewPagerRVAdapter();
        binding.appbarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                if (state!!.name.equals(State.COLLAPSED.name))
                    viewModel.isToolBarCollapsed=true;
                else
                    viewModel.isToolBarCollapsed=false;
            }

            override fun onScrollChanged(appBarLayout: AppBarLayout?, inset: Int) {
                binding.cartButton.alpha =
                    (Math.abs(inset).toFloat() /appBarLayout!!.totalScrollRange)
                binding.cartCountLayout.alpha =
                    (Math.abs(inset).toFloat() /appBarLayout!!.totalScrollRange)
            }
        })
        viewModel = ViewModelProvider(this,object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel( Repository()) as T
            }
        }).get(HomeViewModel::class.java)
        Repository.cartSize.observe(this) { size ->
            if (size != 0) {
                binding.cartCountLayout.visibility = View.VISIBLE
                binding.countTV.text = size.toString()
            }else{
                binding.cartCountLayout.visibility = View.GONE
                binding.countTV.text = size.toString()
            }
        }
        binding.cartButton.setOnClickListener{
            val action = HomeFragmentDirections.actionGlobalCartActivity(viewModel.cart.value!!.toTypedArray() )
            findNavController(R.id.nav_host).navigate(action);
        }

    }


}