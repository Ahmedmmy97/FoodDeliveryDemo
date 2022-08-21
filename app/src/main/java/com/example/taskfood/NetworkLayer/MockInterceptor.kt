package com.example.taskfood.NetworkLayer

import com.example.taskfood.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url

            val responseString = when {
                //uri.pathSegments.last().endsWith("getCategories") -> getListOfCategoryJson
                uri.pathSegments.last().endsWith("getFoodList") -> {
                    val query = chain.request().url.queryParameter("category")
                    when{
                        query.equals("Pizza") -> PizzaFoodJson
                        query.equals("Sushi") -> SushiFoodJson
                        else -> "[]"
                    }
                }
                else -> "[]"
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray()
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }

}

const val getListOfCategoryJson = """
[{
	"title": "Pizza"
	
},{
    "title": "Sushi"
},
{
    "title": "Drinks"
}
]
"""
const val PizzaFoodJson = """
[{
    "name":"Hawaiian",
    "desc":"Chiken, ham, pepperoni, tomato sauce, spicy chorizo and mozzarella",
    "price":46.0,
    "sizeText":"150 grams, 35 cm",
    "photoLink":"https://i.ibb.co/5LJ8Cy2/hawaiian-pizza.jpg",
    "category":"Pizza"
},
{
    "name":"Deluxe",
    "desc":"Chiken, ham, pepperoni, tomato sauce, spicy chorizo and mozzarella",
    "price":46.0,
    "sizeText":"150 grams, 35 cm",
    "photoLink":"https://i.ibb.co/tKN1Q8r/pizza-placeholder.jpg",
    "category":"Pizza"
},
{
    "name":"Deluxe",
    "desc":"Chiken, ham, pepperoni, tomato sauce, spicy chorizo and mozzarella",
    "price":46.0,
    "sizeText":"150 grams, 35 cm",
    "photoLink":"https://i.ibb.co/1XzcqXk/side.jpg",
    "category":"Pizza"
}
]
"""
const val SushiFoodJson = """
[{
    "name":"Deluxe",
    "desc":"Chiken, ham, pepperoni, tomato sauce, spicy chorizo and mozzarella",
    "price":23.0,
    "sizeText":"150 grams, 35 cm",
    "photoLink":"https://i.ibb.co/yX2JDYG/sushi1.jpg",
    "category":"Sushi"
},
{
    "name":"Hawaiian",
    "desc":"Chiken, ham, pepperoni, tomato sauce, spicy chorizo and mozzarella",
    "price":23.0,
    "sizeText":"150 grams, 35 cm",
    "photoLink":"https://i.ibb.co/CVrSQM7/sushi2.jpg",
    "category":"Sushi"
},
{
    "name":"Deluxe",
    "desc":"Chiken, ham, pepperoni, tomato sauce, spicy chorizo and mozzarella",
    "price":23.0,
    "sizeText":"150 grams, 35 cm",
    "photoLink":"https://i.ibb.co/ngG5RVk/sushi3.jpg",
    "category":"Sushi"
}
]
"""
