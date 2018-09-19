package materialcalc.house.godbeom.com.materialcalc.sample.baidu.push;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

public class CloudMessagingIntentService extends IntentService {


	public CloudMessagingIntentService() {
		super("CloudMessagingIntentService");
	}



	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY,
				Utils.getMetaValue(getApplicationContext(), "api_key"));
	 		  //->MyPushMessageRecever.onBind()


	}
}
