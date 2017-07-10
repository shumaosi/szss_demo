package com.szss.androidapp.module.home.fragment;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;
import com.szss.androidapp.module.home.adapter.HomeListAdapter;

/**
 * Created by wuwei on 2017/5/17.
 */

public class HomeListFragment extends Fragment {

	private Activity mActivity;
	private RecyclerView mRecyclerView;
	private RecyclerView.LayoutManager mLayoutManager;
	private HomeListAdapter mHomeListAdapter;

	public HomeListFragment() {
	}

	public static HomeListFragment newInstance() {
		HomeListFragment homeListFragment = new HomeListFragment();
		return homeListFragment;
	}

	public static HomeListFragment newInstance(int page) {
		Bundle bundle = new Bundle();
		bundle.putInt("page", page);
		HomeListFragment f = new HomeListFragment();
		f.setArguments(bundle);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.main_fragment, container, false);
		mRecyclerView = (RecyclerView) v.findViewById(R.id.main_recyclerview);
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity = getActivity();
		init();
	}

	private void init() {
		mLayoutManager = new LinearLayoutManager(mActivity);
		mHomeListAdapter = new HomeListAdapter();
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setAdapter(mHomeListAdapter);
		mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
									   RecyclerView.State state) {
				outRect.bottom = 16;
			}

			@Override
			public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
				ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.gray_b5b5b5));
				int count = parent.getChildCount();
				for (int i = 0; i < count; i++) {
					View v = parent.getChildAt(i);
					colorDrawable.setBounds(v.getLeft(), v.getBottom(), v.getRight(),
							v.getBottom() + 16);
					colorDrawable.draw(c);
				}
			}
		});
	}
}
