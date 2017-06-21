package com.szss.androidapp.profile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;
import com.szss.androidapp.model.ProfileItem;
import com.szss.androidapp.profile.adapter.ProfileAdapter;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/16.
 */

public class ProfileFragment extends Fragment implements ProfileAdapter.ProfileActionClickListener {

	private RecyclerView mRecyclerView;
	private ProfileAdapter mProfileAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.profile_view, container, false);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.profile_view_recyclerview);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initRecyclerView();
		initData();
	}

	private void initData() {
		ArrayList<ProfileItem> list = new ArrayList<>();
		list.add(new ProfileItem(ProfileItem.ItemType.profileInfo));
		list.add(new ProfileItem(ProfileItem.ItemType.space));
		list.add(new ProfileItem(ProfileItem.ItemType.favorable));
		list.add(new ProfileItem(ProfileItem.ItemType.space));
		list.add(new ProfileItem(ProfileItem.ItemType.setting));
		list.add(new ProfileItem(ProfileItem.ItemType.notification));
		list.add(new ProfileItem(ProfileItem.ItemType.message));
		list.add(new ProfileItem(ProfileItem.ItemType.setting));
		list.add(new ProfileItem(ProfileItem.ItemType.notification));
		list.add(new ProfileItem(ProfileItem.ItemType.message));
		list.add(new ProfileItem(ProfileItem.ItemType.setting));
		list.add(new ProfileItem(ProfileItem.ItemType.notification));
		list.add(new ProfileItem(ProfileItem.ItemType.message));
		list.add(new ProfileItem(ProfileItem.ItemType.setting));
		list.add(new ProfileItem(ProfileItem.ItemType.notification));
		list.add(new ProfileItem(ProfileItem.ItemType.message));
		list.add(new ProfileItem(ProfileItem.ItemType.setting));
		list.add(new ProfileItem(ProfileItem.ItemType.notification));
		list.add(new ProfileItem(ProfileItem.ItemType.message));

		mProfileAdapter.addData(list);
	}


	private void initRecyclerView() {
		mProfileAdapter = new ProfileAdapter();
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setAdapter(mProfileAdapter);
	}

	@Override
	public void profileActionClick(int viewType) {
		switch (viewType) {
			case ProfileAdapter.TYPE_PROFILE_INFO:
				break;
			case ProfileAdapter.TYPE_SETTING:
				break;
			case ProfileAdapter.TYPE_NOTIFICATION:
				break;
			case ProfileAdapter.TYPE_MESSAGE:
				break;
		}
	}
}
