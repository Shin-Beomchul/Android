package develop.godbeom.com.myapplication.cache;

import android.graphics.Color;

public enum LoadedFrom {
	MEMORY(Color.GREEN), DISK(Color.YELLOW), NETWORK(Color.RED), LOCAL(Color.BLUE);

    final int debugColor;

    private LoadedFrom(final int debugColor) {
        this.debugColor = debugColor;
    }
}