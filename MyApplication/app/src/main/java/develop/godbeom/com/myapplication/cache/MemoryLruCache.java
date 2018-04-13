package develop.godbeom.com.myapplication.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Administrator on 2018-04-13.
 */

public class MemoryLruCache extends LruCache<String,Bitmap> {
	public MemoryLruCache(int maxSize) {
		super(maxSize/8);
	}

	@Override
	protected int sizeOf(String key, Bitmap bitmap) {
		return bitmap.getByteCount() / 1024;
	}


	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			 put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemCache(String key) {
		return get(key);
	}

}



































/*
-       나머지 activity를 위한 메모리

		-       한번에 Screen에 보여지는 이미지 수

		-       Screen Size

		-       그 외 생략

		위의 것들은 개발자가 결정해야 하는 것이며 적합한 공식이 없다*/


























