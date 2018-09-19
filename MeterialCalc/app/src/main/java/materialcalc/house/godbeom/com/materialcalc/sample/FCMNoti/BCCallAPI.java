package materialcalc.house.godbeom.com.materialcalc.sample.FCMNoti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by user on 2018-03-19.
 */

public class BCCallAPI extends BroadcastReceiver {
	private final String TAG 								= "BCCallAPI";

	public static final String ACTION_EVAL					= "com.example.user.fcmpush.CallEvalAPI";

	public static final String RESULT_GREAT								= "RESULT_GREAT";
	public static final String RESULT_GOOD								= "RESULT_GOOD";
	public static final String RESULT_NOTBAD							= "RESULT_NOTBAD";

	private static final String baseUrl						= "http://m.demo.demo.com";

	private HttpConnection httpConn = HttpConnection.getInstance();

	private Context mContext;

	private String respone;

	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;

		if (intent.getAction().equals(ACTION_EVAL)) {
			NotificationManagerCompat notiMgr = NotificationManagerCompat.from(context);
			notiMgr.cancel(0);

			String result = intent.getStringExtra("RESULT");
			switch (result) {
				case RESULT_GREAT:
					//API 호출
					sendData("/dsp-core-data/api/app-infos");
					Toast.makeText(context, "평가 완료 : " + result, Toast.LENGTH_SHORT).show();
					break;
				case RESULT_GOOD:
					sendData("/dsp-core-data/api/directories/demo");
					Toast.makeText(context, "평가 완료 : " + result, Toast.LENGTH_SHORT).show();
					break;
				case RESULT_NOTBAD:
					sendData("/dsp-core-data/api/account");
					Toast.makeText(context, "평가 완료 : " + result, Toast.LENGTH_SHORT).show();
					break;
				default:
					Toast.makeText(context, "평가 안됨 : " + result, Toast.LENGTH_SHORT).show();
					break;

			}
		}
	}

	/** 웹 서버로 데이터 전송 */
	private void sendData(final String pathUrl) {
		// 네트워크 통신하는 작업은 무조건 작업스레드를 생성해서 호출 해줄 것!!
		new Thread() {
			public void run() {
				// 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
				httpConn.requestWebServer(baseUrl+pathUrl, callback);
			}
		}.start();

	}

	private final Callback callback = new Callback() {
		@Override
		public void onFailure(Call call, IOException e) {
			Log.d(TAG, "콜백오류:"+e.getMessage());
		}
		@Override
		public void onResponse(Call call, Response response) throws IOException {
			String body = response.body().string();
			Log.d(TAG, "서버에서 응답한 Body:"+body);
		}
	};

}
