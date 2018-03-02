package materialcalc.house.godbeom.com.materialcalc;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by Administrator on 2018-03-02.
 */

public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FirebaseApp.initializeApp(this);
	}
}
