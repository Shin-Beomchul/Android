package develop.godbeom.com.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import develop.godbeom.com.myapplication.adapters.ImageAdapter;
import develop.godbeom.com.myapplication.loader.BitmapCallableTask;
import develop.godbeom.com.myapplication.loader.FactorialCalculatorCallable;

public class actMain extends AppCompatActivity  {

	RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		recyclerView = findViewById(R.id.recyclerView);



		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
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



		BitmapCallableTask CallableBitMap = new BitmapCallableTask("http://cfile6.uf.tistory.com/image/257E0B3655214FFD03A829");
		List<Future<Bitmap>> bitmapfutures = new ArrayList<Future<Bitmap>>();
		Future<Bitmap> bitmapFuture = executor.submit(CallableBitMap);
		bitmapfutures.add(bitmapFuture);


		for(Future<Bitmap> futureBitmap : bitmapfutures)
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



		//shut down the executor service now
		executor.shutdown();


		new Thread(() -> {

			//getURL

			int page = 1;
			int catid = 8;
			try {
				Document document = Jsoup.connect(Constant.baseDomain + "/picture-library/searchresults.aspx?catid=" + catid + "&Page=" + page).get();
				final List<String> result = document.select(".gallery-wrap .picture")
						.stream()
						.map(element -> element.attr("src"))
						.collect(Collectors.toList());


				ImageAdapter adapter = new ImageAdapter(getApplicationContext(),result);
				runOnUiThread(() -> {
					recyclerView.setLayoutManager(new GridLayoutManager(actMain.this,2));
					recyclerView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				});



			} catch (IOException e) {
				e.printStackTrace();
			}



			//URL To BitMap
			/*try {
				Bitmap bitmap = null;
				URL url = new URL(Constant.baseDomain + result.get(0));
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
