package com.szss.androidapp.module.setting;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;

import com.r0adkll.slidr.Slidr;
import com.szss.androidapp.R;
import com.szss.androidapp.base.BaseActivity;

/**
 * Created by wuwei on 2017/7/10.
 */

public class SettingActivity extends BaseActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout);
		initToolBar((Toolbar) findViewById(R.id.toolbar), true, getString(R.string.setting));
		showFragment();
		Slidr.attach(this);
	}

	@Override
	protected boolean translucentStatusBar() {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (android.R.id.home == item.getItemId()) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showFragment() {
		PreferenceFragment fragment = new SettingFragment();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.activity_layout_framelayout, fragment,
				String.valueOf(fragment.hashCode()));
		ft.commitAllowingStateLoss();
	}

}
