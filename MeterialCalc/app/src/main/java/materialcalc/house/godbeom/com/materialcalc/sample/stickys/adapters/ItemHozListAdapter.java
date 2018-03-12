package materialcalc.house.godbeom.com.materialcalc.sample.stickys.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.dto.HozImgListDTO;

/**
 * Created by BeomChul.Shin on 2017-12-21.
 * 리스트 내의 가로 스크롤 뷰 어뎁터.
 */

public class ItemHozListAdapter extends RecyclerView.Adapter<ItemHozListAdapter.ViewHolder>{


		private List<HozImgListDTO> hozListItems = null;
		private LayoutInflater mInflater;
		private ItemClickListener mClickListener;

		public ItemHozListAdapter(Context context, List<HozImgListDTO> items) {
			this.mInflater = LayoutInflater.from(context);
			this.hozListItems = items;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = mInflater.inflate(R.layout.item_hoz_item, parent, false);
			ViewHolder viewHolder = new ViewHolder(view);
			return viewHolder;
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			HozImgListDTO data= hozListItems.get(position);
			holder.myTextView.setText(data.getName());

			 Glide.with(holder.bmkThum.getContext())
					.load(data.getImgPath())
					 .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
					 .into(holder.bmkThum);
		}

		@Override
		public int getItemCount() {
			return hozListItems.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
			public View myView;
			public TextView myTextView;
			public ImageView bmkThum;

			public ViewHolder(View itemView) {
				super(itemView);
				myTextView =  itemView.findViewById(R.id.tvAnimalName);
				bmkThum = itemView.findViewById(R.id.bmkThum);
				itemView.setOnClickListener(this);
			}

			@Override
			public void onClick(View view) {
				if (mClickListener != null) {
					mClickListener.onItemClick(view, hozListItems.get(getAdapterPosition()) ,getAdapterPosition());
				}
			}
		}

	public void setHozListItems(List<HozImgListDTO> hozListItems) {
		this.hozListItems = hozListItems;
	}

	public void setClickListener(ItemClickListener itemClickListener) {
			this.mClickListener = itemClickListener;
		}

		public interface ItemClickListener {
			void onItemClick(View view, HozImgListDTO dataDTO,int position);
		}
}
