package develop.godbeom.com.myapplication.loader;

import android.app.Activity;
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

import develop.godbeom.com.myapplication.cache.CacheDiskLru;
import develop.godbeom.com.myapplication.cache.DiskLruImageCache;
import develop.godbeom.com.myapplication.cache.MemoryLruCache;
import develop.godbeom.com.myapplication.loader.old.ThreadSupplier;
import develop.godbeom.com.myapplication.util.RetainFragment;

/**
 * Created by Administrator on 2018-04-11.
 */

public class MyLoader {

	private static Function<Context,MyLoader> holderFunctional = new MyLoaderHolder();

	MemoryLruCache memoryCache = null;
	CacheDiskLru diskLRuCache = null;




	//initer
	private MyLoader(Context context) {
//		int deviceMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//		memoryCache = new MemoryLruCache(deviceMemory);
		memoryCache = initMemCache(context);
		diskLRuCache =  CacheDiskLru.getInstance(context.getApplicationContext());


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
		return holderFunctional.apply(context);
	}

	public MyLoader load(String url, ImageView iv) {

		if (!isOnMainThread()) {
			throw new IllegalArgumentException("You must call this method on the main thread");
		}


		String hashedUrl = String.valueOf(url.hashCode());

		Bitmap memCache = memoryCache.getBitmapFromMemCache(hashedUrl);
		Bitmap diskCache = diskLRuCache.getBitmap(hashedUrl);

		if (memCache != null) {
			iv.setImageBitmap(memCache);
		}
		else if(diskCache != null){
			iv.setImageBitmap(diskCache);
		}
		else {
			loadBitmap(url, bitmap -> {

				Bitmap resizeBitmap =resizeBitmap(bitmap);

				memoryCache.addBitmapToMemoryCache(hashedUrl, resizeBitmap);
				diskLRuCache.addBitmap(hashedUrl,resizeBitmap);

				ThreadSupplier.getInstance().forMainThreadTasks().execute(() -> iv.setImageBitmap(resizeBitmap));
				//bitmap = null; //Dalvik VM Heap Version old(Bitmap.recycle())
			});
		}
		return this;
	}

	private Bitmap resizeBitmap(Bitmap bitmap){
		int height = bitmap.getHeight();

		int width = bitmap.getWidth();

		Bitmap resized = null;

		while (height > 118) {


			resized = Bitmap.createScaledBitmap(bitmap, (width * 118) / height, 118, true);

			height = resized.getHeight();

			width = resized.getWidth();

		}

		return resized;

	}

	private void loadBitmap(String url, onComplete onComplete) {
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

	}

	public void into(ImageView iv) {

	}


	public MemoryLruCache initMemCache(Context context){
		RetainFragment retainFragment = RetainFragment.findOrCreateRetainFragment(((Activity)context).getFragmentManager());
		MemoryLruCache mMemoryCache = retainFragment.mRetainedCache;
		if (mMemoryCache == null) {
			int deviceMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
			mMemoryCache = new MemoryLruCache(deviceMemory/2);
			retainFragment.mRetainedCache = mMemoryCache;
			return retainFragment.mRetainedCache;
		}else{
			throw new NullPointerException("MemCache Init False!");
		}
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
