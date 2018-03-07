package materialcalc.house.godbeom.com.materialcalc.sample.stickys;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.model.UIItem;
import materialcalc.house.godbeom.com.materialcalc.model.UISection;
import materialcalc.house.godbeom.com.materialcalc.sample.utill.DeviceUtil;

public class ActStickySample extends AppCompatActivity {

	@BindView(R.id.stickyRecycler)
	RecyclerView mRecyclerView;

	HIFAdapter mStickyAdapter=null;

	 ArrayList<UISection> mSections = new ArrayList<UISection>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.act_sticky_sample);
		ButterKnife.bind(this);
		createStickyList();
		updateStickyList();
	}


	public void createStickyList(){
		if(mStickyAdapter !=null)
			return ;


		mStickyAdapter = new HIFAdapter(getApplicationContext(),mSections);


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
		mRecyclerView.setAdapter(mStickyAdapter);
	}

	public void updateStickyList(){
		mSections.add(GeneratorBannerSection(HIFAdapter.BANNER_A));
		mSections.add(sectionFactoryTypeA());
		mSections.add(sectionFactoryTypeB());
		mStickyAdapter.notifyAllSectionsDataSetChanged();
	}


	public UISection sectionFactoryTypeA(){
		UISection section = new UISection();
		UIItem footer = new UIItem();
		UIItem header = new UIItem();
		ArrayList<UIItem> items = new ArrayList<UIItem>();

		//header
		header.putViewType(HIFAdapter.HEADER_A);
		//items
		items = dummyItemGenerator("Item",HIFAdapter.ITEM_B,10);

		//footer
		footer.putViewType(HIFAdapter.FOOTER_A);


		section.addItems(items);
		section.setFooter(footer);
		section.setHeader(header);


		return section;
	}
	public UISection sectionFactoryTypeB(){
		UISection section = new UISection();
		UIItem footer = new UIItem();
		UIItem header = new UIItem();
		ArrayList<UIItem> items = new ArrayList<UIItem>();

		//header
		header.putViewType(HIFAdapter.HEADER_B);
		//items
		items = dummyItemGenerator("Item",HIFAdapter.ITEM_A,5);
		//footer
		footer.putViewType(HIFAdapter.FOOTER_B);


		section.addItems(items);
		section.setFooter(footer);
		section.setHeader(header);
		return section;
	}
	public UISection GeneratorBannerSection(int banerType){
		UISection section = new UISection();
		ArrayList<UIItem> items = new ArrayList<UIItem>();
		items = dummyItemGenerator("Item",banerType,1);
		section.addItems(items);
		return section;
	}


	ArrayList<UIItem> dummyItemGenerator(String name, int viewType ,int cnt){

		ArrayList<UIItem> uiItems = new ArrayList<>();
		for(int i=0; i <cnt; i ++){
			UIItem  item = new UIItem();
			item.putViewType(viewType);
			item.putData(name);
			uiItems.add(item);
		}
		return uiItems;
	}

	ArrayList<UIItem> dummyHorizontalListItemGenerator(String name, int viewType ,int cnt){

		ArrayList<UIItem> uiItems = new ArrayList<>();
		for(int i=0; i <cnt; i ++){
			UIItem  item = new UIItem();
			item.putViewType(viewType);
			item.putData(name);
			uiItems.add(item);
		}
		return uiItems;
	}

}
