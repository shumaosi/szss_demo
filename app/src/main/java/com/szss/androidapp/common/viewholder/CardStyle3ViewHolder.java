package com.szss.androidapp.common.viewholder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.szss.androidapp.R;
import com.szss.androidapp.base.WebActivity;
import com.szss.androidapp.common.model.CardStyle2Model;
import com.szss.androidapp.common.model.CardStyle3Model;
import com.szss.androidapp.common.view.RoundedImageView;
import com.szss.androidapp.util.DensityUtil;
import com.szss.androidapp.util.Utils;

/**
 * Created by wuwei on 2017/6/20.
 */

public class CardStyle3ViewHolder extends RecyclerView.ViewHolder {

	private Context mContext;
	private RoundedImageView mCardPhoto;
	private TextView mCardTitle;
	private TextView mCardContent;
	private TextView mCardViewNumber;
	private CardView mCardLayout;

	public CardStyle3ViewHolder(View itemView) {
		super(itemView);
		mContext = itemView.getContext();
		mCardLayout = (CardView) itemView.findViewById(R.id.card_style3_item_layout);
		mCardPhoto = (RoundedImageView) itemView.findViewById(R.id.card_style3_item_photo);
		mCardTitle = (TextView) itemView.findViewById(R.id.card_style3_item_title);
		mCardContent = (TextView) itemView.findViewById(R.id.card_style3_item_content);
		mCardViewNumber = (TextView) itemView.findViewById(R.id.card_style3_item_viewnumber);
//		int width = (Utils.getScreenSize(itemView.getContext()).x - (DensityUtil
//				.dip2px(itemView.getContext(), CardStyle3Model.CARD_MARGING) * 3)) / 2;
//		mCardLayout.setLayoutParams(new CardView.LayoutParams(Utils.getScreenSize(itemView.getContext()).x, CardView.LayoutParams.WRAP_CONTENT));
	}

	public void bindData(final CardStyle3Model cardStyle3Model) {
		mCardTitle.setText(cardStyle3Model.getTitle());
		mCardContent.setText(cardStyle3Model.getContent());
		mCardViewNumber.setText(cardStyle3Model.getViewNumber());
		Glide.with(mContext).load(cardStyle3Model.getPhotoUrl()).error(R.drawable.image66).into(mCardPhoto);
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebActivity.runActivity(itemView.getContext(), cardStyle3Model.getTitle(), cardStyle3Model.getPhotoUrl());
			}
		});
	}

}
