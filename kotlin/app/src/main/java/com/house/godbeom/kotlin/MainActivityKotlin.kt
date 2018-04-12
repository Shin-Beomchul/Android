package com.house.godbeom.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivityKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ExampleTempleteKotlin<Int>()
                .setExampleFunction {
                    var temp: String? = null
                     temp?.length

                }

                .print { tv.text=it.toString()}
                .doStart()


    }


    fun a (){

    }
}
