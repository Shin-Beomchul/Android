package develop.godbeom.com.myapplication.cache;

import android.os.AsyncTask;

import com.jakewharton.DiskLruCache;

import java.io.File;

/**
 * Created by Administrator on 2018-04-13.
 */

public class mDiskCache {
	 DiskLruCache  mDiskLruCache;
	private final Object mDiskCacheLock = new Object();
	private boolean mDiskCacheStarting = true;
	private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB
	private static final String DISK_CACHE_SUBDIR = "thumbnails";

	class InitDiskCacheTask extends AsyncTask<File, Void, Void> {
		@Override
		protected Void doInBackground(File... params) {
			synchronized (mDiskCacheLock) {
				File cacheDir = params[0];
				mDiskLruCache = DiskLruCache.open(cacheDir, DISK_CACHE_SIZE);
				mDiskCacheStarting = false; // Finished initialization
				mDiskCacheLock.notifyAll(); // Wake any waiting threads
			}
			return null;
		}
	}
}
