package com.szss.androidapp.module.haojia;

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
import android.widget.TextView;

import com.szss.androidapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuwei on 2017/6/21.
 */

public class HaojiaFragment extends Fragment {

	private ViewPager mViewPager;
	private TabLayout mTabLayout;
	private TextView mToolbarText;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_fragment, container, false);
		mViewPager = (ViewPager) view.findViewById(R.id.main_viewpager);
		mTabLayout = (TabLayout) view.findViewById(R.id.main_tab_layout);
		mToolbarText = (TextView) view.findViewById(R.id.main_toolbar_text);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mToolbarText.setText("好价");
		mViewPager.setAdapter(new HaojiaViewPagerAdapter(getChildFragmentManager()));
		mTabLayout.setupWithViewPager(mViewPager);
		mViewPager.setCurrentItem(0);

	}

	private static class HaojiaViewPagerAdapter extends FragmentStatePagerAdapter {

		private List<Pair<String, Fragment>> mFragmentList;

		private HaojiaViewPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragmentList = new ArrayList<>();
			mFragmentList.add(new Pair<String, Fragment>("精选", new HaojiaJinxuanFragment()));
			mFragmentList.add(new Pair<String, Fragment>("发现", new HaojiaFaxianFragment()));
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
