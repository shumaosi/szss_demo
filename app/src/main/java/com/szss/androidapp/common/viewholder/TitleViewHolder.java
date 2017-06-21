package com.szss.androidapp.common.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.szss.androidapp.R;
import com.szss.androidapp.common.model.TitleModel;

/**
 * Created by wuwei on 2017/6/20.
 */

public class TitleViewHolder extends RecyclerView.ViewHolder {

	private TextView mTitle;
	private TextView mMoreView;

	public TitleViewHolder(View itemView) {
		super(itemView);
		mTitle = (TextView) itemView.findViewById(R.id.title_item_text);
		mMoreView = (TextView) itemView.findViewById(R.id.title_item_more);
	}

	public void bindData(TitleModel titleModel) {
		mTitle.setText(titleModel.getTitle());
		mMoreView.setVisibility(titleModel.isShowMore() ? View.VISIBLE : View.GONE);
	}

}
