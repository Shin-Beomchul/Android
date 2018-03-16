package materialcalc.house.godbeom.com.retrofit2.utill;

import android.util.Log;

/**
 * Logger for api module <br><br>
 *
 * Created by jongsic.kim on 2017-04-06.
 */
public class ApiLog {

    private static final boolean DEBUG = true;
    private static final String TAG = "Retrofit module ";

    public static void d(String message) {
        if(DEBUG)
            Log.d(TAG, getMessage(message));
    }

    public static void v(String message) {
        if(DEBUG)
            Log.v(TAG, getMessage(message));
    }

    public static void i(String message) {
        if(DEBUG)
            Log.i(TAG, getMessage(message));
    }

    public static void w(String message) {
        if(DEBUG)
            Log.w(TAG, getMessage(message));
    }

    public static void e(String message) {
        if(DEBUG)
            Log.e(TAG, getMessage(message));
    }


    private static String getMessage(String message) {
        return message;
    }

}
