package develop.godbeom.com.myapplication.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2018-04-12.
 */

public class BitmapCallableTask implements Callable<Bitmap> {
	String imgUrl =null;
	public BitmapCallableTask(String imageUrl){
		imgUrl =imageUrl;
	}
	@Override
	public Bitmap call() throws Exception {
		Bitmap bitmap = null;
		URL url = new URL(imgUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(false);
		conn.connect();
		bitmap = BitmapFactory.decodeStream(conn.getInputStream());
		return bitmap;
	}
}
