package develop.godbeom.com.myapplication.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

class DownloadFilesTask extends AsyncTask<String, Void, Bitmap> {
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Bitmap doInBackground(String... urls) {

		try {
			Bitmap bitmap = null;
			URL url = new URL(urls[0]);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(false);
			conn.connect();
			bitmap = BitmapFactory.decodeStream(conn.getInputStream());

//		ThumbnailUtils.extractThumbnail(bitmap, 30, 30);

			return bitmap;
		} catch (Exception e) {
		}

		return null;
	}


	@Override
	protected void onPostExecute(Bitmap result) {
	}



}


