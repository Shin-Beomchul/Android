package develop.godbeom.com.myapplication.loader.parallelism;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
/**
 * Created by BeomChul on 2018. 4. 14..
 */
public class MainThreadExecutor implements Executor {

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
	public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}