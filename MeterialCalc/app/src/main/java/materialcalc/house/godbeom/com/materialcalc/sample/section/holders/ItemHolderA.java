package materialcalc.house.godbeom.com.materialcalc.sample.section.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.BaseHolder;

public class ItemHolderA extends BaseHolder {
		@BindView(R.id.tvItem)
		public TextView tvItem;

		@BindView(R.id.iv_itemA)
		public ImageView iv_itemA;


		public ItemHolderA(View itemView) {
			super(itemView);
		}
	}