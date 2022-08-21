package com.example.taskfood.Menu.Models

import com.google.gson.annotations.SerializedName

class Category(@SerializedName("title") var title: String?) {
    var clicked: Boolean? = false

}