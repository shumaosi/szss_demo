package com.szss.androidapp.module.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.szss.androidapp.R;
import com.szss.androidapp.base.BaseActivity;
import com.szss.androidapp.base.EntryActivity;
import com.szss.androidapp.module.imui.NotificationInitSampleHelper;
import com.szss.androidapp.util.DensityUtil;
import com.szss.androidapp.util.IMUtil;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/17.
 */

public class LauncherActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

	private ViewPager mViewPager;
	private ArrayList<Fragment> mFragments;
	private LauncherViewPagerAdapter mLauncherViewPagerAdapter;
	private ImageView[] tips;
	private int currentSelect;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.luancher_view);
		initIM();
		initTips();
		initViewPager();
	}

	@Override
	protected boolean translucentStatusBar() {
		return true;
	}

	private void initTips() {
		ViewGroup viewGroup = (ViewGroup) findViewById(R.id.luancher_view_tips_viewgroup);
		tips = new ImageView[4];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new ViewGroup.LayoutParams(DensityUtil.px2dip(this, 10), DensityUtil.px2dip(this, 10)));
			if (i == 0) {
				imageView.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
			tips[i] = imageView;
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = DensityUtil.px2dip(this, 30);
			layoutParams.rightMargin = DensityUtil.px2dip(this, 30);
			viewGroup.addView(imageView, layoutParams);
		}
	}

	private void initViewPager() {
		mFragments = new ArrayList<>();
		mFragments.add(LauncherFragment.newInstance(0));
		mFragments.add(LauncherFragment.newInstance(1));
		mFragments.add(LauncherFragment.newInstance(2));
		mFragments.add(LauncherFragment.newInstance(3));
		mViewPager = (ViewPager) findViewById(R.id.luancher_view_viewpager);
		mLauncherViewPagerAdapter = new LauncherViewPagerAdapter(getSupportFragmentManager(), mFragments);
		mViewPager.setAdapter(mLauncherViewPagerAdapter);
		mViewPager.setOffscreenPageLimit(2);
		mViewPager.setCurrentItem(0);
		mViewPager.addOnPageChangeListener(this);
	}

	private void setTipsBackground(int selectPosition) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectPosition) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		setTipsBackground(position);
		currentSelect = position;
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	private class LauncherViewPagerAdapter extends FragmentStatePagerAdapter {

		private ArrayList<Fragment> mFragments;

		public LauncherViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
			super(fm);
			this.mFragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}
	}

	public void openHome() {
		Intent intent = new Intent(this, EntryActivity.class);
		startActivity(intent);
//		finish();
		overridePendingTransition(R.anim.right_in, 0);
	}

	private void initIM() {
		//此实现不一定要放在Application onCreate中
		//此对象获取到后，保存为全局对象，供APP使用
		//此对象跟用户相关，如果切换了用户，需要重新获取
		IMUtil.mIMKit = YWAPI.getIMKitInstance(IMUtil.USERID, IMUtil.IM_APP_KEY);
		IYWLoginService loginService = IMUtil.mIMKit.getLoginService();
		YWLoginParam loginParam = YWLoginParam.createLoginParam(IMUtil.USERID, IMUtil.PASSWORD);
		loginService.login(loginParam, new IWxCallback() {

			@Override
			public void onSuccess(Object... arg0) {
				NotificationInitSampleHelper.init();

			}

			@Override
			public void onProgress(int arg0) {
				// TODO Auto-generated method stub
//				Toast.makeText(EntryActivity.this, arg0 + "", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onError(int errCode, String description) {
				//如果登录失败，errCode为错误码,description是错误的具体描述信息
//				Toast.makeText(EntryActivity.this, description + "", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
