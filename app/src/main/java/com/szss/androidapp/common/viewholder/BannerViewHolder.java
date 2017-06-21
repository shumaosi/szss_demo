package com.szss.androidapp.common.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.szss.androidapp.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/20.
 */

public class BannerViewHolder extends RecyclerView.ViewHolder {

	private Banner mBanner;

	public BannerViewHolder(View itemView) {
		super(itemView);
		mBanner = (Banner) itemView.findViewById(R.id.banner_item);
	}

	public void bindData() {
		mBanner.setImageLoader(new ImageLoader() {
			private static final long serialVersionUID = -6104274223673485961L;
			@Override
			public void displayImage(Context context, Object path, ImageView imageView) {
				imageView.setImageResource((int) path);
			}
		});
		ArrayList<Integer> imageList = new ArrayList<>();
		imageList.add(R.drawable.banner_photo_1);
		imageList.add(R.drawable.banner_photo_2);
		imageList.add(R.drawable.banner_photo_3);
		mBanner.setImages(imageList);
		mBanner.setDelayTime(4 * 1000);
		mBanner.start();
	}

}
