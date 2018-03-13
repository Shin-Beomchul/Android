package materialcalc.house.godbeom.com.materialcalc.sample.stickys.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;

import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.model.UIItem;
import materialcalc.house.godbeom.com.materialcalc.model.UISection;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.base.StickyFooterHolder;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.base.StickyHeaderHolder;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.base.StickyItemHolder;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders.FooterA;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders.FooterB;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders.HeaderA;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders.HeaderB;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders.ItemA;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders.ItemB;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders.HeaderBannerA;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders.ItemHorizantalList;

/**
 * Created by BeomChul.Shin on 2018-03-06.
 */

public class HIFAdapter extends SectioningAdapter{




	public final static int ITEM_A = 1;
	public final static int ITEM_B = 2;

	public final static int FOOTER_A = 3;
	public final static int FOOTER_B = 4;

	public final static int HEADER_A = 5;
	public final static int HEADER_B = 6;
	public final static int HEADER_BANNER_A = 0;

	public final static int ITEM_HORIZANTAL_LIST = 7;
	FooterA.MoreListener moreListener;


	ArrayList<UISection> mSections;
	Context mContext;

	public HIFAdapter(Context context, ArrayList<UISection> sections) {
		mContext = context;
		mSections = sections;

	}


	/**
	 * 섹션의 개수
	 */
	@Override
	public int getNumberOfSections() {
		return mSections.size();
	}


	/**
	 * 섹션의 아이템 개수
	 */
	@Override
	public int getNumberOfItemsInSection(int sectionIndex) {
		return mSections.get(sectionIndex).getItems().size();
	}

	@Override
	public boolean doesSectionHaveHeader(int sectionIndex) {
		return mSections.get(sectionIndex).hasHeader();
	}

	@Override
	public boolean doesSectionHaveFooter(int sectionIndex) {
		return mSections.get(sectionIndex).hasFooter();
	}

	/**
	 * 헤더 뷰 종류
	 */
	@Override
	public int getSectionHeaderUserType(int sectionIndex) {
		return mSections.get(sectionIndex).getHeader().getViewType();
	}


	/**
	 * 아이템 뷰 종류
	 */
	@Override
	public int getSectionItemUserType(int sectionIndex, int itemIndex) {
		return mSections.get(sectionIndex).getItems().get(itemIndex).getViewType();
	}

	/**
	 * 푸터 뷰 종류
	 */
	@Override
	public int getSectionFooterUserType(int sectionIndex) {
		return mSections.get(sectionIndex).getFooter().getViewType();
	}

	/**
	 * viewXML을 즉시 트레이싱.
	 */
	@Override
	public StickyItemHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
		switch (itemUserType) {
			case ITEM_A:
				return new ItemA(R.layout.item_a, parent, false); //Code Type 2 -Adapter  view Res 트레이싱용.
			case ITEM_B:
				return new ItemB(inflateHolderView(R.layout.item_b, parent, false)); //Code Type 1 -Adapter
			case ITEM_HORIZANTAL_LIST:
				return new ItemHorizantalList(R.layout.item_list_content, parent, false);
			default:
				return new ItemB(inflateHolderView(R.layout.item_b, parent, false));
		}
	}



	@Override
	public StickyHeaderHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
		switch (headerType) {
			case HEADER_A:
				return new HeaderA(inflateHolderView(R.layout.header_a, parent, false));
			case HEADER_B:
				return new HeaderB(inflateHolderView(R.layout.header_b, parent, false));
			case HEADER_BANNER_A:
				return new HeaderBannerA(inflateHolderView(R.layout.item_benner, parent, false));
			default:
				return new HeaderB(inflateHolderView(R.layout.header_b, parent, false));
		}
	}

	@Override
	public StickyFooterHolder onCreateFooterViewHolder(ViewGroup parent, int footerType) {
		switch (footerType) {
			case FOOTER_A:
				return new FooterA(inflateHolderView(R.layout.footer_a, parent, false),moreListener);
			case FOOTER_B:
				return new FooterB(inflateHolderView(R.layout.footer_b, parent, false));
			default:
				return new FooterB(inflateHolderView(R.layout.footer_b, parent, false));
		}
	}


	@Override
	public void onBindItemViewHolder(ItemViewHolder itemViewHolder, int sectionIndex, int itemIndex, int itemType) {
		UISection section = mSections.get(sectionIndex);
		UIItem item = section.getItems().get(itemIndex);
		StickyItemHolder holder = (StickyItemHolder) itemViewHolder;
		holder.onBind(section, item, sectionIndex, itemIndex, itemType);
	}

	@Override
	public void onBindHeaderViewHolder(HeaderViewHolder headerViewHolder, int sectionIndex, int headerType) {
		UISection section = mSections.get(sectionIndex);
		StickyHeaderHolder holder = (StickyHeaderHolder) headerViewHolder;
		holder.onBind(section, section.getHeader(), sectionIndex, headerType);
	}

	@Override
	public void onBindFooterViewHolder(FooterViewHolder footerViewHolder, int sectionIndex, int footerType) {
		UISection section = mSections.get(sectionIndex);
		StickyFooterHolder holder = (StickyFooterHolder) footerViewHolder;
		holder.onBind(section, section.getFooter(), sectionIndex, footerType);
	}


	private View inflateHolderView(int viewId, ViewGroup parent, boolean attachToRoot) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		return inflater.inflate(viewId, parent, attachToRoot);
	}


	public void setMoreListener(FooterA.MoreListener moreListener) {
		this.moreListener = moreListener;
	}


}
