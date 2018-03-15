package materialcalc.house.godbeom.com.materialcalc.sample.section.holders;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.BaseHolder;

public class FooterTabHolderA extends BaseHolder {

	@BindView(R.id.morebtn)
	public Button morebtn;

	public FooterTabHolderA(View itemView) {
		super(itemView);
	}



	public interface onMoreListener{
		public void onMore(String sectionTag);
	}
}