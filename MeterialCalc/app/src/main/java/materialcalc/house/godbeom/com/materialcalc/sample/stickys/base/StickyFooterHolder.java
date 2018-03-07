package materialcalc.house.godbeom.com.materialcalc.sample.stickys.base;

import android.view.View;

import org.zakariya.stickyheaders.SectioningAdapter;

import butterknife.ButterKnife;
import materialcalc.house.godbeom.com.materialcalc.model.UIItem;
import materialcalc.house.godbeom.com.materialcalc.model.UISection;

/**
 * Created by Administrator on 2018-03-06.
 *
 * HIF 중복 분리 이유 : 각 영역별 고유한 기능이 필요 할 수 있음.
 */

public abstract  class StickyFooterHolder extends SectioningAdapter.FooterViewHolder {

	public StickyFooterHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		onCreate();
	}


	public abstract void onCreate();
	public abstract void onBind(UISection section, UIItem item, int sectionIndex, int itemType);

}
