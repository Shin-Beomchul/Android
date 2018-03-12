package materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.model.UIItem;
import materialcalc.house.godbeom.com.materialcalc.model.UISection;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.base.StickyItemHolder;

/**
 * Created by BeomChul.Shin on 2018-03-06.
 */

public class ItemA extends StickyItemHolder {

	@BindView(R.id.iv_itemA)
	ImageView iv_itemA;
	/*Code Type 2 -Holder View inflating */
	public ItemA(int viewRes, ViewGroup parent, boolean attachToRoot) {
		super(R.layout.item_a, parent, attachToRoot);
	}



	@Override
	public void onCreate() {
		iv_itemA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(iv_itemA.getContext() ,"I'am A-Type imageView ",Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onBind(UISection section, UIItem item, int sectionIndex, int itemIndex, int itemType) {

	}
}
