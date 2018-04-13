package com.house.godbeom.kotlin

/**
 * Created by Administrator on 2018-04-10.
 */

interface BaseTaskKotlin<R> {


    fun description(descrition: String): BaseTaskKotlin<*>
    fun setExampleFunction(consumer: FunctionConsumer<R>): BaseTaskKotlin<*>
    fun print(result: Subscribe<R>): BaseTaskKotlin<*>

}
