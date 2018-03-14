package materialcalc.house.godbeom.com.materialcalc.sample.section.sections;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-03-13.
 */

public abstract class BaseHolder  extends RecyclerView.ViewHolder {
	public View rootView;
	public BaseHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		rootView = itemView;
	}


}
