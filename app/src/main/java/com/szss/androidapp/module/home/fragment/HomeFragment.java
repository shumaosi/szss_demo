package com.szss.androidapp.module.home.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.szss.androidapp.R;
import com.szss.androidapp.base.EntryActivity;
import com.szss.androidapp.base.WebActivity;
import com.szss.androidapp.module.barcode.activity.CaptureActivity;
import com.szss.androidapp.module.profile.fragment.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuwei on 2017/6/14.
 */

public class HomeFragment extends Fragment {

	private ViewPager mViewPager;
	private TabLayout mTabLayout;
	private ImageView mBarcodeIcon;
	private FloatingActionButton mFloatingActionButton;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_fragment, container, false);
		mViewPager = (ViewPager) view.findViewById(R.id.main_viewpager);
		mTabLayout = (TabLayout) view.findViewById(R.id.main_tab_layout);
		mBarcodeIcon = (ImageView) view.findViewById(R.id.barcodeicon);
		mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewPager.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
		mTabLayout.setupWithViewPager(mViewPager);
		mViewPager.setCurrentItem(0);
		fabAction();
		barcodeAction();
	}

	private void barcodeAction() {
		mBarcodeIcon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
					if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
						Intent intent = new Intent(HomeFragment.this.getContext(), CaptureActivity.class);
						startActivityForResult(intent, EntryActivity.REQUEST_CODE);
					} else {
						ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, ProfileFragment.REQUEST_PERMISSION_CAMERA);
					}
				}
			}
		});
	}

	private void fabAction() {
		mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebActivity.runActivity(HomeFragment.this.getActivity(), "商城", WebActivity.CHANNEL_MALL, "http://m.3songshu.com/#/tabs/index");
			}
		});
	}

	private static class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

		private List<Pair<String, Fragment>> mFragmentList;

		public HomeViewPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragmentList = new ArrayList<>();
			mFragmentList.add(new Pair<String, Fragment>("松鼠新闻", HomeNewsFragment.newInstance()));
			mFragmentList.add(new Pair<String, Fragment>("图片", HomeImageFragment.newInstance()));
			mFragmentList.add(new Pair<String, Fragment>("聊天", HomeChatFragment.newInstance()));
//			mFragmentList.add(new Pair<String, Fragment>("OkRx2", HomeListFragment.newInstance()));
//			mFragmentList.add(new Pair<String, Fragment>("OkDownload", HomeListFragment.newInstance()));
//			mFragmentList.add(new Pair<String, Fragment>("OkUpload", HomeListFragment.newInstance()));
		}

		@Override
		public Fragment getItem(int position) {
			return mFragmentList.get(position).second;
		}

		@Override
		public int getCount() {
			return mFragmentList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mFragmentList.get(position).first;
		}
	}
}
