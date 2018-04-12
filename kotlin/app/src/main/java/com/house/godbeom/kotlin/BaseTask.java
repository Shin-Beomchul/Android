package com.house.godbeom.kotlin;

/**
 * Created by Administrator on 2018-04-10.
 */

public interface BaseTask<R> {


	BaseTask description(String descrition);
	BaseTask setExampleFunction(FunctionConsumer<R> consumer);
	BaseTask print(Subscribe<R> result);
	void doStart();

}
