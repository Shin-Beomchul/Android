package develop.godbeom.com.myapplication.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import java.util.Collections;
import java.util.List;

import develop.godbeom.com.myapplication.Constant;
import develop.godbeom.com.myapplication.R;
import develop.godbeom.com.myapplication.loader.MyLoader;
/**
 * Created by BeomChul on 2018. 4. 14..
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {


	private List<String> urls = Collections.emptyList();
	private LayoutInflater mInflater;

	public ImageAdapter(Activity context, List<String> urls) {

		this.mInflater = LayoutInflater.from(context);
		this.urls = urls;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = mInflater.inflate(R.layout.item_simple, parent, false);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		String url = urls.get(position);

 		MyLoader.getInstance(holder.iv.getContext())
 				.load(Constant.baseDomain + url, holder.iv);
	}

	@Override
	public int getItemCount() {
		return urls.size();
	}


	public class ViewHolder extends RecyclerView.ViewHolder  {
		public View myView;
		public ImageView iv;

		public ViewHolder(View itemView) {
			super(itemView);
			iv = (ImageView) itemView.findViewById(R.id.iv);
		}


	}
}