package materialcalc.house.godbeom.com.materialcalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import materialcalc.house.godbeom.com.materialcalc.sample.stickys.ActStickySample;

public class MainActivity extends AppCompatActivity {



	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


//		startActivity(new Intent(MainActivity.this,ActFreeRippleSample.class));

//		startActivity(new Intent(MainActivity.this,ActRemoteConfig.class));

		startActivity(new Intent(MainActivity.this,ActStickySample.class));


	}
}
