package develop.godbeom.com.myapplication.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by BeomChul on 2018. 4. 14..
 */

public class CacheLruDisk implements BaseCache{


    private static final String TAG = "DiskLruImageCache";
    private DiskLruCache diskCache;
    private static CacheLruDisk instance;
    private static Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
    private static int compressQuality = 70;
    private static final int APP_VERSION = 1;
    private static final int VALUE_COUNT = 1;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB


    private CacheLruDisk(final Context context) {
        this(context, compressFormat, compressQuality);
    }

    //initer
    private CacheLruDisk(final Context context, final Bitmap.CompressFormat format, final int quality) {
        try {
            final File diskCacheDir = develop.godbeom.com.myapplication.util.Utils.getDiskCacheDir(context, CacheLruDisk.TAG);
            diskCache = DiskLruCache.open(diskCacheDir, APP_VERSION, VALUE_COUNT, CacheLruDisk.DISK_CACHE_SIZE);
            compressFormat = format;
            compressQuality = quality;
        } catch (final IOException e) {
            Log.e(TAG, "Failed to initialize DiskLruImageCache", e);
        }
    }

    public static CacheLruDisk getInstance(final Context context) {
        if (instance == null) {
            instance = new CacheLruDisk(context);
        }
        return instance;
    }

    public static boolean isInitialized() {
        return instance != null;
    }


    @Override
    public void addBitmap(String key,Bitmap bitmap) {
    /// Disk LRU imple

        if (diskCache == null) {
            return;
        }

        DiskLruCache.Editor editor = null;
        try {
            editor = diskCache.edit(key);
            if (editor == null)
                return;

            if (writeBitmapToFile(bitmap, editor)) {
                editor.commit();

            } else {
                editor.abort();
                Log.e(TAG, "ERROR on: image put on disk cache " + key);
            }
        } catch (final IOException e) {
            Log.e(TAG, "ERROR on: image put on disk cache " + key, e);
            try {
                if (editor != null) {
                    editor.abort();
                }
            } catch (final IOException ignored) {} catch (final IllegalStateException ignored) {}
        }

    }

    @Override
    public Bitmap getBitmap(String key) {
        if (diskCache == null) {
            return null;
        }

        Bitmap bitmap = null;
        DiskLruCache.Snapshot snapshot = null;
        try {

            snapshot = diskCache.get(key);
            if (snapshot == null)
                return null;
            final InputStream in = snapshot.getInputStream(0);
            if (in != null) {
                final BufferedInputStream buffIn = new BufferedInputStream(in, develop.godbeom.com.myapplication.util.Utils.IO_BUFFER_SIZE);
                bitmap = decodeStream(buffIn);
            }
        } catch (final IOException e) {
            Log.e(TAG, "ERROR getBitmap", e);
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }

        return bitmap;
    }

    @Override
    public void clearCache() {
        if (diskCache == null) {
            return;
        }


        try {
            diskCache.delete();
        } catch (final IOException e) {
            Log.e(TAG, "", e);
        }
    }



    private boolean writeBitmapToFile(final Bitmap bitmap, final DiskLruCache.Editor editor) throws IOException {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(editor.newOutputStream(0), develop.godbeom.com.myapplication.util.Utils.IO_BUFFER_SIZE);
            return bitmap.compress(compressFormat, compressQuality, out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }



    public static Bitmap decodeStream(final InputStream stream) {
        try {
            return BitmapFactory.decodeStream(stream);
        } catch (final OutOfMemoryError e) {
            Log.e(TAG, "Out of memory in decodeStream()", e);
            return null;
        }
    }
}
