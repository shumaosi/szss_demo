package com.szss.androidapp.common.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.szss.androidapp.R;
import com.szss.androidapp.common.model.CardStyle1Model;
import com.szss.androidapp.common.view.RoundedImageView;

/**
 * Created by wuwei on 2017/6/20.
 */

public class CardStyle1ViewHolder extends RecyclerView.ViewHolder {

	private Context mContext;
	private RoundedImageView mCardPhoto;
	private TextView mCardTitle;
	private RoundedImageView mCardUserIcon;
	private TextView mCardUserName;
	private TextView mCardReplyNumber;
	private TextView mCardViewNumber;

	public CardStyle1ViewHolder(View itemView) {
		super(itemView);
		mContext = itemView.getContext();
		mCardPhoto = (RoundedImageView) itemView.findViewById(R.id.card_style2_item_photo);
		mCardTitle = (TextView) itemView.findViewById(R.id.card_style1_item_title);
		mCardUserIcon = (RoundedImageView) itemView.findViewById(R.id.card_style1_item_usericon);
		mCardUserName = (TextView) itemView.findViewById(R.id.card_style1_item_username);
		mCardReplyNumber = (TextView) itemView.findViewById(R.id.card_style1_item_replynumber);
		mCardViewNumber = (TextView) itemView.findViewById(R.id.card_style1_item_viewnumber);
	}

	public void bindData(CardStyle1Model cardStyle1Model) {
		mCardTitle.setText(cardStyle1Model.getTitle());
		mCardUserName.setText(cardStyle1Model.getUserName());
		mCardReplyNumber.setText(cardStyle1Model.getReplyNumber());
		mCardViewNumber.setText(cardStyle1Model.getViewNumber());
		Glide.with(mContext).load(cardStyle1Model.getPhotoUrl()).error(R.drawable.image66).into(mCardPhoto);
		Glide.with(mContext).load(cardStyle1Model.getUserIconUrl()).error(R.drawable.image66).into(mCardUserIcon);
	}

}
