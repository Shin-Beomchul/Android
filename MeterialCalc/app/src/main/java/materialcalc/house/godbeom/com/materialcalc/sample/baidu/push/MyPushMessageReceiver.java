package materialcalc.house.godbeom.com.materialcalc.sample.baidu.push;
/*
 * Push消息处理receiver。请编写您需要的回调函数， 一般来说： onBind是必须的，用来处理startWork返回值；
 *onMessage用来接收透传消息； onSetTags、onDelTags、onListTags是tag相关操作的回调；
 *onNotificationClicked在通知被点击时回调； onUnbind是stopWork接口的返回值回调
 * 返回值中的errorCode，解释如下：
 *0 - Success
 *10001 - Network Problem
 *10101  Integrate Check Error
 *30600 - Internal Server Error
 *30601 - Method Not Allowed
 *30602 - Request Params Not Valid
 *30603 - Authentication Failed
 *30604 - Quota Use Up Payment Required
 *30605 -Data Required Not Found
 *30606 - Request Time Expires Timeout
 *30607 - Channel Token Timeout
 *30608 - Bind Relation Not Found
 *30609 - Bind Number Too Many
 * 当您遇到以上返回错误时，如果解释不了您的问题，请用同一请求的返回值requestId和errorCode联系我们追查问题。
 *
 */

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MyPushMessageReceiver extends PushMessageReceiver {


	/**
	 * TAG to Log
	 */
	public static final String TAG = MyPushMessageReceiver.class
			.getSimpleName();

	/**
	 * onTokenRefresh 를 대신함.
	 * <p>
	 * PushManager.startWork를 호출하면 sdk가 Push합니다.
	 * 서버가 바인딩 요청을 시작하면이 프로세스는 비동기 적입니다.
	 * 바인딩 요청의 결과는 onBind를 통해 반환됩니다. 유니 캐스트 푸시를 사용해야하는 경우 여기에서 채널을 가져와야합니다.
	 * ID와 사용자 ID를 응용 프로그램 서버에 업로드 한 다음 채널 ID와 사용자 ID가있는 서버 인터페이스를 단일 전화 또는 사용자 푸시로 호출합니다.
	 *
	 * @param context   BroadcastReceiver的执行Context
	 * @param errorCode 绑定接口返回值，0 - 成功
	 * @param appid     应用id。errorCode非0时为null
	 * @param userId    应用user id。errorCode非0时为null
	 * @param channelId 应用channel id。errorCode非0时为null
	 * @param requestId 向服务端发起的请求id。在追查问题时有用；
	 * @return none
	 */
	@Override
	public void onBind(Context context, int errorCode, String appid,
					   String userId, String channelId, String requestId) {
		String responseString = "onBind errorCode=" + errorCode + " appid="
				+ appid + " userId=" + userId + " channelId=" + channelId
				+ " requestId=" + requestId;
		Log.d(TAG, responseString);

		if (errorCode == 0) {
			//  성공
			sendDeviceToken(context,channelId);
			Log.d(TAG, "透传消息");
		}
		// Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
		updateContent(context, responseString);
	}

	private static final String BUILD_TYPE = "BAIDU";
	private void sendDeviceToken(Context context, String token) {
//		Toast.makeText(context, " 바인딩 성공 "+token, Toast.LENGTH_SHORT).show();
		Log.i("Baidu", "바인딩 성공 " + token);
	}







	/**
	 * 투명 메시지.
	 * <p>
	 * 통과 메시지를 수신하는 기능입니다.
	 * DenpleGCMListenerService 를 대신함.
	 *
	 * @param context             上下文
	 * @param message             推送的消息
	 * @param customContentString 自定义内容,为空或者json字符串
	 *
	 *        customContentString 투명 메시지는 커스텀 컨텐츠를 지원하지 않음 :
	 *        투명한 메시지는 추가 필드 (customContentString)를 지원하지 않으며, Android 알림 만 추가 필드를 지원합니다.  : http://push.baidu.com/issue/view/1377
	 */
	@Override
	public void onMessage(Context context, String message, String customContentString) {
		String messageString = "透传消息 onMessage=\"" + message + "\" customContentString=" + customContentString;
 		updateContent(context, messageString);
	}



	/**
	 * 도착 알림 기능을 수신합니다.
	 *
	 * @param context             上下文
	 * @param title               推送的通知的标题
	 * @param description         推送的通知的描述
	 * @param customContentString 自定义内容，为空或者json字符串
	 */

	@Override
	public void onNotificationArrived(Context context, String title,
									  String description, String customContentString) {

		String notifyString = "通知到达 onNotificationArrived  title=\"" + title
				+ "\" description=\"" + description + "\" customContent="
				+ customContentString;
		Log.d(TAG, notifyString);

		// 自定义内容获取方式，mykey和myvalue对应通知推送时自定义内容中设置的键和值
		if (!TextUtils.isEmpty(customContentString)) {
			JSONObject customJson = null;
			try {
				customJson = new JSONObject(customContentString);
				String myvalue = null;
				if (!customJson.isNull("mykey")) {
					myvalue = customJson.getString("mykey");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
		// 你可以參考 onNotificationClicked中的提示从自定义内容获取具体值
		updateContent(context, notifyString);
	}

	/**
	 * 接收通知点击的函数。
	 *
	 * @param context             上下文
	 * @param title               推送的通知的标题
	 * @param description         推送的通知的描述
	 * @param customContentString 自定义内容，为空或者json字符串
	 */
	@Override
	public void onNotificationClicked(Context context, String title,
									  String description, String customContentString) {
		String notifyString = "通知点击 onNotificationClicked title=\"" + title + "\" description=\""
				+ description + "\" customContent=" + customContentString;
		Log.d(TAG, notifyString);

		// 自定义内容获取方式，mykey和myvalue对应通知推送时自定义内容中设置的键和值
		if (!TextUtils.isEmpty(customContentString)) {
			JSONObject customJson = null;
			try {
				customJson = new JSONObject(customContentString);
				String myvalue = null;
				if (!customJson.isNull("mykey")) {
					myvalue = customJson.getString("mykey");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
		updateContent(context, notifyString);
	}


	/*   * setTags() 的回调函数。
	   *
	   * @param context
	   *            上下文
	   * @param errorCode
	   *            错误码。0表示某些tag已经设置成功；非0表示所有tag的设置均失败。
	   * @param successTags
	   *            设置成功的tag
	   * @param failTags
	   *            设置失败的tag
	   * @param requestId
	   *            分配给对云推送的请求的id
	*/
	@Override
	public void onSetTags(Context context, int errorCode,
						  List<String> sucessTags, List<String> failTags, String requestId) {
		String responseString = "onSetTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.d(TAG, responseString);

		// Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
		updateContent(context, responseString);
	}

	/*
	 * delTags() 的回调函数。
	 *
	 * @param context
	 *            上下文
	 * @param errorCode
	 *            错误码。0表示某些tag已经删除成功；非0表示所有tag均删除失败。
	 * @param successTags
	 *            成功删除的tag
	 * @param failTags
	 *            删除失败的tag
	 * @param requestId
	 *            分配给对云推送的请求的id
	*/
	@Override
	public void onDelTags(Context context, int errorCode,
						  List<String> sucessTags, List<String> failTags, String requestId) {
		String responseString = "onDelTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.d(TAG, responseString);

		// Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
		updateContent(context, responseString);
	}

	/**
	 * listTags() 的回调函数。
	 *
	 * @param context   上下文
	 * @param errorCode 错误码。0表示列举tag成功；非0表示失败。
	 * @param tags      当前应用设置的所有tag。
	 * @param requestId 分配给对云推送的请求的id
	 */
	@Override
	public void onListTags(Context context, int errorCode, List<String> tags,
						   String requestId) {
		String responseString = "onListTags errorCode=" + errorCode + " tags="
				+ tags;
		Log.d(TAG, responseString);

		// Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
		updateContent(context, responseString);
	}

	/**
	 * PushManager.stopWork() 的回调函数。
	 *
	 * @param context   上下文
	 * @param errorCode 错误码。0表示从云推送解绑定成功；非0表示失败。
	 * @param requestId 分配给对云推送的请求的id
	 */
	@Override
	public void onUnbind(Context context, int errorCode, String requestId) {
		String responseString = "onUnbind errorCode=" + errorCode
				+ " requestId = " + requestId;
		Log.d(TAG, responseString);

		if (errorCode == 0) {
			// 解绑定成功
			Log.d(TAG, "解绑成功");
		}
		// Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
		updateContent(context, responseString);
	}

	private void updateContent(Context context, String content) {
		Log.d(TAG, "updateContent");
	 	String logText = "" + Utils.logStringCache;

		if (!logText.equals("")) {
			logText += "\n";
		}

		SimpleDateFormat sDateFormat = new SimpleDateFormat("HH-mm-ss");
		logText += sDateFormat.format(new Date()) + ": ";
		logText += content;

		Utils.logStringCache = logText;

		Intent intent = new Intent();
		intent.setClass(context.getApplicationContext(), PushDemoActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.getApplicationContext().startActivity(intent);
	}

}
