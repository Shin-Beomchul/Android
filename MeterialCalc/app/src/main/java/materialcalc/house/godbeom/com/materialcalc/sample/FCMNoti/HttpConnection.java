package materialcalc.house.godbeom.com.materialcalc.sample.FCMNoti;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by user on 2018-03-19.
 */

public class HttpConnection {
	private OkHttpClient client;
	private static HttpConnection instance = new HttpConnection();
	public static HttpConnection getInstance() {
		return instance;
	}

	private HttpConnection(){ this.client = new OkHttpClient(); }


	/** 웹 서버로 요청을 한다. */
	public void requestWebServer(String url, Callback callback) {
		RequestBody body = new FormBody.Builder()
				.build();
		Request request = new Request.Builder()
				.url(url)
				.get()
				.build();
		client.newCall(request).enqueue(callback);
	}

}
