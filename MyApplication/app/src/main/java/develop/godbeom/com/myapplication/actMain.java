package develop.godbeom.com.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import develop.godbeom.com.myapplication.adapters.ImageAdapter;
import develop.godbeom.com.myapplication.cache.MemoryLruCache;
import develop.godbeom.com.myapplication.util.RetainFragment;

public class actMain extends AppCompatActivity  {

	RecyclerView recyclerView;
	TextView tvUiObject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		recyclerView = findViewById(R.id.recyclerView);
		tvUiObject = findViewById(R.id.tvUiObject);


		Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate); //무한회전 (Ui Thread 상태 확인)
		tvUiObject.setAnimation(animation);


		final List<String> result = new  ArrayList<String>();
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

		ImageAdapter adapter = new ImageAdapter(this,result);

		recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
		recyclerView.setAdapter(adapter);
		adapter.notifyDataSetChanged();







		new Thread(() -> {
			//getURL
			/*int page = 1;
			int catid = 8;
			try {
				Document document = Jsoup.connect(Constant.baseDomain + "/picture-library/searchresults.aspx?catid=" + catid + "&Page=" + page).get();
				final List<String> result = document.select(".gallery-wrap .picture")
						.stream()
						.map(element -> element.attr("src"))
						.collect(Collectors.toList());*/


		}).start();

	}




}
