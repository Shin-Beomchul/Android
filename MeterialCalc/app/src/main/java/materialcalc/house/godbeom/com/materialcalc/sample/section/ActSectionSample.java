package materialcalc.house.godbeom.com.materialcalc.sample.section;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.dtos.SectionDTO;
import materialcalc.house.godbeom.com.materialcalc.sample.section.holders.FooterTabHolderA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.SectionTabHeader;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.SectionTypeA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.SingleSectionBanner;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.SinpleSectionHozParent;
import materialcalc.house.godbeom.com.materialcalc.sample.utill.DummyFat;

public class ActSectionSample extends AppCompatActivity {


	int ListCOL= 2;
	private SectionedRecyclerViewAdapter sectionAdapter;
	@BindView(R.id.sectionRecyclerView)
	RecyclerView recyclerView;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_section);
		ButterKnife.bind(this);

		createList();
		updateList();
	}


	public void createList() {
		sectionAdapter = new SectionedRecyclerViewAdapter();
		final GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), ListCOL);
		glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {

				switch (sectionAdapter.getSectionItemViewType(position)) {
					case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
					case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
						return glm.getSpanCount();
				}

				if (isSectionInItemByTag("SectionTab", position)) {
					return glm.getSpanCount();
				}

				if (isSectionInItemByTag("SectionBanner", position)) {
					return glm.getSpanCount();
				}

				if (isSectionInItemByTag("SectionHoz", position)) {
					return glm.getSpanCount();
				}

				return 1;
			}
		});
		recyclerView.setLayoutManager(glm);
		recyclerView.setAdapter(sectionAdapter);
	}

	public void updateList() {

		//TODO API
		sectionAdapter.addSection("SectionBanner", new SingleSectionBanner(this, "SectionBanner", "배너", new SectionDTO()));
		sectionAdapter.addSection("SectionHoz",new SinpleSectionHozParent(this, "SectionHoz", "카테고리 추천", DummyFat.createDummyData(4)));
		sectionAdapter.addSection("SectionTypeA-1",new SectionTypeA(this, "SectionTypeA-1", "오프라인 교육", DummyFat.createDummyData(3)));
		sectionAdapter.addSection("SectionTab", new SectionTabHeader(this, "SectionTab", " 이미지 탭", DummyFat.createDummyTabData(3)
				, new FooterTabHolderA.onMoreListener() {
			@Override
			public void onMore(String sectionTag) {
				SectionTabHeader section =(SectionTabHeader)sectionAdapter.getSection(sectionTag);
				List<SectionDTO> items = section.getList();
				int before = items.size();
				//TODO API
				section.getList().addAll(DummyFat.createDummyTabData(3));
				sectionAdapter.notifyItemRangeChangedInSection(sectionTag,before,items.size());
				int sectionPos = sectionAdapter.getSectionPosition(sectionTag);
				recyclerView.smoothScrollToPosition(sectionPos+items.size()+1);
			}
		}));
	}

	/**
	 * tagSection에 포함된 Item 이면 true
	 */
	private boolean isSectionInItemByTag(String tag, int adaptPos) {
		int sectionStart = sectionAdapter.getSectionPosition(tag);
		int sectionInItemCnt = sectionAdapter.getSection(tag).getContentItemsTotal();
		if (sectionStart <= adaptPos && (sectionStart + sectionInItemCnt) >= adaptPos) {
			return true;
		} else {
			return false;
		}
	}




}
