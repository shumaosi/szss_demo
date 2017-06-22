package com.szss.androidapp.profile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.szss.androidapp.R;
import com.szss.androidapp.action.ActionName;
import com.szss.androidapp.model.ProfileItem;
import com.szss.androidapp.profile.adapter.ProfileAdapter;
import com.szss.androidapp.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.szss.androidapp.action.ActionName.ProfilePage_UserInfoIconClickAction;

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
		mProfileAdapter.setProfileActionClickListener(this);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setAdapter(mProfileAdapter);
	}

	private ImageView mImageView;

	@Override
	public void profileActionClick(ActionName actionName, View view) {
		switch (actionName) {
			case ProfilePage_UserInfoIconClickAction:
				mImageView = (ImageView) view;
				ImagePicker imagePicker = ImagePicker.getInstance();
				imagePicker.setImageLoader(new GlideImageLoader());
				imagePicker.setShowCamera(true);
				imagePicker.setSelectLimit(1);
				imagePicker.setCrop(false);
				Intent intent = new Intent(getActivity(), ImageGridActivity.class);
				startActivityForResult(intent, 100);
				break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
			if (data != null && requestCode == 100) {
				//noinspection unchecked
				List<ImageItem> images = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
				ImageItem imageItem = images.get(0);
				Glide.with(getContext()).load(imageItem.path).error(R.mipmap.ic_launcher).into(mImageView);
			}
		}
	}
}
