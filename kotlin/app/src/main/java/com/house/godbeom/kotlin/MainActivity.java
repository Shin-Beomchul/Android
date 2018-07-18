package com.house.godbeom.kotlin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		new ExampleTemplete<Integer>().setExampleFunction(
				 ()-> 1)
				.print(result -> {})
		.doStart();





	}
}
