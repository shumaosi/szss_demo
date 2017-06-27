package com.szss.androidapp.haojia;

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
import com.szss.androidapp.base.BaseSwiperefreshFragment;
import com.szss.androidapp.common.model.CardStyle3Model;
import com.szss.androidapp.util.DensityUtil;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/22.
 */

public class HaojiaFaxianFragment extends BaseSwiperefreshFragment {

	private HaojiaFaxianAdapter mHaojiaFaxianAdapter;
	private GridLayoutManager mGridLayoutManager;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initRecylerView();
	}

	private void initRecylerView() {
		mHaojiaFaxianAdapter = new HaojiaFaxianAdapter();
		mGridLayoutManager = new GridLayoutManager(getActivity(), CardStyle3Model.ROW_NUMBER);
		final int spacing = DensityUtil.dip2px(getActivity(), CardStyle3Model.CARD_MARGING);
		mRecyclerView.setLayoutManager(mGridLayoutManager);
		mRecyclerView.setAdapter(mHaojiaFaxianAdapter);
		mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
				int position = parent.getChildAdapterPosition(view);
				outRect.top = spacing;
				switch (position % CardStyle3Model.ROW_NUMBER) {
					case 0:
						outRect.left = spacing;
						outRect.right = spacing / 2;
						break;
					case 1:
						outRect.left = spacing / 2;
						outRect.right = spacing;
						break;
				}
			}
		});
		ArrayList<CardStyle3Model> list = new ArrayList<>();
		list.add(new CardStyle3Model());
		list.add(new CardStyle3Model());
		list.add(new CardStyle3Model());
		list.add(new CardStyle3Model());
		list.add(new CardStyle3Model());
		mHaojiaFaxianAdapter.addData(list);
	}

	@Override
	public void onRefresh() {
		setRefreshing(false);
	}
}
