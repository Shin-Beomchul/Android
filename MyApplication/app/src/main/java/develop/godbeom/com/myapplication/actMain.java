package develop.godbeom.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import develop.godbeom.com.myapplication.adapters.ImageAdapter;

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


		/*ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> resultList = new ArrayList<>();

		Random random = new Random();

		for (int i=0; i<4; i++)
		{
			FactorialCalculatorCallable Callable  = new FactorialCalculatorCallable(random.nextInt(10));
			Future<Integer> result = executor.submit(Callable);
			resultList.add(result);
		}



		for(Future<Integer> future : resultList)
		{
			try
			{
				System.out.println("Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
			}
			catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
		}


		List<Future<Bitmap>> bitmapfutures = new ArrayList<Future<Bitmap>>();
		BitmapCallableTask CallableBitMap = new BitmapCallableTask("http://cfile6.uf.tistory.com/image/257E0B3655214FFD03A829");
		BitmapCallableTask CallableBitMap2 = new BitmapCallableTask("http://cfile6.uf.tistory.com/image/257E0B3655214FFD03A829");
		BitmapCallableTask CallableBitMap3 = new BitmapCallableTask("http://cfile6.uf.tistory.com/image/257E0B3655214FFD03A829");

		Future<Bitmap> bitmapFuture = executor.submit(CallableBitMap);
		bitmapfutures.add(bitmapFuture);


		for(Future<Bitmap> futureBitmap : bitmapfutures)
		{
			try
			{
				Log.i("FutureSS :: ", "Future result is - " + " - " + futureBitmap.get().getByteCount() + "; And Task done is " + futureBitmap.isDone());
			}
			catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
		}



		//shut down the executor service now
		executor.shutdown();*/


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



				ImageAdapter adapter = new ImageAdapter(getApplicationContext(),result);
				runOnUiThread(() -> {
					recyclerView.setLayoutManager(new GridLayoutManager(actMain.this,2));
					recyclerView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				});



			/*} catch (IOException e) {
				e.printStackTrace();
			}*/



			//URL To BitMap
			/*try {
				Bitmap bitmap = null;
				URL load = new URL(Constant.baseDomain + result.get(0));
				HttpURLConnection conn = (HttpURLConnection) load.openConnection();
				conn.setDoInput(true);
				conn.setDoOutput(false);
				conn.connect();
				bitmap = BitmapFactory.decodeStream(conn.getInputStream());
				Log.i("finished", "ok");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
*/

		}).start();

	}


	interface  view{
		void onloadDone(ArrayList<String> strs) ;
	}


}
