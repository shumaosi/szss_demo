package com.szss.androidapp.common.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.szss.androidapp.R;
import com.szss.androidapp.base.WebActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
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
//		imageList.add(R.drawable.banner1);
//		imageList.add(R.drawable.banner5);
//		imageList.add(R.drawable.banner6);
//		mBanner.setImages(imageList);

		final ArrayList<String> imageUrlList = new ArrayList<>();
		imageUrlList.add("http://img.zcool.cn/community/018221574bf5fa6ac72525ae8d5a25.jpg@900w_1l_2o_100sh.jpg");
		imageUrlList.add("http://www.quanmama.com/AdminImageUpload/ueditor/image/20160229/6359236345532236118392105.png");
		imageUrlList.add("http://image.suning.cn/uimg/sop/commodity/298412517591554847189300_x.jpg");
		mBanner.setImages(imageUrlList);
		mBanner.setDelayTime(4 * 1000);
		mBanner.setIndicatorGravity(BannerConfig.RIGHT);
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
