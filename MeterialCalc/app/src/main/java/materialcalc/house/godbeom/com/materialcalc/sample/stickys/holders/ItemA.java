package materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders;

import android.content.Context;
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
 * Created by Administrator on 2018-03-06.
 */

public class ItemA extends StickyItemHolder {

	Context mContext;
	@BindView(R.id.iv_itemA)
	ImageView iv_itemA;
	/*Code Type 2 -Holder View inflating */
	public ItemA(int viewRes, ViewGroup parent, boolean attachToRoot) {
		super(R.layout.item_a, parent, attachToRoot);
//		super(context, viewRes, parent, attachToRoot); /**둘 다 허용.*/
		mContext = parent.getContext();
	}





	@Override
	public void onCreate() {
		iv_itemA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(mContext ,"I'am A-Type imageView ",Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onBind(UISection section, UIItem item, int sectionIndex, int itemIndex, int itemType) {

	}
}
