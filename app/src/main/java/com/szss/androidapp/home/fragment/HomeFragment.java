package com.szss.androidapp.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuwei on 2017/6/14.
 */

public class HomeFragment extends Fragment {

	private ViewPager mViewPager;
	private TabLayout mTabLayout;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_fragment, container, false);
		mViewPager = (ViewPager) view.findViewById(R.id.main_viewpager);
		mTabLayout = (TabLayout) view.findViewById(R.id.main_tab_layout);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewPager.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
		mTabLayout.setupWithViewPager(mViewPager);
		mViewPager.setCurrentItem(0);
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
