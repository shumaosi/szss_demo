package com.szss.androidapp.common.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.szss.androidapp.R;
import com.szss.androidapp.common.model.CardStyle2Model;
import com.szss.androidapp.common.view.RoundedImageView;

/**
 * Created by wuwei on 2017/6/20.
 */

public class CardStyle2ViewHolder extends RecyclerView.ViewHolder {

	private Context mContext;
	private RoundedImageView mCardPhoto;
	private TextView mCardTitle;
	private TextView mCardType;
	private TextView mCardReplyNumber;
	private TextView mCardViewNumber;

	public CardStyle2ViewHolder(View itemView) {
		super(itemView);
		mContext = itemView.getContext();
		mCardPhoto = (RoundedImageView) itemView.findViewById(R.id.card_style2_item_photo);
		mCardTitle = (TextView) itemView.findViewById(R.id.card_style2_item_title);
		mCardType = (TextView) itemView.findViewById(R.id.card_style2_item_topictype);
		mCardReplyNumber = (TextView) itemView.findViewById(R.id.card_style2_item_replynumber);
		mCardViewNumber = (TextView) itemView.findViewById(R.id.card_style2_item_viewnumber);
	}

	public void bindData(CardStyle2Model cardStyle2Model) {
		mCardTitle.setText(cardStyle2Model.getTitle());
		mCardType.setText(cardStyle2Model.getTopicType());
		mCardViewNumber.setText(cardStyle2Model.getViewNumber());
		mCardReplyNumber.setText(cardStyle2Model.getReplyNumber());
		Glide.with(mContext).load(cardStyle2Model.getPhotoUrl()).error(R.drawable.image66).into(mCardPhoto);
	}

}
