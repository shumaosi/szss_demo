package com.szss.androidapp.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.szss.androidapp.R;
import com.szss.androidapp.base.EntryActivity;

/**
 * Created by wuwei on 2017/6/17.
 */

public class LauncherFragment extends Fragment {

	private ImageView mImageView;
	private TextView mStartButton;
	private int mPage;

	public static LauncherFragment newInstance(int page) {
		LauncherFragment launcherFragment = new LauncherFragment();
		launcherFragment.mPage = page;
		return launcherFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.launcher_item_view, container, false);
		mImageView = (ImageView) view.findViewById(R.id.launcher_item_view_imageview);
		mStartButton = (TextView) view.findViewById(R.id.launcher_item_view_startview);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (mPage == 0) {
			mImageView.setImageResource(R.drawable.launch_page13);
		} else if (mPage == 1) {
			mImageView.setImageResource(R.drawable.launch_page12);
		} else if (mPage == 2) {
			mImageView.setImageResource(R.drawable.launch_page9);
		} else {
			mImageView.setImageResource(R.drawable.launch_page8);
			mStartButton.setVisibility(View.VISIBLE);
			mStartButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(LauncherFragment.this.getContext(), EntryActivity.class);
					startActivity(intent);
					getActivity().finish();
				}
			});
		}
	}
}
