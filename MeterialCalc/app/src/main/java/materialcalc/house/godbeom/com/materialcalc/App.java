package materialcalc.house.godbeom.com.materialcalc;

import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

/**
 * Created by Administrator on 2018-03-02.
 */

public class App extends MultiDexApplication {

	@Override
	public void onCreate() {
		super.onCreate();
		FirebaseApp.initializeApp(this);
	}
}
