package com.house.godbeom.kotlin.shape;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by god on 2018. 7. 31..
 */

public class RootPager {

  protected 	RootPager(Context context){
		Toast.makeText(context,"Single",Toast.LENGTH_SHORT);
	}

	protected RootPager(Context context, String attr){
		Toast.makeText(context,"Context-With-attr",Toast.LENGTH_SHORT);
	}

}
