package develop.godbeom.com.myapplication.util;

import java.util.HashMap;
/**
 * Created by BeomChul on 2018. 4. 14..
 */
public class TimeChecker {
	public static final int CAN_NOT_FIND_START_VALUE =-404;
	HashMap<String, Long> startSet = null;

	private TimeChecker() {
		startSet = new HashMap<>();
	}

	private static class Singleton {
		private static final TimeChecker instance = new TimeChecker();
	}

	public static TimeChecker getInstance() {
		return Singleton.instance;
	}


	public void start(String startKey) {
		startSet.put(startKey,  System.currentTimeMillis());
		startSet.get(startKey);
	}

	public Long end(String startKey) {
		try{
			Long startTime = startSet.get(startKey);
			startSet.remove(startKey);
			return (System.currentTimeMillis() - startTime);
		}catch (Exception e){
			return (long)CAN_NOT_FIND_START_VALUE;
		}
	}
}