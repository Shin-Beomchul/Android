package materialcalc.house.godbeom.com.materialcalc.sample.utill;

import java.util.ArrayList;
import java.util.Random;

import materialcalc.house.godbeom.com.materialcalc.sample.section.dtos.SectionDTO;

/**
 * Created by Administrator on 2018-03-15.
 */

public class DummyFat {
	private static final Random RANDOM = new Random();
	public static ArrayList<SectionDTO> createDummyData(int cnt) {
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


	public static ArrayList<SectionDTO> createDummyTabData(int cnt) {
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


	public static String getRandomStringNumber() {

		return String.valueOf(RANDOM.nextInt(100000));
	}
}
