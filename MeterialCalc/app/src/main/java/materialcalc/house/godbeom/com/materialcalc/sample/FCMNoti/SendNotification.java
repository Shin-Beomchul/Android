package materialcalc.house.godbeom.com.materialcalc.sample.FCMNoti;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;

import materialcalc.house.godbeom.com.materialcalc.R;

/**
 * Created by user on 2018-03-19.
 */
public class SendNotification {
	public static final String PUSH_APP = "appName";
	public static final String PUSH_TITLE = "title";
	public static final String PUSH_SENDTYPE = "sendType";
	public static final String PUSH_TEXT = "text";
	public static final String PUSH_DATA = "data";

	public static final String PUSH_REQ_LOGIN = "requireLoginPage"; //로그인 필요한 페이지인지 flag

	private static final String PUSH_LANDING_TYPE = "landingType";
	private static final String PUSH_URL_TARGET = "targetUrl";
	private static final String PUSH_URL_IMAGE = "imageUrl";


	public static final String SENDTYPE_HYBRID = "hybrid"; // 덴플 2.0



	public static void sendPushNotification(Activity mContext, Bundle bundle, String data) {

		String jsonData = "";
		String title = "";
		String text = "";
		String sendType = "";
		boolean reqLogin;
		try {
			title = URLDecoder.decode(bundle.getString(PUSH_TITLE), "UTF-8");
			text = URLDecoder.decode(bundle.getString(PUSH_TEXT), "UTF-8");
			jsonData = URLDecoder.decode(bundle.getString(PUSH_DATA), "UTF-8");
			sendType = URLDecoder.decode(bundle.getString(PUSH_SENDTYPE), "UTF-8");
			reqLogin = Boolean.parseBoolean(URLDecoder.decode(bundle.getString(PUSH_REQ_LOGIN), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			title = "demo";
			text = "Notice from demo";
			sendType = SENDTYPE_HYBRID;
			reqLogin = false;
			jsonData = "";
			e.printStackTrace();
		} catch (NullPointerException e) {
			title = "demo";
			text = "Notice from demo";
			sendType = SENDTYPE_HYBRID;
			reqLogin = false;
			jsonData = "";
			e.printStackTrace();
		}
		int random = new Random().nextInt(100);
		Intent intent = new Intent(mContext, ActFCM.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.putExtra("BUNDLE", bundle);
		intent.putExtra("STRING", data);
		PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);


		NotificationManagerCompat notiMgr = NotificationManagerCompat.from(mContext);
		notiMgr.notify(0, createPushNotification(mContext, pendingIntent, title, text, random));


	}

	private static Notification createPushNotification(Activity mContext, PendingIntent pIntent, String title , String message, int random) {
		Intent intenta = new Intent(mContext, BCCallAPI.class);
		intenta.setAction(BCCallAPI.ACTION_EVAL);
		intenta.putExtra("RESULT", BCCallAPI.RESULT_GREAT);
		intenta.putExtra("NUM", random);
		PendingIntent intent1 = PendingIntent.getBroadcast(mContext, 0, intenta, 0);

		Intent intentb = new Intent(mContext, BCCallAPI.class);
		intentb.setAction(BCCallAPI.ACTION_EVAL);
		intentb.putExtra("RESULT", BCCallAPI.RESULT_GOOD);
		intentb.putExtra("NUM", random);
		PendingIntent intent2 = PendingIntent.getBroadcast(mContext, 0, intentb, 1);


		Intent intentc = new Intent(mContext, BCCallAPI.class);
		intentc.setAction(BCCallAPI.ACTION_EVAL);
		intentc.putExtra("RESULT", BCCallAPI.RESULT_NOTBAD);
		intentc.putExtra("NUM", random);
		PendingIntent intent3 = PendingIntent.getBroadcast(mContext, 0, intentc, 2);


		NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setContentTitle(title)
				.setContentText(message)
				.setAutoCancel(true)
				.setTicker(message)
				.setPriority(Notification.PRIORITY_HIGH)
				.setVisibility(Notification.VISIBILITY_PUBLIC)
				.setDefaults(Notification.DEFAULT_ALL)
				.setContentIntent(pIntent)
				.addAction(0, "매우만족", intent1) // #0
				.addAction(0, "약간만족", intent2)  // #1
				.addAction(0, "보통", intent3)     // #2
				.setStyle(new NotificationCompat.BigTextStyle().bigText(message));


		return builder.build();
	}


}
