package com.szss.androidapp.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by wuwei on 2015/12/23.
 */
public abstract class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

	private int totalItemCount, lastInScreen;
	private int current_page = 1;

	private LinearLayoutManager mLinearLayoutManager;

	public RecyclerViewOnScrollListener(LinearLayoutManager linearLayoutManager) {
		this.mLinearLayoutManager = linearLayoutManager;
	}

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
		super.onScrolled(recyclerView, dx, dy);

		lastInScreen = mLinearLayoutManager.findFirstVisibleItemPosition() + mLinearLayoutManager.getChildCount();
		totalItemCount = mLinearLayoutManager.getItemCount();
		boolean shouldLoadingMore = lastInScreen != 0 && lastInScreen >= totalItemCount;

		if (shouldLoadingMore) {
			current_page++;
			onLoadMore(current_page);
		}
	}

	public abstract void onLoadMore(int current_page);

}
