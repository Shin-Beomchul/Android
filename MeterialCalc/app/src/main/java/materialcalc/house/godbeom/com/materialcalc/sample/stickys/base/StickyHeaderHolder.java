package materialcalc.house.godbeom.com.materialcalc.sample.stickys.base;

import android.view.View;

import org.zakariya.stickyheaders.SectioningAdapter;

import butterknife.ButterKnife;
import materialcalc.house.godbeom.com.materialcalc.model.UIItem;
import materialcalc.house.godbeom.com.materialcalc.model.UISection;

/**
 * Created by Administrator on 2018-03-06.
 */

public abstract class StickyHeaderHolder extends SectioningAdapter.HeaderViewHolder {
	public StickyHeaderHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		onCreate();
	}

	public abstract void onCreate();
	public abstract void onBind(UISection section, UIItem item, int sectionIndex,int itemType);
}
