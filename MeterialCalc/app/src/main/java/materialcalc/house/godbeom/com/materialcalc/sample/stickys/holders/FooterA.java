package materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.model.UIItem;
import materialcalc.house.godbeom.com.materialcalc.model.UISection;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.base.StickyFooterHolder;

/**
 * Created by BeomChul.Shin on 2018-03-06.
 * 더보기
 */

public class FooterA extends StickyFooterHolder {

	@BindView(R.id.morebtn)
	Button morebtn;
	MoreListener moreListener;


	public FooterA(View itemView,MoreListener moreListener) {
		super(itemView);
		this.moreListener = moreListener;
	}

	@Override
	public void onCreate() {

	}

	@Override
	public void onBind(final UISection section, final UIItem item, final int sectionIndex, int itemType) {
		morebtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				moreListener.onClickLoadMore(section,item,sectionIndex);
			}
		});

	}








	public interface MoreListener {
		public void onClickLoadMore(UISection section,UIItem moreItem,int sectionIdx);
	}
}
