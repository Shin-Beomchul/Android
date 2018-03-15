package materialcalc.house.godbeom.com.materialcalc.sample.section.sections;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.dtos.SectionDTO;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionParameters;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Administrator on 2018-03-14.
 */
public class SectionHozRow extends StatelessSection {
	final String TAG;
	String title;
	List<SectionDTO> list;
	MoreClick moreClick;

	/**
	 * Fragment Context
	 */
	Context mContext;
	SectionedRecyclerViewAdapter sectionAdapter;

	public SectionHozRow(Context context, String sectionTag, List<SectionDTO> list, MoreClick moreClick) {
		super(SectionParameters.builder()
				.itemResourceId(R.layout.item_section_hoz)
				.build());
		this.mContext = context;
		this.title = title;
		this.list = list;
		TAG = sectionTag;
		this.moreClick = moreClick;
	}

	@Override
	public int getContentItemsTotal() {
		return list.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder(View view) {
		ItemTabMoreHolderA createdHolder = new ItemTabMoreHolderA(view);
		return createdHolder;
	}

	@Override
	public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
		final ItemTabMoreHolderA itemHolder = (ItemTabMoreHolderA) holder;

		Glide.with(itemHolder.iv_itemA.getContext())
				.load(list.get(position).getImageUrl())
				.apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
				.into(itemHolder.iv_itemA);
	}









	class ItemTabMoreHolderA extends BaseHolder {
		@BindView(R.id.iv_itemA)
		ImageView iv_itemA;

		public ItemTabMoreHolderA(View itemView) {
			super(itemView);
		}
	}



	interface  MoreClick{
	 public void onMore();
	}

}
