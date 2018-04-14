package develop.godbeom.com.myapplication.cache;

import android.graphics.Bitmap;

/**
 * Created by god on 2018. 4. 14..
 */

public class CacheMemoryLru implements BaseCache {

    @Override
    public void addBitmap(String url, Bitmap bitmap) {
      //memCache
    }

    @Override
    public Bitmap getBitmap(String key) {
        return null;
    }

    @Override
    public void clearCache() {

    }
}
