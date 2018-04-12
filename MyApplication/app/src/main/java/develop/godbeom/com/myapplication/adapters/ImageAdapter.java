package develop.godbeom.com.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

import develop.godbeom.com.myapplication.Constant;
import develop.godbeom.com.myapplication.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {


	private List<String> urls = Collections.emptyList();
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	public ImageAdapter(Context context, List<String> animals) {
		this.mInflater = LayoutInflater.from(context);
		this.urls = animals;
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

		Glide.with(holder.iv.getContext())
				.load(Constant.baseDomain + url)
				.apply(new RequestOptions().placeholder(R.mipmap.ic_launcher_round))
				.into(holder.iv);


	}

	@Override
	public int getItemCount() {
		return urls.size();
	}






	public void setClickListener(ItemClickListener itemClickListener) {
		this.mClickListener = itemClickListener;
	}

	public interface ItemClickListener {
		void onItemClick(View view, String url, int position);
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		public View myView;
		public ImageView iv;

		public ViewHolder(View itemView) {
			super(itemView);
			iv = (ImageView) itemView.findViewById(R.id.iv);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			if (mClickListener != null)
				mClickListener.onItemClick(view, urls.get(getAdapterPosition()), getAdapterPosition());
		}
	}
}