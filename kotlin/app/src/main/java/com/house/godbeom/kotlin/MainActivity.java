package com.house.godbeom.kotlin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ExampleTemplete<Integer> d = new ExampleTemplete<Integer>();

		 d.setExampleFunction(new FunctionConsumer<Integer>() {
			 @Override
			 public Integer Example() {
			 	int a= 3;

				 return 1;
			 }


		 }).print(new Subscribe<Integer>() {
			 @Override
			 public void result(Integer result) {

			 }
		 })
		 .doStart();






	}
}
