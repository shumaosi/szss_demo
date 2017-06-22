package com.szss.androidapp.common.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.szss.androidapp.R;
import com.szss.androidapp.base.WebActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/20.
 */

public class BannerViewHolder extends RecyclerView.ViewHolder {

	private Banner mBanner;
	private Context mContext;

	public BannerViewHolder(View itemView) {
		super(itemView);
		mContext = itemView.getContext();
		mBanner = (Banner) itemView.findViewById(R.id.banner_item);
	}

	public void bindData() {
		mBanner.setImageLoader(new ImageLoader() {
			private static final long serialVersionUID = -6104274223673485961L;

			@Override
			public void displayImage(Context context, Object path, ImageView imageView) {
//				imageView.setImageResource((int) path);
				Glide.with(mContext).load((String) path).error(R.drawable.image55).into(imageView);
			}
		});

//		ArrayList<Integer> imageList = new ArrayList<>();
//		imageList.add(R.drawable.banner_photo_1);
//		imageList.add(R.drawable.banner_photo_2);
//		imageList.add(R.drawable.banner_photo_3);
//		mBanner.setImages(imageList);

		final ArrayList<String> imageUrlList = new ArrayList<>();
		imageUrlList.add("http://my.3zitie.cn/shop/2015/2015316/img/2015031609284231824.jpg");
		imageUrlList.add("http://s15.sinaimg.cn/middle/6cfc958ft9846c34bee2e&690");
		imageUrlList.add("http://img.cheshi-img.com/product/1_1024/p/35300/35372/52e0a9823af8d.jpg");
		mBanner.setImages(imageUrlList);

		mBanner.setDelayTime(4 * 1000);
		mBanner.start();
		mBanner.setOnBannerListener(new OnBannerListener() {
			@Override
			public void OnBannerClick(int position) {
				String url = imageUrlList.get(position);
				WebActivity.runActivity(itemView.getContext(), "广告", url);
			}
		});
	}

}
