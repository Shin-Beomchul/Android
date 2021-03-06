package materialcalc.house.godbeom.com.materialcalc.sample.remoteconfig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import materialcalc.house.godbeom.com.materialcalc.BuildConfig;
import materialcalc.house.godbeom.com.materialcalc.R;

public class ActRemoteConfig extends AppCompatActivity {


	private static final String TAG = "MainActivity";

	// Remote Config keys
	private static final String LOADING_PHRASE_CONFIG_KEY = "loading_phrase";
	private static final String WELCOME_MESSAGE_KEY = "welcome_message";
	private static final String WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps";
	private static final String AlertURL_KEY = "AlertURL";
	private static final String showAlert_KEY = "showAlert";


	private FirebaseRemoteConfig mFirebaseRemoteConfig;
	private TextView mWelcomeTextView;
	private TextView welcomeCaps;
	private TextView loadPhrase;
	private TextView AlertUrl;
	private TextView showAlert;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_remote_config);


		mWelcomeTextView = findViewById(R.id.welcomeTextView);
		welcomeCaps = findViewById(R.id.welcomeCaps);
		loadPhrase = findViewById(R.id.loadPhrase);
		AlertUrl = findViewById(R.id.AlertUrl);
		showAlert = findViewById(R.id.showAlert);

		Button fetchButton = findViewById(R.id.fetchButton);
		fetchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fetchWelcome();
			}
		});



		// Get Remote Config instance.
		// [START get_remote_config_instance]
		mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
		// [END get_remote_config_instance]

		// Create a Remote Config Setting to enable developer mode, which you can use to increase
		// the number of fetches available per hour during development. See Best Practices in the
		// README for more information.
		// [START enable_dev_mode]
		FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
				.setDeveloperModeEnabled(BuildConfig.DEBUG)
				.build();
		mFirebaseRemoteConfig.setConfigSettings(configSettings);
		// [END enable_dev_mode]

		// Set default Remote Config parameter values. An app uses the in-app default values, and
		// when you need to adjust those defaults, you set an updated value for only the values you
		// want to change in the Firebase console. See Best Practices in the README for more
		// information.
		// [START set_default_values]
		mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults); // Fetch 전 값.
		// [END set_default_values]


		mWelcomeTextView.setText(mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY));
		welcomeCaps.setText(mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_CAPS_KEY));
		loadPhrase.setText(mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY));
		showAlert.setText(mFirebaseRemoteConfig.getBoolean(showAlert_KEY)+"");
		AlertUrl.setText(mFirebaseRemoteConfig.getString(AlertURL_KEY));


		loadPhrase.setText(mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY));

//		fetchWelcome();
	}




	/**
	 * Fetch a welcome message from the Remote Config service, and then activate it.
	 */
	private void fetchWelcome() {


		long cacheExpiration = 3600; // 1 hour in seconds.
		// If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
		// retrieve values from the service.
		if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
			cacheExpiration = 0;
		}

		// [START fetch_config_with_callback]
		// cacheExpirationSeconds is set to cacheExpiration here, indicating the next fetch request
		// will use fetch data from the Remote Config service, rather than cached parameter values,
		// if cached parameter values are more than cacheExpiration seconds old.
		// See Best Practices in the README for more information.
		mFirebaseRemoteConfig.fetch(cacheExpiration)
				.addOnCompleteListener(this, new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						if (task.isSuccessful()) {
							Toast.makeText(ActRemoteConfig.this, "Fetch Succeeded",
									Toast.LENGTH_SHORT).show();

							// After config data is successfully fetched, it must be activated before newly fetched
							// values are returned.
							mFirebaseRemoteConfig.activateFetched(); //  해당 코드 주석 처리 후 설정창에서 제거 할 경우 반영 안됨.

							mWelcomeTextView.setText(mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY));
							welcomeCaps.setText(mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_CAPS_KEY));
							loadPhrase.setText(mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY));
							showAlert.setText(mFirebaseRemoteConfig.getBoolean(showAlert_KEY)+"");
							AlertUrl.setText(mFirebaseRemoteConfig.getString(AlertURL_KEY));



						} else {
							Toast.makeText(ActRemoteConfig.this, "Fetch Failed",
									Toast.LENGTH_SHORT).show();
						}
						displayWelcomeMessage();
					}
				});
		// [END fetch_config_with_callback]
	}

	/**
	 * Display a welcome message in all caps if welcome_message_caps is set to true. Otherwise,
	 * display a welcome message as fetched from welcome_message.
	 */
	// [START display_welcome_message]
	private void displayWelcomeMessage() {
		// [START get_config_values]
		String welcomeMessage = mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY);
		// [END get_config_values]
		if (mFirebaseRemoteConfig.getBoolean(WELCOME_MESSAGE_CAPS_KEY)) {
			mWelcomeTextView.setAllCaps(true);
		} else {
			mWelcomeTextView.setAllCaps(false);
		}
		mWelcomeTextView.setText(welcomeMessage);
	}
	// [END display_welcome_message]

}
