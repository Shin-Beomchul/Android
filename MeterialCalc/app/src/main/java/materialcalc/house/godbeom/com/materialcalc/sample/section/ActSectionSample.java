package materialcalc.house.godbeom.com.materialcalc.sample.section;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.section.dtos.SectionDTO;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.SectionTab;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.SectionTypeA;
import materialcalc.house.godbeom.com.materialcalc.sample.section.sections.SectionYouTube;

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

				if (isSectionInItemByTag("SectionYouTube", position)) {
					return glm.getSpanCount();
				}

				return 1;
			}
		});
		recyclerView.setLayoutManager(glm);
		recyclerView.setAdapter(sectionAdapter);
	}

	public void updateList() {
		sectionAdapter.addSection("SectionYouTube", new SectionYouTube(this, "SectionYouTube", "유튜브", createDummyData(1)));
		sectionAdapter.addSection("SectionTab", new SectionTab(this, "SectionTab", "이미지 탭", createDummyTabData(3)));
		sectionAdapter.addSection(new SectionTypeA(this, "Section-010", "오프라인 교육", createDummyData(3)));
		sectionAdapter.addSection(new SectionTypeA(this, "Section-010", "온라인 교육", createDummyData(5)));
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

	private ArrayList<SectionDTO> createDummyData(int cnt) {
		ArrayList<SectionDTO> apples = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			SectionDTO appleA = new SectionDTO();
			appleA.setName("Introduction-Overview of implants :  " + i);
			if (i % 2 == 0) {
				appleA.setImageUrl("https://cdn.pixabay.com/photo/2017/12/29/18/47/turkey-3048299_960_720.jpg");
			} else if (i % 3 == 0) {
				appleA.setImageUrl("https://cdn.pixabay.com/photo/2018/03/12/00/43/portrait-3218469_960_720.jpg");
			} else {
				appleA.setImageUrl("https://cdn.pixabay.com/photo/2015/03/12/04/43/landscape-669619_960_720.jpg");
			}
			apples.add(appleA);
		}
		return apples;
	}


	private ArrayList<SectionDTO> createDummyTabData(int cnt) {
		ArrayList<SectionDTO> apples = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			SectionDTO appleA = new SectionDTO();
			appleA.setName("Introduction-Overview of implants :  " + i);
			if (i % 2 == 0) {
				appleA.setImageUrl("https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg");//설인
			} else if (i % 3 == 0) {
				appleA.setImageUrl("https://cdn.pixabay.com/photo/2018/03/02/18/29/snow-3193865_960_720.jpg");//눈
			} else {
				appleA.setImageUrl("https://cdn.pixabay.com/photo/2018/03/12/00/43/portrait-3218469_960_720.jpg");//독
			}
			apples.add(appleA);
		}
		return apples;
	}


}
