package com.szss.androidapp.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;

import com.szss.androidapp.R;
import com.szss.androidapp.application.SzssApp;
import com.szss.androidapp.guide.LauncherActivity;
import com.szss.androidapp.haojia.HaojiaFragment;
import com.szss.androidapp.haowen.HaowenFragment;
import com.szss.androidapp.home.fragment.HomeFragment;
import com.szss.androidapp.home.fragment.HomeImageFragment;
import com.szss.androidapp.profile.fragment.ProfileFragment;
import com.szss.androidapp.util.PrefsUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class EntryActivity extends BaseActivity {

	public static final int NAVIGATION_TAB_HOME = 0;
	public static final int NAVIGATION_TAB_HAOJIA = 1;
	public static final int NAVIGATION_TAB_HAOWU = 2;
	public static final int NAVIGATION_TAB_HAOWEN = 3;
	public static final int NAVIGATION_TAB_PROFILE = 4;

	private ArrayList<Fragment> mFragments;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_home:
					showFragment(NAVIGATION_TAB_HOME);
					return true;
				case R.id.navigation_haojia:
					showFragment(NAVIGATION_TAB_HAOJIA);
					return true;
				case R.id.navigation_haowu:
					showFragment(NAVIGATION_TAB_HAOWU);
					return true;
				case R.id.navigation_haowen:
					showFragment(NAVIGATION_TAB_HAOWEN);
					return true;
				case R.id.navigation_profile:
					showFragment(NAVIGATION_TAB_PROFILE);
					return true;
			}
			return false;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		enterApp();
	}

	private void enterApp() {
		SharedPreferences sharedPreferences = PrefsUtil.getInstance();
		if (sharedPreferences.getBoolean(PrefsUtil.FIRST_OPEN_APP, true)) {
			sharedPreferences.edit().putBoolean(PrefsUtil.FIRST_OPEN_APP, false).apply();
			Intent intent = new Intent(this, LauncherActivity.class);
			startActivity(intent);
			finish();
		} else {
			Observable.timer(2, TimeUnit.SECONDS)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Consumer<Long>() {
						@Override
						public void accept(@io.reactivex.annotations.NonNull Long aLong) throws Exception {
							setContentView(R.layout.activity_main);
							initSystemBarTint();
							initFragments();
							BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
							disableShiftMode(navigation);
							navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
							navigation.setSelectedItemId(getLastVisitNavigationId());
						}
					});
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}


	private int getLastVisitNavigationId() {
		int lastvisit = PrefsUtil.getInstance().getInt(PrefsUtil.ENTERPAGE_LASTVISIT, NAVIGATION_TAB_HOME);
		if (lastvisit == NAVIGATION_TAB_HOME) {
			return R.id.navigation_home;
		}
		if (lastvisit == NAVIGATION_TAB_HAOJIA) {
			return R.id.navigation_haojia;
		}
		if (lastvisit == NAVIGATION_TAB_HAOWU) {
			return R.id.navigation_haowu;
		}
		if (lastvisit == NAVIGATION_TAB_HAOWEN) {
			return R.id.navigation_haowen;
		}
		return R.id.navigation_profile;
	}

	private void initFragments() {
		mFragments = new ArrayList<>();
		mFragments.add(NAVIGATION_TAB_HOME, new HomeFragment());
		mFragments.add(NAVIGATION_TAB_HAOJIA, new HaojiaFragment());
		mFragments.add(NAVIGATION_TAB_HAOWU, new HomeImageFragment());
		mFragments.add(NAVIGATION_TAB_HAOWEN, new HaowenFragment());
		mFragments.add(NAVIGATION_TAB_PROFILE, new ProfileFragment());
	}

	private void showFragment(int index) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		for (int i = 0; i < mFragments.size(); i++) {
			Fragment fragment = mFragments.get(i);
			if (i == index) {
				if (fragment.isAdded()) {
					fragmentTransaction.show(fragment);
				} else {
					fragmentTransaction.add(R.id.fragment_container, fragment);
				}
			} else {
				if (fragment.isAdded()) {
					fragmentTransaction.hide(fragment);
				}
			}
		}
		fragmentTransaction.commit();
		saveLastVistIndex(index);
	}

	private void saveLastVistIndex(int index) {
		SharedPreferences sharedPreferences = PrefsUtil.getInstance();
		sharedPreferences.edit().putInt(PrefsUtil.ENTERPAGE_LASTVISIT, index).apply();
	}

	@Override
	protected boolean translucentStatusBar() {
		return false;
	}

	// 固定bottom navigationmenu
	public static void disableShiftMode(BottomNavigationView view) {
		BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
		try {
			Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
			shiftingMode.setAccessible(true);
			shiftingMode.setBoolean(menuView, false);
			shiftingMode.setAccessible(false);
			for (int i = 0; i < menuView.getChildCount(); i++) {
				BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
				//noinspection RestrictedApi
				item.setShiftingMode(false);
				// set once again checked value, so view will be updated
				//noinspection RestrictedApi
				item.setChecked(item.getItemData().isChecked());
			}
		} catch (Exception e) {
			Toast.makeText(SzssApp.getInstance().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}

}
