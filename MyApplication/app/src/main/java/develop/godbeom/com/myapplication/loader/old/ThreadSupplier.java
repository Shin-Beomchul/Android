package develop.godbeom.com.myapplication.loader.old;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
* Singleton class for default executor supplier
*/
public class ThreadSupplier {
	/*
	* Number of cores to decide the number of threads
    */
	public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

	/*
	* thread pool executor for background tasks
	*/
	private   ThreadPoolExecutor mForBackgroundTasks = null;
	/*
	* thread pool executor for light weight background tasks
	*/
	/*
	* thread pool executor for main thread tasks
	*/
	private   Executor mMainThreadExecutor = null;
    /*
    * an instance of DefaultExecutorSupplier
    */


	public static ThreadSupplier getInstance() {
		return ThreadSupplier.LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final ThreadSupplier INSTANCE = new ThreadSupplier();
	}

	/*
	* constructor for  DefaultExecutorSupplier
	*/
	public ThreadSupplier() {


		ThreadFactory backgroundPriorityThreadFactory = new
				PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);
		mForBackgroundTasks = new ThreadPoolExecutor(
				2,
				2,
				60L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(),
				backgroundPriorityThreadFactory
		);
		mMainThreadExecutor = new MainThreadExecutor();
	}
	/*
	* returns the thread pool executor for background task
	*/
	public ThreadPoolExecutor backGroundPool() {
		return mForBackgroundTasks;
	}

	/*
	* returns the thread pool executor for main thread task
	*/
	public Executor forMainThreadTasks() {
		return mMainThreadExecutor;
	}
}