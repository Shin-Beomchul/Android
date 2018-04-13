package develop.godbeom.com.myapplication.util;

import android.os.Looper;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Administrator on 2018-04-12.
 */

public class Thread {


	public static boolean isOnMainThread() {
		return Looper.myLooper() == Looper.getMainLooper();
	}

	public static boolean isOnBackgroundThread() {
		return !isOnMainThread();
	}


	public static <T> Queue<T> createQueue(int size) {
		return new ArrayDeque<>(size);
	}
}
