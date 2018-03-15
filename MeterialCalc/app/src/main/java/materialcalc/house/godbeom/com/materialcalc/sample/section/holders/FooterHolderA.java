package materialcalc.house.godbeom.com.materialcalc.sample.section.holders;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.BaseHolder;

public class FooterHolderA extends BaseHolder {

		@BindView(R.id.morebtn)
		public Button morebtn;

		public FooterHolderA(View itemView) {
			super(itemView);
		}
	}