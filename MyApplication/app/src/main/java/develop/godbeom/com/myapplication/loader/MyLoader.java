package develop.godbeom.com.myapplication.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import develop.godbeom.com.myapplication.cache.mMemoryLruCache;
import develop.godbeom.com.myapplication.loader.old.ThreadSupplier;

/**
 * Created by Administrator on 2018-04-11.
 */

public class MyLoader {

	List<Future<Bitmap>> resultList = new ArrayList<>();
	//	MemoryCache memoryCache = new MemoryCache();
	mMemoryLruCache memoryCache = null;


	private MyLoader() {
		int deviceMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		memoryCache = new mMemoryLruCache(deviceMemory / 8);
	}

	public static MyLoader getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final MyLoader INSTANCE = new MyLoader();
	}

	public MyLoader load(String url, ImageView iv) {

		if (!isOnMainThread()) {
			throw new IllegalArgumentException("You must call this method on the main thread");
		}

		Bitmap memCache = memoryCache.getBitmapFromMemCache(url);
		if (memCache != null) {
			iv.setImageBitmap(memCache);
		} else {
			loadBitmap(url, bitmap -> {
				memoryCache.addBitmapToMemoryCache(url, bitmap);
				iv.setImageBitmap(bitmap);
				bitmap = null; //Dalvik VM Heap Version old(Bitmap.recycle())
			});
		}
		return this;
	}

	public MyLoader loadBitmap(String url, onComplete onComplete) {
		ThreadSupplier.getInstance().backGroundPool()
				.execute(() -> {
					try {
						URL urlcon = new URL(url);
						HttpURLConnection conn = (HttpURLConnection) urlcon.openConnection();
						conn.setDoInput(true);
						conn.setDoOutput(false);
						conn.connect();
						Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
						public static byte[] toByteArray(InputStream input) throws IOException {
							ByteArrayOutputStream output = new ByteArrayOutputStream();
							copy((InputStream)input, (OutputStream)output);
							return output.toByteArray();
						}


						onComplete.bitMap(bitmap);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
		return this;
	}

	public void into(ImageView iv) {

	}


	public static Bitmap decodeByteArray(final byte[] data, final BitmapFactory.Options options) {
		try {
			return BitmapFactory.decodeByteArray(data, 0, data.length, options);
		} catch (final OutOfMemoryError e) {
			return null;
		}
	}

	public boolean isMemCacheHit(String url) {
		return false;
	}

	public Bitmap getBitmapMemCacheed() {
		return null;
	}


	public boolean isDiskCacheHit(String url) {
		return false;
	}

	public boolean getBitmapDiskCached() {
		return false;
	}


	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy((InputStream)input, (OutputStream)output);
		return output.toByteArray();
	}

	public static int copy(InputStream input, OutputStream output) throws IOException {
		long count = copyLarge(input, output);
		return count > 2147483647L?-1:(int)count;
	}

	public static long copyLarge(InputStream input, OutputStream output) throws IOException {
		return copyLarge(input, output, new byte[4096]);
	}

	public static boolean isOnMainThread() {
		return Looper.myLooper() == Looper.getMainLooper();
	}

	public static boolean isOnBackgroundThread() {
		return !isOnMainThread();
	}

}
