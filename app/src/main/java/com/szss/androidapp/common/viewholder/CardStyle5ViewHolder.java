package com.szss.androidapp.common.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.szss.androidapp.R;
import com.szss.androidapp.base.WebActivity;
import com.szss.androidapp.common.model.CardStyle5Model;

/**
 * Created by wuwei on 2017/6/20.
 */

public class CardStyle5ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	private ImageView mImageView1;
	private ImageView mImageView2;
	private ImageView mImageView3;
	private ImageView mImageView4;


	public CardStyle5ViewHolder(View itemView) {
		super(itemView);
		mImageView1 = (ImageView) itemView.findViewById(R.id.card_style5_item_image1);
		mImageView2 = (ImageView) itemView.findViewById(R.id.card_style5_item_image2);
		mImageView3 = (ImageView) itemView.findViewById(R.id.card_style5_item_image3);
		mImageView4 = (ImageView) itemView.findViewById(R.id.card_style5_item_image4);
	}

	public void bindData(CardStyle5Model cardStyle5Model) {
		mImageView1.setOnClickListener(this);
		mImageView2.setOnClickListener(this);
		mImageView3.setOnClickListener(this);
		mImageView4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		WebActivity.runActivity(itemView.getContext(), "精选", "http://s15.sinaimg.cn/middle/6cfc958ft9846c34bee2e&690");
	}
}

