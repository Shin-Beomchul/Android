package develop.godbeom.com.myapplication.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import develop.godbeom.com.myapplication.cache.CacheLruMemory;
/**
 * Created by BeomChul on 2018. 4. 14..
 */
public class RetainFragment extends Fragment {
    private static final String TAG = "RetainFragment";
		public CacheLruMemory mRetainedCache;

		public RetainFragment() {}

		public static RetainFragment findOrCreateRetainFragment(FragmentManager fm) {
			RetainFragment fragment = (RetainFragment) fm.findFragmentByTag(TAG);
			if (fragment == null) {
				fragment = new RetainFragment();
				fm.beginTransaction().add(fragment, TAG).commitAllowingStateLoss();
			}
			return fragment;
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setRetainInstance(true);
		}
	}