package com.szss.androidapp.haojia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;
import com.szss.androidapp.base.BaseRecycleViewAdapter;
import com.szss.androidapp.common.viewholder.CardStyle3ViewHolder;

/**
 * Created by wuwei on 2017/6/22.
 */

public class HaojiaFaxianAdapter extends BaseRecycleViewAdapter {


	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_style3_item, parent, false);
		return new CardStyle3ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
	}

}
