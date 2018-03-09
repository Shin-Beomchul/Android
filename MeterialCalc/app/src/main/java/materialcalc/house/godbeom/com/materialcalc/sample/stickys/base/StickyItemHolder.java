package materialcalc.house.godbeom.com.materialcalc.sample.stickys.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zakariya.stickyheaders.SectioningAdapter;

import butterknife.ButterKnife;
import materialcalc.house.godbeom.com.materialcalc.model.UIItem;
import materialcalc.house.godbeom.com.materialcalc.model.UISection;

/**
 * Created by BeomChul.Shin on 2018-03-06.
 */

public abstract class StickyItemHolder extends SectioningAdapter.ItemViewHolder{

	/*param*/
	public StickyItemHolder(int viewRes, ViewGroup parent, boolean attachToRoot){
		super(LayoutInflater.from(parent.getContext()).inflate(viewRes, parent, attachToRoot));
		ButterKnife.bind(this, itemView); //itemView is Root inflateing Completed
		onCreate();

	}

	/**param viewInstance*/
	public StickyItemHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		onCreate();
	}


	public abstract void onCreate();
	public abstract void onBind(UISection section, UIItem item, int sectionIndex, int itemIndex, int itemType);
}
