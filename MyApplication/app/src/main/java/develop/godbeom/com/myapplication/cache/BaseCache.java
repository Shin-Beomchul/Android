package develop.godbeom.com.myapplication.cache;

import android.graphics.Bitmap;

/**
 * Created by god on 2018. 4. 14..
 */

public interface BaseCache {


     void addBitmap(String key, Bitmap bitmap);
     Bitmap getBitmap(final String key);
     void clearCache();


}
