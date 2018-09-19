package develop.godbeom.com.myapplication.loader.parallelism;

import android.os.Process;

import java.util.concurrent.ThreadFactory;
/**
 * Created by BeomChul on 2018. 4. 14..
 */
public class PriorityThreadFactory implements ThreadFactory {

    private final int mThreadPriority;

    public PriorityThreadFactory(int threadPriority) {
		mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Runnable wrapperRunnable = () -> {
			try {
				Process.setThreadPriority(mThreadPriority);
			} catch (Throwable t) {

			}
			runnable.run();
		};
        return new Thread(wrapperRunnable);
    }

}