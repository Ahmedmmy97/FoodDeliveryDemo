package com.example.taskfood.Cart

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.example.taskfood.Cart.Viewmodels.CartViewModel
import com.example.taskfood.Menu.ViewModels.HomeViewModel
import com.example.taskfood.R
import com.example.taskfood.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var viewModel: CartViewModel
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.appBar.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        navController = findNavController(this, R.id.nav_host)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        viewModel.getCart().postValue(CartActivityArgs.fromBundle(intent.extras!!).cartItems.toCollection(ArrayList()))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() ||super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}