package materialcalc.house.godbeom.com.materialcalc.sample.utill.adapter;

import android.support.v4.app.FragmentStatePagerAdapter;

import materialcalc.house.godbeom.com.materialcalc.sample.utill.fragments.FmtTabA;
import materialcalc.house.godbeom.com.materialcalc.sample.utill.fragments.FmtTabB;

/**
 * Created by Administrator on 2018-03-15.
 */

public class SimplePagerAdapter extends FragmentStatePagerAdapter {
	public SimplePagerAdapter(android.support.v4.app.FragmentManager fm) {
		super(fm);
	}

	@Override
	public android.support.v4.app.Fragment getItem(int position) {
		switch (position) {
			case 0:
				return  FmtTabA.newInstance("Tab1","Tab2");
			case 1:
				return  FmtTabB.newInstance("Tab1","Tab2");
			case 2:
				return  FmtTabB.newInstance("Tab1","Tab2");
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position){
			case  0: return "CuRRICULUM";
			case  1: return "검증된 강사진";
			default:  return "학습 시스템";
		}
	}
}

