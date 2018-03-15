package materialcalc.house.godbeom.com.materialcalc.sample.section.sections;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.dtos.SectionDTO;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionParameters;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by BeomChul.Shin on 2018-03-14.
 */

public class SingleSectionBanner extends StatelessSection {
	final String TAG;
	String title;
	SectionDTO sectionDTO;
	Context mContext;

	public SingleSectionBanner(Context context, String sectionTag, String title, SectionDTO sectionDTO) {
		super(SectionParameters.builder()
				.itemResourceId(R.layout.item_section_youtube)
				.build());
		this.mContext = context;
		this.title = title;
		TAG = sectionTag;
		this.sectionDTO = sectionDTO;
	}

	@Override
	public int getContentItemsTotal() {
		return 1;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder(View view) {
		return new SingleSectionBanner.ItemYouTubeHolderA(view);
	}

	@Override
	public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
		final SingleSectionBanner.ItemYouTubeHolderA itemHolder = (SingleSectionBanner.ItemYouTubeHolderA) holder;

	}




	class ItemYouTubeHolderA extends BaseHolder {

		public ItemYouTubeHolderA(View itemView) {
			super(itemView);
		}
	}


}
