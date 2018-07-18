package com.house.godbeom.kotlin;

import android.widget.TextView;

/**
 * Created by Administrator on 2018-04-10.
 */

public class ExampleTemplete<R> implements BaseTask<R> {


	Subscribe<R> resultSubScribe;
	FunctionConsumer<R> consumer;
	@Override
	public BaseTask<R> description(String descrition) {
		return this;
	}

	@Override
	public BaseTask<R> setExampleFunction(FunctionConsumer<R> consumer) {
		this.consumer = consumer;
		return this;
	}

	@Override
	public BaseTask<R> print(Subscribe<R> result) {
		resultSubScribe = result;
		return this;
	}

	@Override
	public BaseTask print(TextView view) {
		String  result=String.valueOf(consumer.Example());
		view.setText(result);
		return this;
	}

	@Override
	public void doStart(){
		resultSubScribe.result(consumer.Example());
	}



}
