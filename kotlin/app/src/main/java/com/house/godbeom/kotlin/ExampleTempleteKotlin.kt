package com.house.godbeom.kotlin

import android.widget.TextView

/**
 * Created by Administrator on 2018-04-10.
 */

class ExampleTempleteKotlin<R> : BaseTask<R> {



    internal var resultSubScribe: Subscribe<R>?=null
    internal var consumer: FunctionConsumer<R>?=null


    override fun description(descrition: String): BaseTask<R> {
        return this
    }

    override fun setExampleFunction(consumer: FunctionConsumer<R>): BaseTask<R> {
        this.consumer = consumer
        return this
    }

    override fun print(result: Subscribe<R>): BaseTask<R> {
        resultSubScribe = result
        return this
    }

    override fun print(view: TextView?): BaseTask<*> {
        view?.text = consumer?.Example().toString()
        return this
    }

    override fun doStart() {
        resultSubScribe?.result(consumer?.Example())
    }


}
