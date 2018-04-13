package develop.godbeom.com.myapplication.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Function;

import develop.godbeom.com.myapplication.cache.DiskLruImageCache;
import develop.godbeom.com.myapplication.cache.MemoryLruCache;
import develop.godbeom.com.myapplication.loader.old.ThreadSupplier;

/**
 * Created by Administrator on 2018-04-11.
 */

public class MyLoader {

	private static Function<Context,MyLoader> holderFunctional = new MyLoaderHolder();

	MemoryLruCache memoryCache = null;
	DiskLruImageCache diskLRuCache = null;


	//initer
	private MyLoader(Context context) {
		int deviceMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		memoryCache = new MemoryLruCache(deviceMemory/2);
		diskLRuCache =  DiskLruImageCache.getInstance(context);


	}


	//LazySingleton with param
	private static class MyLoaderHolder implements Function<Context ,MyLoader>{

		//Only One Race
		@Override
		public synchronized  MyLoader apply(Context context) {
			class MyLoaderFactory implements Function<Context, MyLoader>{

				private final MyLoader INSTANCE = new MyLoader(context);

				@Override
				public MyLoader apply(Context context) {
					return INSTANCE;
				}
			}
			if (!MyLoaderFactory.class.isInstance(holderFunctional)) {
				holderFunctional = new MyLoaderFactory();
			}

			return holderFunctional.apply(context);
		}
	}

	public static MyLoader getInstance(Context context) {
		return holderFunctional.apply(context.getApplicationContext());
	}

	public MyLoader load(String url, ImageView iv) {

		if (!isOnMainThread()) {
			throw new IllegalArgumentException("You must call this method on the main thread");
		}


		Bitmap memCache = memoryCache.getBitmapFromMemCache(url);
		Bitmap diskCache = diskLRuCache.getBitmap(String.valueOf(url.hashCode()));

		if (memCache != null) {
			iv.setImageBitmap(memCache);
		}else if(diskCache!=null){
			iv.setImageBitmap(diskCache);
		}else {
			loadBitmap(url, bitmap -> {
				memoryCache.addBitmapToMemoryCache(url, bitmap);
				diskLRuCache.put(String.valueOf(url.hashCode()),bitmap);
				iv.setImageBitmap(bitmap);
				//bitmap = null; //Dalvik VM Heap Version old(Bitmap.recycle())
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

						onComplete.bitMap(bitmap);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
		return this;
	}

	public void into(ImageView iv) {

	}

	private boolean hasImage(@NonNull ImageView view) {
		Drawable drawable = view.getDrawable();
		boolean hasImage = (drawable != null);

		if (hasImage && (drawable instanceof BitmapDrawable)) {
			hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
		}

		return hasImage;
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


	/*public static byte[] toByteArray(InputStream input) throws IOException {
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
	}*/

	public static boolean isOnMainThread() {
		return Looper.myLooper() == Looper.getMainLooper();
	}

	public static boolean isOnBackgroundThread() {
		return !isOnMainThread();
	}

}
