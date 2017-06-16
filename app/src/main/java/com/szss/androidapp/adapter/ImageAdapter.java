package com.szss.androidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.szss.androidapp.R;
import com.szss.androidapp.fragment.ImageFragment;
import com.szss.androidapp.util.DensityUtil;
import com.szss.androidapp.util.Utils;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/14.
 */

public class ImageAdapter extends RecyclerView.Adapter {

	private ArrayList<Integer> imageRes;

	public ImageAdapter() {
		imageRes = new ArrayList<>();
		imageRes.add(R.drawable.image10);
		imageRes.add(R.drawable.image11);
		imageRes.add(R.drawable.image12);
		imageRes.add(R.drawable.image13);
		imageRes.add(R.drawable.image14);
		imageRes.add(R.drawable.image15);
		imageRes.add(R.drawable.image16);
		imageRes.add(R.drawable.image17);
		imageRes.add(R.drawable.image1);
		imageRes.add(R.drawable.image2);
		imageRes.add(R.drawable.image3);
		imageRes.add(R.drawable.image4);
		imageRes.add(R.drawable.image10);
		imageRes.add(R.drawable.image11);
		imageRes.add(R.drawable.image12);
		imageRes.add(R.drawable.image13);
		imageRes.add(R.drawable.image14);
		imageRes.add(R.drawable.image15);
		imageRes.add(R.drawable.image16);
		imageRes.add(R.drawable.image17);
		imageRes.add(R.drawable.image1);
		imageRes.add(R.drawable.image2);
		imageRes.add(R.drawable.image3);
		imageRes.add(R.drawable.image4);
		imageRes.add(R.drawable.image10);
		imageRes.add(R.drawable.image11);
		imageRes.add(R.drawable.image12);
		imageRes.add(R.drawable.image13);
		imageRes.add(R.drawable.image14);
		imageRes.add(R.drawable.image15);
		imageRes.add(R.drawable.image16);
		imageRes.add(R.drawable.image17);
		imageRes.add(R.drawable.image1);
		imageRes.add(R.drawable.image2);
		imageRes.add(R.drawable.image3);
		imageRes.add(R.drawable.image4);
		imageRes.add(R.drawable.image10);
		imageRes.add(R.drawable.image11);
		imageRes.add(R.drawable.image12);
		imageRes.add(R.drawable.image13);
		imageRes.add(R.drawable.image14);
		imageRes.add(R.drawable.image15);
		imageRes.add(R.drawable.image16);
		imageRes.add(R.drawable.image17);
		imageRes.add(R.drawable.image1);
		imageRes.add(R.drawable.image2);
		imageRes.add(R.drawable.image3);
		imageRes.add(R.drawable.image4);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
		RecyclerView.ViewHolder viewHolder = new ImageViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		int res = imageRes.get(position);
		((ImageViewHolder) holder).mImageView.setImageResource(res);
	}

	@Override
	public int getItemCount() {
		return imageRes.size();
	}

	private static class ImageViewHolder extends RecyclerView.ViewHolder {

		private ImageView mImageView;

		public ImageViewHolder(View itemView) {
			super(itemView);
			int width = (Utils.getScreenSize(itemView.getContext()).x - (DensityUtil
					.dip2px(itemView.getContext(), 4) * 4) )/ 3;
			mImageView = (ImageView) itemView.findViewById(R.id.imageitemview);
			mImageView.getLayoutParams().height = width;
			mImageView.getLayoutParams().width = width;
		}
	}

}
