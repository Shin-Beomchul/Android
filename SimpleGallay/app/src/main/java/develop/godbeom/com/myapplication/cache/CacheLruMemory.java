package develop.godbeom.com.myapplication.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by BeomChul on 2018. 4. 14..
 */

public class CacheLruMemory extends LruCache<String,Bitmap> implements BaseCache {

    public CacheLruMemory(int maxSize) {
        super(maxSize/8);
    }


    @Override
    protected int sizeOf(String key, Bitmap bitmap) {
        return bitmap.getByteCount() / 1024;
    }

    @Override
    public void addBitmap(String key, Bitmap bitmap) {
        if (getBitmap(key) == null) {
            put(key, bitmap);
        }
    }

    @Override
    public Bitmap getBitmap(String key) {
        return get(key);
    }

    @Override
    public void clearCache() {

    }
}
