package com.szss.androidapp.haowen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;
import com.szss.androidapp.model.HaowenItemModel;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/20.
 */

public class HaowenFragment extends Fragment {

	private RecyclerView mRecyclerView;
	private HaowenAdapter mHaowenAdapter;
	private LinearLayoutManager mLinearLayoutManager;

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
		initData();
	}

	private void initRecyclerView() {
		mHaowenAdapter = new HaowenAdapter();
		mLinearLayoutManager = new LinearLayoutManager(getContext());
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		mRecyclerView.setAdapter(mHaowenAdapter);
	}

	private void initData() {
		ArrayList<HaowenItemModel> list = new ArrayList<>();
		list.add(new HaowenItemModel(HaowenItemModel.ItemType.banner));
		list.add(new HaowenItemModel(HaowenItemModel.ItemType.huati));
		list.add(new HaowenItemModel(HaowenItemModel.ItemType.space));
		list.add(new HaowenItemModel(HaowenItemModel.ItemType.title));
		list.add(new HaowenItemModel(HaowenItemModel.ItemType.imageText));
		list.add(new HaowenItemModel(HaowenItemModel.ItemType.imageText));
		list.add(new HaowenItemModel(HaowenItemModel.ItemType.imageText));
		list.add(new HaowenItemModel(HaowenItemModel.ItemType.imageText));
		mHaowenAdapter.addData(list);
	}

}
