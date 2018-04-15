package develop.godbeom.com.myapplication.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;
/**
 * Created by BeomChul on 2018. 4. 14..
 */
public class Utils {
    public static final int IO_BUFFER_SIZE = 8 * 1024;
    private static final String TAG = "Utils";

    @SuppressLint("NewApi")
    private static class GingerbreadOrHigherUtils {
        public static boolean isExternalStorageRemovable() {
            return Environment.isExternalStorageRemovable();
        }
    }



    public static File getDiskCacheDir(final Context context, final String uniqueName) {
        final boolean externalCacheAvailable = (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) ||
                                               !Utils.isExternalStorageRemovable();
        String cachePath = null;

        if (externalCacheAvailable && Utils.getExternalCacheDir(context) != null) {
            cachePath = Utils.getExternalCacheDir(context).getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }


    @SuppressLint("ObsoleteSdkInt")
    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            GingerbreadOrHigherUtils.isExternalStorageRemovable();
        }
        return true;
    }

    public static File getExternalCacheDir(final Context context) {
        if (hasExternalCacheDir()) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO;
    }
}
