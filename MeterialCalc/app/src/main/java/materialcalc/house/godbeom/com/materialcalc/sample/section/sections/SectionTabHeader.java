package materialcalc.house.godbeom.com.materialcalc.sample.section.sections;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.dtos.SectionDTO;
import materialcalc.house.godbeom.com.materialcalc.sample.section.holders.FooterTabHolderA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.holders.HeaderTabHolderA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.holders.ItemTabHolderA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionParameters;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Administrator on 2018-03-14.
 */

public class SectionTabHeader extends StatelessSection {
	final String TAG;
	List<SectionDTO> list;

	FooterTabHolderA.onMoreListener mMoreListener;

	/**
	 * Fragment Context
	 */
	Context mContext;


	public SectionTabHeader(Context context, String sectionTag, String title, List<SectionDTO> list,FooterTabHolderA.onMoreListener moreListener) {
		super(SectionParameters.builder()
				.headerResourceId(R.layout.header_section_tab)
				.itemResourceId(R.layout.item_section_b)
				.footerResourceId(R.layout.footer_a)
				.build());
		this.mContext = context;
		this.list = list;
		TAG = sectionTag;
		this.mMoreListener = moreListener;
	}

	@Override
	public int getContentItemsTotal() {
		return list.size();
	}

	/*아이템*/
	@Override
	public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
		final ItemTabHolderA itemHolder = (ItemTabHolderA) holder;
		Glide.with(itemHolder.iv_itemA.getContext())
				.load(list.get(position).getImageUrl())
				.apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
				.into(itemHolder.iv_itemA);
	}

	/*더보기*/
	@Override
	public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
		FooterTabHolderA footerHolderA = (FooterTabHolderA) holder;
		super.onBindFooterViewHolder(holder);
	}

	/*헤더*/
	@Override
	public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
		HeaderTabHolderA headerHolder = (HeaderTabHolderA) holder;
	}




	/*아이템 생성*/
	@Override
	public RecyclerView.ViewHolder getItemViewHolder(View view) {
		return new ItemTabHolderA(view);
	}
	/*헤더 생성*/
	@Override
	public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
		return new HeaderTabHolderA(view);
	}
	/*더보기 생성*/
	@Override
	public RecyclerView.ViewHolder getFooterViewHolder(View view) {
		FooterTabHolderA footerHolder =new FooterTabHolderA(view);
		footerHolder.morebtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mMoreListener.onMore(TAG);
			}
		});
		return footerHolder;
	}


	public List<SectionDTO> getList() {
		return list;
	}
}
