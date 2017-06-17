package com.szss.androidapp.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.szss.androidapp.base.BaseActivity;

import java.util.List;

/**
 * Created by wuwei on 2017/6/17.
 */

public class LauncherActivity extends BaseActivity {

	private ViewPager mViewPager;
	private List<Fragment> mFragments;
	private LauncherViewPagerAdapter mLauncherViewPagerAdapter;
	private ImageView[] tips;
	private int currentSelect;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected boolean translucentStatusBar() {
		return true;
	}

	private void initTips() {
		tips = new ImageView[3];
//		for ()
	}

	private class LauncherViewPagerAdapter extends FragmentStatePagerAdapter {

		public LauncherViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return null;
		}

		@Override
		public int getCount() {
			return 0;
		}
	}

}
