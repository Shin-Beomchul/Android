package com.house.godbeom.kotlin;

import android.widget.TextView;

/**
 * Created by Administrator on 2018-04-10.
 */

public interface BaseTask<R> {


	BaseTask description(String descrition);
	BaseTask setExampleFunction(FunctionConsumer<R> consumer);
	BaseTask print(Subscribe<R> result);
	BaseTask print(TextView view);
	void doStart();

}
