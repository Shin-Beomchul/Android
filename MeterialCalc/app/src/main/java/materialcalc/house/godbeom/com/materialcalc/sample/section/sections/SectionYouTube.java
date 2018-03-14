package materialcalc.house.godbeom.com.materialcalc.sample.section.sections;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.dtos.SectionDTO;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionParameters;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Administrator on 2018-03-14.
 */

public class SectionYouTube extends StatelessSection {
	final String TAG;
	String title;
	List<SectionDTO> list;
	Context mContext;

	public SectionYouTube(Context context, String sectionTag, String title, List<SectionDTO> list) {
		super(SectionParameters.builder()
				.itemResourceId(R.layout.item_section_youtube)
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
		return new SectionYouTube.ItemYouTubeHolderA(view);
	}

	@Override
	public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
		final SectionYouTube.ItemYouTubeHolderA itemHolder = (SectionYouTube.ItemYouTubeHolderA) holder;

	}




	class ItemYouTubeHolderA extends BaseHolder {

		public ItemYouTubeHolderA(View itemView) {
			super(itemView);
		}
	}


}
