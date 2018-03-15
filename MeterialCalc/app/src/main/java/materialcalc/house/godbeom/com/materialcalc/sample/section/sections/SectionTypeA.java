package materialcalc.house.godbeom.com.materialcalc.sample.section.sections;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.dtos.SectionDTO;
import materialcalc.house.godbeom.com.materialcalc.sample.section.holders.FooterHolderA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.holders.HeaderHolderA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.holders.ItemHolderA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionParameters;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.StatelessSection;

public class SectionTypeA extends StatelessSection {
	final String TAG;
	String title;
	List<SectionDTO> list;
	Context mContext;

	public SectionTypeA(Context context, String sectionTag, String title, List<SectionDTO> list) {
		super(SectionParameters.builder()
				.itemResourceId(R.layout.item_section_a)
				.headerResourceId(R.layout.header_section_a)
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
		return new ItemHolderA(view);
	}

	@Override
	public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
		final ItemHolderA itemHolder = (ItemHolderA) holder;

		final String name = list.get(position).getName();

		itemHolder.tvItem.setText(name);

		Glide.with(itemHolder.iv_itemA.getContext())
				.load(list.get(position).getImageUrl())
				.apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
				.into(itemHolder.iv_itemA);

		itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "Click Item " + name,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
		return new HeaderHolderA(view);
	}

	@Override
	public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
		HeaderHolderA headerHolder = (HeaderHolderA) holder;
		headerHolder.tvHeader.setText(title);
	}


	@Override
	public RecyclerView.ViewHolder getFooterViewHolder(View view) {
		return new FooterHolderA(view);
	}

	@Override
	public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
		FooterHolderA footerHolderA = (FooterHolderA) holder;
		footerHolderA.morebtn.setText(title + "  -더보기");
		super.onBindFooterViewHolder(holder);
	}


}