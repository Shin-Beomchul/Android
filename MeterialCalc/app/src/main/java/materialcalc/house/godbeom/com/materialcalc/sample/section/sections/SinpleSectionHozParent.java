package materialcalc.house.godbeom.com.materialcalc.sample.section.sections;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.dtos.SectionDTO;
import materialcalc.house.godbeom.com.materialcalc.sample.section.holders.HeaderHolderA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionParameters;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.StatelessSection;
import materialcalc.house.godbeom.com.materialcalc.sample.utill.DummyFat;

/**
 * Created by Administrator on 2018-03-14.
 * <p>
 * /**item 내부에 Recycler
 */


public class SinpleSectionHozParent extends StatelessSection {
	final String TAG;
	String title;
	List<SectionDTO> listItems = new ArrayList<SectionDTO>();

	/**
	 * Fragment Context
	 */
	Context mContext;
	SectionedRecyclerViewAdapter sectionAdapter;


	public SinpleSectionHozParent(Context context, String sectionTag, String title, List<SectionDTO> Hozlist) {
		super(SectionParameters.builder()
				.headerResourceId(R.layout.header_section_a)
				.itemResourceId(R.layout.item_section_tab)
				.build());
		this.mContext = context;
		this.title = title;
		TAG = sectionTag;
		listItems =Hozlist;
	}

	@Override
	public int getContentItemsTotal() {
		return 1;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder(View view) {
		final ItemHozList createdHolder = new ItemHozList(view);

		sectionAdapter = new SectionedRecyclerViewAdapter();
		createdHolder.recyclerView.setAdapter(sectionAdapter);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(createdHolder.recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
		createdHolder.recyclerView.setLayoutManager(linearLayoutManager);
		return createdHolder;
	}

	@Override
	public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
		final ItemHozList itemHolder = (ItemHozList) holder;

		//TODO API
		//Hoz Section
		sectionAdapter.addSection(TAG, new SectionHozRow(itemHolder.recyclerView.getContext(), TAG, listItems
				, new SectionHozRow.MoreClick() {
						@Override
						public void onMore() {
							int before = listItems.size();
							listItems.addAll(DummyFat.createDummyData(3));
						}
					}));
	}


	@Override
	public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
		return new HeaderHolderA(view);
	}


	@Override
	public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
		HeaderHolderA headerHolderA = (HeaderHolderA)holder;
		headerHolderA.tvHeader.setText(title);
		super.onBindHeaderViewHolder(holder);
	}

	class ItemHozList extends BaseHolder {
		@BindView(R.id.recyclerView)
		RecyclerView recyclerView;

		public ItemHozList(View itemView) {
			super(itemView);
		}
	}


}
