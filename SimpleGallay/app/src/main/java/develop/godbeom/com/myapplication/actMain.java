package develop.godbeom.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import develop.godbeom.com.myapplication.adapters.ImageAdapter;
import develop.godbeom.com.myapplication.loader.onComplete;
import develop.godbeom.com.myapplication.loader.parallelism.ThreadSupplier;
/**
 * Created by BeomChul on 2018. 4. 14..
 */
public class actMain extends AppCompatActivity {

	RecyclerView recyclerView;
	TextView tvUiObject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		recyclerView = findViewById(R.id.recyclerView);
		tvUiObject = findViewById(R.id.tvUiObject);
		tvUiObject.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate)); // (Ui Thread 상태 확인)


		ImageAdapter adapter = new ImageAdapter(this, getUrls());
		recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
		recyclerView.setAdapter(adapter);
	}






	/**get Scroller (Not used)*/
	public void getUrls(onComplete<List<String>> complete) {
		ThreadSupplier.getInstance().backGroundPool().execute(() -> {
			int page = 1;
			int catid = 8;
			try {
				Document document = Jsoup.connect(Constant.baseDomain + "/picture-library/searchresults.aspx?catid=" + catid + "&Page=" + page).get();
				complete.result(document.select(".gallery-wrap .picture")
						.stream()
						.map(element -> element.attr("src"))
						.collect(Collectors.toList()));

			} catch (Exception e) {
				complete.result(null);
			}
		});
	}

	/*read Url*/
	public List<String> getUrls() {
		final List<String> result = new ArrayList<String>();
		result.add("/Images/Thumbnails/1387/138719.jpg");
		result.add("/Images/Thumbnails/1334/133451.jpg");
		result.add("/Images/Thumbnails/1334/133491.jpg");
		result.add("/Images/Thumbnails/1480/148062.jpg");
		result.add("/Images/Thumbnails/1461/146172.jpg");
		result.add("/Images/Thumbnails/1463/146322.jpg");
		result.add("/Images/Thumbnails/1473/147352.jpg");
		result.add("/Images/Thumbnails/1461/146192.jpg");
		result.add("/Images/Thumbnails/1465/146552.jpg");
		result.add("/Images/Thumbnails/1465/146522.jpg");
		result.add("/Images/Thumbnails/1462/146232.jpg");
		result.add("/Images/Thumbnails/1468/146807.jpg");
		result.add("/Images/Thumbnails/1473/147357.jpg");
		result.add("/Images/Thumbnails/1387/138719.jpg");
		result.add("/Images/Thumbnails/1334/133451.jpg");
		result.add("/Images/Thumbnails/1334/133491.jpg");
		result.add("/Images/Thumbnails/1480/148062.jpg");
		result.add("/Images/Thumbnails/1461/146172.jpg");
		result.add("/Images/Thumbnails/1463/146322.jpg");
		result.add("/Images/Thumbnails/1473/147352.jpg");
		result.add("/Images/Thumbnails/1461/146192.jpg");
		result.add("/Images/Thumbnails/1465/146552.jpg");
		result.add("/Images/Thumbnails/1465/146522.jpg");
		result.add("/Images/Thumbnails/1462/146232.jpg");
		result.add("/Images/Thumbnails/1468/146807.jpg");
		result.add("/Images/Thumbnails/1473/147357.jpg");
		result.add("/Images/Thumbnails/1387/138719.jpg");
		result.add("/Images/Thumbnails/1334/133451.jpg");
		result.add("/Images/Thumbnails/1334/133491.jpg");
		result.add("/Images/Thumbnails/1480/148062.jpg");
		result.add("/Images/Thumbnails/1461/146172.jpg");
		result.add("/Images/Thumbnails/1463/146322.jpg");
		result.add("/Images/Thumbnails/1473/147352.jpg");
		result.add("/Images/Thumbnails/1461/146192.jpg");
		result.add("/Images/Thumbnails/1465/146552.jpg");
		result.add("/Images/Thumbnails/1465/146522.jpg");
		result.add("/Images/Thumbnails/1462/146232.jpg");
		result.add("/Images/Thumbnails/1468/146807.jpg");
		result.add("/Images/Thumbnails/1473/147357.jpg");
		return result;
	}



	//getURL


}
