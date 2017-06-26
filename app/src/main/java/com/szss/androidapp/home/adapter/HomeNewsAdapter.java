package com.szss.androidapp.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;
import com.szss.androidapp.base.BaseRecycleViewAdapter;
import com.szss.androidapp.common.viewholder.LoadingViewHolder;
import com.szss.androidapp.common.viewholder.NewsViewHolder;
import com.szss.androidapp.model.NewsModel;

/**
 * Created by wuwei on 2017/6/15.
 */

public class HomeNewsAdapter extends BaseRecycleViewAdapter {

	public static final int TYPE_NEWS = 1;
	public static final int TYPE_LOADING = 2;

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		if (TYPE_NEWS == viewType) {
			View newsView = layoutInflater.inflate(R.layout.item_news, parent, false);
			return new NewsViewHolder(newsView);
		} else {
			View loadView = layoutInflater.inflate(R.layout.small_loading, parent, false);
			return new LoadingViewHolder(loadView);
		}
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
		if (TYPE_NEWS == getItemViewType(position)) {
			final NewsModel newsModel = (NewsModel) getItem(position);
			NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
			newsViewHolder.bindData(newsModel);
		}
	}

	@Override
	public int getItemViewType(int position) {
		Object object = getDataList().get(position);
		if (object instanceof NewsModel) {
			return TYPE_NEWS;
		} else {
			return TYPE_LOADING;
		}
	}

}
