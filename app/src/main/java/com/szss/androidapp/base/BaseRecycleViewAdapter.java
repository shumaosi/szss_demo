package com.szss.androidapp.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.szss.androidapp.model.ProfileItemModel;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/20.
 */

public class BaseRecycleViewAdapter extends RecyclerView.Adapter {

	protected ArrayList<Object> mData;


	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return getDataList().size();
	}

	public ArrayList getDataList() {
		if (mData == null) {
			mData = new ArrayList<>();
		}
		return mData;
	}

	public void addData(ArrayList list) {
		getDataList().addAll(list);
		notifyDataSetChanged();
	}

}
