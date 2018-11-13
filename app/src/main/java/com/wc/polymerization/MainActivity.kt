package com.wc.polymerization

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import okhttp.NetWorkManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


     fun apiRequest(view: View){
        NetWorkManager.getRequest().getWeatherByAddress("北京","json","11ffd27d38deda622f51c9d314d46b17")
    }
}
