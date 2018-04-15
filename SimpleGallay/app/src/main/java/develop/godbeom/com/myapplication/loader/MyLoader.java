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

import develop.godbeom.com.myapplication.cache.CacheLruDisk;
import develop.godbeom.com.myapplication.cache.CacheLruMemory;
import develop.godbeom.com.myapplication.loader.parallelism.ThreadSupplier;
import develop.godbeom.com.myapplication.util.RetainFragment;

/**
 * Created by BeomChul.Shin on 2018-04-11.
 */

public class MyLoader {

	private static Function<Context, MyLoader> holderFunctional = new MyLoaderHolder();

	CacheLruMemory memoryCache = null;
	CacheLruDisk diskLRuCache = null;

	//initer
	private MyLoader(Context context) {
		memoryCache = initMemCache(context);
		diskLRuCache = CacheLruDisk.getInstance(context.getApplicationContext());
	}

	//LazySingleton with param
	private static class MyLoaderHolder implements Function<Context, MyLoader> {
		//Only One Race
		@Override
		public synchronized MyLoader apply(Context context) {
			class MyLoaderFactory implements Function<Context, MyLoader> {

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

	/**
	 * open interface
	 */
	public MyLoader load(String reqUrl, ImageView iv) {

		if (!isOnMainThread()) {
			throw new IllegalArgumentException("You must call this method on the main thread");
		}

		String hashedUrl = String.valueOf(reqUrl.hashCode());
		Bitmap memCache = memoryCache.getBitmap(hashedUrl);
		Bitmap diskCache = diskLRuCache.getBitmap(hashedUrl);
		if (memCache != null) {// Hit
			iv.setImageBitmap(memCache);
		} else if (diskCache != null) { //Hit
			iv.setImageBitmap(diskCache);
		} else {
			loadBitmap(reqUrl, bitmap -> {
				iv.setTag(hashedUrl);
				Bitmap resizeBitmap = resizeBitmap(bitmap);
				memoryCache.addBitmap(hashedUrl, resizeBitmap);
				diskLRuCache.addBitmap(hashedUrl, resizeBitmap);

				ThreadSupplier.getInstance().forMainThreadTasks().execute(() -> {

					if(isMatch(iv,hashedUrl));
					iv.setImageBitmap(resizeBitmap);
				});
				//bitmap = null; //Dalvik VM Heap Version old(Bitmap.recycle())
			});
		}
		return this;
	}


	/**리스트 뷰 재사용 판단.*/
	public boolean isMatch(ImageView iv , String hashUrl){

		// advanceImpl .리스트아이템 재사용 시점에 이미지뷰 enable 확인.

		return true;


	}


	/*resize*/
	private Bitmap resizeBitmap(Bitmap bitmap) {

		// advance = justOnly size

		// advance - 기준을 넘는 사진? 이미지열화 : set .

		int height = bitmap.getHeight();

		int width = bitmap.getWidth();

		Bitmap resized = null;
		while (height > 256) {
			resized = Bitmap.createScaledBitmap(bitmap, (width * 256) / height, 256, true);
			height = resized.getHeight();
			width = resized.getWidth();
		}
		return resized;

	}

	/*비트맵 로드*/
	private void loadBitmap(String url, onComplete<Bitmap> onComplete) {
		ThreadSupplier.getInstance().backGroundPool()
				.execute(() -> {
					try {
						URL urlcon = new URL(url);
						HttpURLConnection conn = (HttpURLConnection) urlcon.openConnection();
						conn.setDoInput(true);
						conn.setDoOutput(false);
						conn.connect();
						Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());

						onComplete.result(bitmap);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
	}


	/*메모리 캐시 초기화*/
	private CacheLruMemory initMemCache(Context context) {
		RetainFragment retainFragment = RetainFragment.findOrCreateRetainFragment(((Activity) context).getFragmentManager());
		CacheLruMemory mMemoryCache = retainFragment.mRetainedCache;
		if (mMemoryCache == null) {
			int deviceMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
			mMemoryCache = new CacheLruMemory(deviceMemory / 2);
			retainFragment.mRetainedCache = mMemoryCache;
			return retainFragment.mRetainedCache;
		} else {
			throw new NullPointerException("MemCache Init False!");
		}
	}

	private boolean hasImage(@NonNull ImageView view) {
		Drawable drawable = view.getDrawable();
		boolean hasImage = (drawable != null);

		if (hasImage && (drawable instanceof BitmapDrawable)) {
			hasImage = ((BitmapDrawable) drawable).getBitmap() != null;
		}

		return hasImage;
	}

	private static boolean isOnMainThread() {
		return Looper.myLooper() == Looper.getMainLooper();
	}


}
