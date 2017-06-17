package com.szss.androidapp.home.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;
import com.szss.androidapp.home.adapter.HomeImageAdapter;
import com.szss.androidapp.util.DensityUtil;

/**
 * Created by wuwei on 2017/6/14.
 */

public class HomeImageFragment extends Fragment {

	private RecyclerView mRecyclerView;

	public static HomeImageFragment newInstance() {
		HomeImageFragment homeImageFragment = new HomeImageFragment();
		return homeImageFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_fragment, container, false);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initRecyclerView();
	}

	private void initRecyclerView() {
		final HomeImageAdapter homeImageAdapter = new HomeImageAdapter();
		GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
		final int spacing = DensityUtil.dip2px(getActivity(), 4);
		mRecyclerView.setLayoutManager(gridLayoutManager);
		mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
				int position = parent.getChildAdapterPosition(view);
				outRect.top = spacing;
				switch (position % 3) {
					case 0:
						outRect.left = spacing;
						outRect.right = spacing / 2;
						break;
					case 1:
						outRect.left = spacing / 2;
						outRect.right = spacing / 2;
						break;
					case 2:
						outRect.left = spacing / 2;
						outRect.right = spacing;
						break;
				}
			}
		});
		mRecyclerView.setAdapter(homeImageAdapter);
	}

}
