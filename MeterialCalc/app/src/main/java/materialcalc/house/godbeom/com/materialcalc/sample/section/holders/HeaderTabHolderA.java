package materialcalc.house.godbeom.com.materialcalc.sample.section.holders;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.BaseHolder;

public class HeaderTabHolderA extends BaseHolder {
		@BindView(R.id.btnBest) public TextView btnBest;
		@BindView(R.id.btnComment)public 	TextView btnComment;
		@BindView(R.id.btnPlay) public	TextView btnPlay;

		public HeaderTabHolderA(View itemView) {
			super(itemView);
		}
	}