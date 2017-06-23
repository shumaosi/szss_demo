package com.szss.androidapp.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;

/**
 * Created by wuwei on 2017/6/22.
 */

public class BaseSwiperefreshFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	protected RecyclerView mRecyclerView;
	protected SwipeRefreshLayout mSwipeRefreshLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.swiperefresh_fragment, container, false);
		mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh_layout);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.refreshLayout_recyclerview);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
	}

	@Override
	public void onRefresh() {

	}

	protected void setRefreshing(final boolean refreshing) {
		mSwipeRefreshLayout.postDelayed(new Runnable() {
			@Override
			public void run() {
				mSwipeRefreshLayout.setRefreshing(refreshing);
			}
		}, 3000);
	}

}
