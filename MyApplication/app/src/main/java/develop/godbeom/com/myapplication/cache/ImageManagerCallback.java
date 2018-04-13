package develop.godbeom.com.myapplication.cache;

import android.graphics.Bitmap;

public interface ImageManagerCallback {

    void onBitmapLoaded(final Bitmap bitmap, final LoadedFrom source);

    void onLoadFailed(final LoadedFrom source, Exception e);
}
