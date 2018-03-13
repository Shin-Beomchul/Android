package materialcalc.house.godbeom.com.materialcalc.sample.stickys;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.model.UIItem;
import materialcalc.house.godbeom.com.materialcalc.model.UISection;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.adapters.HIFAdapter;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.dto.HozImgListDTO;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.holders.FooterA;
import materialcalc.house.godbeom.com.materialcalc.sample.utill.DeviceUtil;

/**
 * Created by BeomChul.Shin on 2018-03-06.
 *
 * grid 대응 문제. : android sectioned grid recyclerview
 * space 대응 문제.
 *
 */
public class ActStickySample extends AppCompatActivity implements FooterA.MoreListener {

	@BindView(R.id.stickyRecycler)
	RecyclerView mRecyclerView;
	HIFAdapter mHIFStickyAdapter = null;
	ArrayList<UISection> mSections = new ArrayList<UISection>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_sticky_sample);
		ButterKnife.bind(this);
		createStickyList();
		updateStickyList();
	}


	/**
	 * 생성
	 */
	public void createStickyList() {
		if (mHIFStickyAdapter != null)
			return;

		mHIFStickyAdapter = new HIFAdapter(getApplicationContext(), mSections);
		mHIFStickyAdapter.setMoreListener(this);
		changeCol(1);
//		changeCol(2); //FIXME

		mRecyclerView.setAdapter(mHIFStickyAdapter);
	}

	/**
	 * 업데이트
	 */
	public void updateStickyList() {
		mSections.add(sectionBanner(HIFAdapter.HEADER_BANNER_A));
		mSections.add(sectionTypeB());
		mSections.add(sectionTypeA());
		mSections.add(sectionMixType());
		mHIFStickyAdapter.notifyAllSectionsDataSetChanged();
	}

	/**
	 * 추가
	 */
	public void addItem(UIItem addItem, int sectionIdx) {

		if (mSections == null || mHIFStickyAdapter == null) {
			return;    //err
		}

		ArrayList<UIItem> sectionInItems = mSections.get(sectionIdx).getItems();
		sectionInItems.add(addItem);
		mHIFStickyAdapter.notifySectionItemChanged(sectionIdx, sectionInItems.size() - 1);
	}



	/**더보기*/
	@Override
	public void onClickLoadMore(UISection section, UIItem moreItem, int sectionIndex) {
		addItem(dummyHorizontalListItem(HIFAdapter.ITEM_HORIZANTAL_LIST, 4), sectionIndex);
		Toast.makeText(getApplicationContext(), sectionIndex + " 번째 섹션 추가.", Toast.LENGTH_LONG).show();
	}




	/**mode 변경*/
	public void changeCol(int col){
		if(col ==0 || col ==1){
			StickyHeaderLayoutManager StickylayoutManager = new StickyHeaderLayoutManager();
			StickylayoutManager.setHeaderPositionChangedCallback(new StickyHeaderLayoutManager.HeaderPositionChangedCallback() {
				@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
				@Override
				public void onHeaderPositionChanged(int sectionIndex, View header, StickyHeaderLayoutManager.HeaderPosition oldPosition, StickyHeaderLayoutManager.HeaderPosition newPosition) {
					if (DeviceUtil.checkEnableVersion(Build.VERSION_CODES.LOLLIPOP)) {
						boolean elevated = newPosition == StickyHeaderLayoutManager.HeaderPosition.STICKY;
						header.setElevation(elevated ? 8 : 0);
					}
				}
			});
			mRecyclerView.setLayoutManager(StickylayoutManager);

			/*try Grid*/
		}else{
			final GridLayoutManager manager = new GridLayoutManager(this, col);
			mRecyclerView.setLayoutManager(manager);
			manager.setItemPrefetchEnabled(false);
			manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
				@Override
				public int getSpanSize(int position) {
					int sectionIdx = mHIFStickyAdapter.getSectionForAdapterPosition(position);
//					int sectionInItemIdx =mHIFStickyAdapter.getPositionOfItemInSection(sectionIdx,position);
					int headerPos =mHIFStickyAdapter.getAdapterPositionForSectionHeader(sectionIdx);
					int footerPos =mHIFStickyAdapter.getAdapterPositionForSectionFooter(sectionIdx);
					if (mSections.get(sectionIdx).hasHeader() && headerPos ==position ||
						mSections.get(sectionIdx).hasFooter() && footerPos ==position ) {
						return manager.getSpanCount();
					}
					return 1;
				}
			});
			mRecyclerView.setLayoutManager(manager);
		}
	}


























	/**Sample Data*/
	public UISection sectionTypeA() {
		UISection section = new UISection();
		UIItem footer = new UIItem();
		UIItem header = new UIItem();
		ArrayList<UIItem> items = new ArrayList<UIItem>();

		//header
		header.putViewType(HIFAdapter.HEADER_A);
		//items
		items = dummyItemGenerator("Item", HIFAdapter.ITEM_A, 10);
		//footer
		footer.putViewType(HIFAdapter.FOOTER_A);


		section.addItems(items);
		section.setFooter(footer);
		section.setHeader(header);


		return section;
	}

	public UISection sectionTypeB() {
		UISection section = new UISection();
		UIItem footer = new UIItem();
		UIItem header = new UIItem();
		ArrayList<UIItem> items = new ArrayList<UIItem>();

		//header
		header.putViewType(HIFAdapter.HEADER_B);
		//items
		items = dummyItemGenerator("Item", HIFAdapter.ITEM_B, 5);
		//footer
		footer.putViewType(HIFAdapter.FOOTER_B);


		section.addItems(items);
		section.setFooter(footer);
		section.setHeader(header);
		return section;
	}

	public UISection sectionBanner(int banerType) {
		UISection section = new UISection();
		section.setHeader(BannerItemGenerator(banerType));
		return section;
	}

	public UISection sectionMixType() {
		UISection section = new UISection();
		UIItem footer = new UIItem();
		UIItem header = new UIItem();
		ArrayList<UIItem> items = new ArrayList<UIItem>();

		//header
		header.putViewType(HIFAdapter.HEADER_B);
		//items
		UIItem item1 = new UIItem();
		item1.putViewType(HIFAdapter.ITEM_A);
		item1.putData("item1");


		UIItem itemHozList = dummyHorizontalListItem(HIFAdapter.ITEM_HORIZANTAL_LIST, 5);


		UIItem item2 = new UIItem();
		item2.putViewType(HIFAdapter.ITEM_B);
		item2.putData("item2");

		UIItem item3 = new UIItem();
		item3.putViewType(HIFAdapter.ITEM_A);
		item3.putData("item3");


		UIItem itemHozList2 = dummyHorizontalListItem(HIFAdapter.ITEM_HORIZANTAL_LIST, 5);
		UIItem item4 = new UIItem();
		item4.putViewType(HIFAdapter.ITEM_B);
		item4.putData("item4");

		UIItem item5 = new UIItem();
		item5.putViewType(HIFAdapter.ITEM_A);
		item5.putData("item5");

		UIItem item6 = new UIItem();
		item6.putViewType(HIFAdapter.ITEM_B);
		item6.putData("item6");

		//footer
		footer.putViewType(HIFAdapter.FOOTER_A);


		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(itemHozList);
		items.add(item4);
		items.add(item5);
		items.add(itemHozList2);
		items.add(item6);


		section.addItems(items);
		section.setFooter(footer);
		section.setHeader(header);


		return section;
	}

	ArrayList<UIItem> dummyItemGenerator(String name, int viewType, int cnt) {

		ArrayList<UIItem> uiItems = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			UIItem item = new UIItem();
			item.putViewType(viewType);
			item.putData(name);
			uiItems.add(item);
		}
		return uiItems;
	}

	public UIItem BannerItemGenerator(int bannerType){
		UIItem item = new UIItem();
		item.putViewType(bannerType);
		item.putData("NANNER");
		return item;
	}

	UIItem dummyHorizontalListItem(int viewType, int cnt) {

		UIItem item = new UIItem();
		ArrayList<HozImgListDTO> hozItems = new ArrayList<>();
		item.putViewType(viewType);

		for (int i = 0; i < cnt; i++) {
			HozImgListDTO hozImgListDTO = new HozImgListDTO();
			hozImgListDTO.setName("이미지 + " + i);

			if (i % 2 == 0) {
				hozImgListDTO.setImgPath("https://cdn.pixabay.com/photo/2017/12/29/18/47/turkey-3048299_960_720.jpg");
			} else if (i % 3 == 0) {
				hozImgListDTO.setImgPath("https://cdn.pixabay.com/photo/2018/03/12/00/43/portrait-3218469_960_720.jpg");
			} else {
				hozImgListDTO.setImgPath("https://cdn.pixabay.com/photo/2015/03/12/04/43/landscape-669619_960_720.jpg");
			}

			hozItems.add(hozImgListDTO);
		}

		item.putData(hozItems);
		return item;
	}


}
