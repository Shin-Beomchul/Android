package materialcalc.house.godbeom.com.materialcalc.sample.section.holders;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.BaseHolder;

public class ItemTabHolderA extends BaseHolder {

	@BindView(R.id.iv_itemA)
	public ImageView iv_itemA;
		public ItemTabHolderA(View itemView) {
			super(itemView);
		}
	}