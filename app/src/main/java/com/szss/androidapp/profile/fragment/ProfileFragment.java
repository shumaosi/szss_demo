package com.szss.androidapp.profile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.szss.androidapp.base.BaseFragment;
import com.szss.androidapp.model.ProfileItem;
import com.szss.androidapp.profile.adapter.ProfileAdapter;
import com.szss.androidapp.rxbus.RefreshProfileEvent;
import com.szss.androidapp.rxbus.RxBus;
import com.szss.androidapp.rxbus.UpdateProfileIconEvent;
import com.szss.androidapp.util.GlideImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by wuwei on 2017/6/16.
 */

public class ProfileFragment extends BaseFragment implements ProfileAdapter.ProfileActionClickListener {

	private RecyclerView mRecyclerView;
	private ProfileAdapter mProfileAdapter;

	private ImageView mImageView;

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
		addSubscribe();
	}

	private void addSubscribe() {
		RxBus.getInstance().subscribe(Object.class, new Consumer() {
			@Override
			public void accept(@NonNull Object o) throws Exception {
				if (o instanceof UpdateProfileIconEvent) {
					UpdateProfileIconEvent updateProfileIconEvent = (UpdateProfileIconEvent) o;
					Glide.with(getContext()).load(updateProfileIconEvent.getImageItem().path).error(R.mipmap.ic_launcher).into(mImageView);
				} else if (o instanceof RefreshProfileEvent) {
					refreshData();
				}
			}
		});
	}

	private void refreshData() {
		if (mProfileAdapter != null) {
			mProfileAdapter.getDataList().clear();
			ArrayList<ProfileItem> list = new ArrayList<>();
			list.add(new ProfileItem(ProfileItem.ItemType.profileInfo));
			list.add(new ProfileItem(ProfileItem.ItemType.space));
			list.add(new ProfileItem(ProfileItem.ItemType.profileInfo));
			list.add(new ProfileItem(ProfileItem.ItemType.space));
			list.add(new ProfileItem(ProfileItem.ItemType.profileInfo));
			list.add(new ProfileItem(ProfileItem.ItemType.space));
			list.add(new ProfileItem(ProfileItem.ItemType.notification));
			mProfileAdapter.addData(list);
		}
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

	@Override
	public void profileActionClick(ActionName actionName, View view) {
		switch (actionName) {
			case ProfilePage_UserInfoIconClickAction:
				mImageView = (ImageView) view;
				ImagePicker imagePicker = ImagePicker.getInstance();
				imagePicker.setImageLoader(new GlideImageLoaderUtil());
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
				RxBus.getInstance().send(new UpdateProfileIconEvent(imageItem));
			}
		}
	}
}
