package materialcalc.house.godbeom.com.retrofit2.define;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public enum Server {

	// 실 서버
	PRODUCTION("상용 서버",
			"http://m.test.com",
			"http://m.test.com",
			"http://m.test.com"),


	AMAZON("amazon",
			"http://m.test.com",
			"http://m.test.com",
			"http://m.test.com"),


	DEV("개발서버",
			"http://m.test.com",
			"http://m.test.com",
			"http://m.test.com"),;


	private String title;
	private String apiUrl;
	private String webUrl;
	private String mobileWebUrl;

	private Server(String title, String apiUrl, String webUrl, String mobileWebUrl) {
		this.title = title;
		this.apiUrl = apiUrl;
		this.webUrl = webUrl;
		this.mobileWebUrl = mobileWebUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public String getMobileWebUrl() {
		return mobileWebUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[" + title + "] " + apiUrl);
		return builder.toString();
	}

	private static String KEY_SERVER_TYPE = "KEY_SERVER_TYPE";

	public static Server getCurrentServerType(Context context) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		int serverTypeValue = pref.getInt(KEY_SERVER_TYPE, -1);

		Server serverType = null;
		if (serverTypeValue < 0) {
			serverType = null;
		} else {
			serverType = values()[serverTypeValue];
		}
		return serverType;
	}

	public static void setCurrentServerType(Context context, int which) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		pref.edit().putInt(KEY_SERVER_TYPE, which).commit();
	}
}