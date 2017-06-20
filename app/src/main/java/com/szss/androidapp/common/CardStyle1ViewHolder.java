package com.szss.androidapp.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.szss.androidapp.view.RoundedImageView;

/**
 * Created by wuwei on 2017/6/20.
 */

public class CardStyle1ViewHolder extends RecyclerView.ViewHolder {

	private RoundedImageView mCardPhoto;
	private TextView mCardContent;
	private RoundedImageView mCardUserIcon;
	private TextView mCardUserName;
	private TextView mCardReplyNumber;

	public CardStyle1ViewHolder(View itemView) {
		super(itemView);
	}

}
