package com.house.godbeom.kotlin.shape

import android.content.Context
import android.util.Log

/**
 * Created by god on 2018. 7. 31..
 */

class MyExtendPager : RootPager {

    init {
        Log.i("생성자","init{ }")
    }




    constructor(context: Context) : super(context){
        Log.i("생성자","constructor(Context )")
    }
    constructor(context: Context,attr:String) : super(context,attr){
        Log.i("생성자","constructor(Context ,attr )")
    }





}