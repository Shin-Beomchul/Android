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
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Administrator on 2018-03-14.
 */

public class SectionTab extends StatelessSection {
	final String TAG;
	String title;
	List<SectionDTO> list;
	Context mContext;

	public SectionTab(Context context, String sectionTag, String title, List<SectionDTO> list) {
		super(SectionParameters.builder()
				.itemResourceId(R.layout.item_section_tab)
				.build());
		this.mContext = context;
		this.title = title;
		this.list = list;
		TAG = sectionTag;
	}

	@Override
	public int getContentItemsTotal() {
		return list.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder(View view) {
		return new SectionTab.ItemTabHolderA(view);
	}

	@Override
	public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
		final SectionTab.ItemTabHolderA itemHolder = (SectionTab.ItemTabHolderA) holder;

		Glide.with(itemHolder.ivTab.getContext())
				.load(list.get(position).getImageUrl())
				.apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
				.into(itemHolder.ivTab);
	}




	class ItemTabHolderA extends BaseHolder {

		@BindView(R.id.ivTab)
		ImageView ivTab;
		public ItemTabHolderA(View itemView) {
			super(itemView);
		}
	}


}
