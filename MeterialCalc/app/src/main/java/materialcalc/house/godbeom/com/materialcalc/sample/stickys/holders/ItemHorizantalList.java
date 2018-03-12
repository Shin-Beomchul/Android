package materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.model.UIItem;
import materialcalc.house.godbeom.com.materialcalc.model.UISection;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.adapters.ItemHozListAdapter;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.base.StickyItemHolder;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.dto.HozImgListDTO;

/**
 * Created by BeomChul.Shin on 2018-03-07.
 */

public class ItemHorizantalList extends StickyItemHolder {

	@BindView(R.id.recyclerViewItem)
	RecyclerView recyclerViewHoz;
	Context mContext ;

	ItemHozListAdapter hozListAdapter;
	LinearLayoutManager virticalLayoutManagaer;

	public ItemHorizantalList(int viewRes, ViewGroup parent, boolean attachToRoot) {
		super(R.layout.item_list_content, parent, attachToRoot);
		mContext =parent.getContext();
	}


	@Override
	public void onCreate() {
		hozListAdapter = new ItemHozListAdapter(recyclerViewHoz.getContext(), Collections.<HozImgListDTO>emptyList());
	    virticalLayoutManagaer = new LinearLayoutManager(recyclerViewHoz.getContext(), LinearLayoutManager.HORIZONTAL, false);
		recyclerViewHoz.setLayoutManager(virticalLayoutManagaer);
		recyclerViewHoz.setAdapter(hozListAdapter);
	}

	@Override
	public void onBind(UISection section, UIItem item, int sectionIndex, int itemIndex, int itemType) {


		hozListAdapter.setHozListItems((ArrayList<HozImgListDTO>)item.getData());
		hozListAdapter.setClickListener(new ItemHozListAdapter.ItemClickListener() {
			@Override
			public void onItemClick(View view, HozImgListDTO dataDTO, int position) {
				Toast.makeText(recyclerViewHoz.getContext(),dataDTO.getName(),Toast.LENGTH_LONG).show();
			}
		});
		hozListAdapter.notifyDataSetChanged();
		}


}
