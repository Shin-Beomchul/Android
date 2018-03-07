package materialcalc.house.godbeom.com.materialcalc.sample.utill;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.net.ConnectivityManagerCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

/**
 * Device 속성 전반 관련 유틸 클래스 <br><br>
 * <p>
 * Created by jongsic.kim on 2017-04-10.
 */
public class DeviceUtil {

	public static String getAppVersion(Context context) {
		PackageInfo packageInfo = null;
		String versionName = "";
		try {
			packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			versionName = packageInfo.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return versionName;
	}

	public static boolean checkEnableVersion(int checkVersion) {
		int currentVersion = Build.VERSION.SDK_INT;
		return currentVersion >= checkVersion;
	}

	public static Point getScreenSize(Context context, boolean bToDPI) {
		Point size = new Point();

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		if (checkEnableVersion(Build.VERSION_CODES.HONEYCOMB)) {
			display.getSize(size);
		} else {
			size.x = display.getWidth();
			size.y = display.getHeight();
		}

		if (bToDPI) {
			size.x = (int) convertPixelToDp(size.x);
			size.y = (int) convertPixelToDp(size.y);
		}

		return size;
	}

	public static float convertDpToPixel(float dp) {
		return dp * Resources.getSystem().getDisplayMetrics().density;
	}

	public static double convertPixelToDp(float px) {
		return px / Resources.getSystem().getDisplayMetrics().density;
	}

	public static void showSoftInput(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
	}

	public static void hideSoftInput(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static boolean isNetworkConnected(Context context) {
		return ConnectivityManagerCompat.isActiveNetworkMetered((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
	}

	public static boolean isWiFiConnected(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		return networkInfo.isConnected();
	}



	/** if 1 Wifi
	 *     2 3G
	 *     -1 switching.. */
	public static int isNetWorkType(Context context) {

		if (IsWifiAvailable(context)) {
			return 1;
		} else if (Is3GAvailable(context)) {
			return 2;
		} else {
			return -1;
		}

	}

	public static boolean IsWifiAvailable(Context context) {
		ConnectivityManager m_NetConnectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean bConnect = false;
		try {
			if (m_NetConnectMgr == null) return false;
			NetworkInfo info = m_NetConnectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			bConnect = (info.isAvailable() && info.isConnected());
		} catch (Exception e) {
			return false;
		}
		return bConnect;
	}

	public static boolean Is3GAvailable(Context context) {
		ConnectivityManager m_NetConnectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean bConnect = false;
		try {
			if (m_NetConnectMgr == null) return false;
			NetworkInfo info = m_NetConnectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			bConnect = (info.isAvailable() && info.isConnected());
		} catch (Exception e) {
			return false;
		}
		return bConnect;
	}

	/*세로기기 확인 기본 설정인 기기를 구분*/
	public static boolean isDefaultVertical(Context context){

		DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();

		int width = dm.widthPixels;

		int height = dm.heightPixels;

		if(height>width){
			return true ; //Vertical
		}else{
			return false; //hor
		}
	}

 	public static int getScale(Context context, WebView webView){
		int PIC_WIDTH= webView.getRight()-webView.getLeft();
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int width = display.getWidth();
		Double val = new Double(width)/new Double(PIC_WIDTH);
		val = val * 100d;
		return val.intValue();
	}

}


