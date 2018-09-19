package materialcalc.house.godbeom.com.materialcalc;

import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

import io.realm.Realm;
import materialcalc.house.godbeom.com.retrofit2.core.API;
import materialcalc.house.godbeom.com.retrofit2.define.Server;

/**
 * Created by Administrator on 2018-03-02.
 */

public class App extends MultiDexApplication {

	@Override
	public void onCreate() {
		super.onCreate();
		FirebaseApp.initializeApp(this);
		Server serverType = Server.valueOf(BuildConfig.SERVER_TYPE);
		API.instance().initialize(this, serverType);
		Realm.init(getApplicationContext());
	}
}
