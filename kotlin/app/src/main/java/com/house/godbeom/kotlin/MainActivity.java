package com.house.godbeom.kotlin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.house.godbeom.kotlin.shape.MyExtendPager;
import com.house.godbeom.kotlin.shape.SinglePager;
import com.house.godbeom.kotlin.testmodule.ExampleTemplete;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		new ExampleTemplete<Integer>().setExampleFunction(
				 ()-> 1)
				.print(result -> {})
		.doStart();


		new MyExtendPager(getApplicationContext(),"tq");
		new SinglePager();


	}
}
