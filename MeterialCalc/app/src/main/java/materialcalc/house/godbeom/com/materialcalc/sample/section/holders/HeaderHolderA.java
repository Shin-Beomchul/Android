package materialcalc.house.godbeom.com.materialcalc.sample.section.holders;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.BaseHolder;

public class HeaderHolderA extends BaseHolder {
		@BindView(R.id.headerTv)
		public TextView tvHeader;

		public HeaderHolderA(View itemView) {
			super(itemView);
		}
	}