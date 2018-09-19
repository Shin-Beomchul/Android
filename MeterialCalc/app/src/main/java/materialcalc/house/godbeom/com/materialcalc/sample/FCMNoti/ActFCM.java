package materialcalc.house.godbeom.com.materialcalc.sample.FCMNoti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import materialcalc.house.godbeom.com.materialcalc.R;

/**
 * Created by user on 2018-03-19.
 */

public class ActFCM extends AppCompatActivity {
	private static final String TAG = "ActFCM";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_fcm);

		String json = "\"data\": {\n" +
				"\t\t\"notification\": {\n" +
				"\t\t\t\"title\": \"demo.\",\n" +
				"\t\t\t\"body\": \"하이브리드 푸시\",\n" +
				"\t\t\t\"icon\": \"myicon\"\n" +
				"\t\t},\n" +
				"\t\t\"requireLoginPage\": false,\n" +
				"\t\t\"landingType\": \"1\",\n" +
				"\t\t\"targetUrl\": \"http://m.demo.com/dsp/posters/view/304\",\n" +
				"\t\t\"imageUrl\": \"http://imageshack.com/a/img923/9128/JXk3iT.png\"\n" +
				"\t}";

		JSONObject jsonObject;
		Bundle bundle = new Bundle();
		try {
			jsonObject = new JSONObject(json);
			Iterator iter = jsonObject.keys();
			while(iter.hasNext()){
				String key = (String)iter.next();
				String value = jsonObject.getString(key);
				bundle.putString(key,value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Button btnNoti = (Button) findViewById(R.id.btnNoti);
		btnNoti.setOnClickListener(new View.OnClickListener() {
									   @Override
									   public void onClick(View view) {
										   SendNotification.sendPushNotification(ActFCM.this, bundle, json);
									   }
								   });
	}
}
