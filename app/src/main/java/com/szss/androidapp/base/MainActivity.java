package com.szss.androidapp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.szss.androidapp.R;
import com.szss.androidapp.haojia.HaojiaFragment;
import com.szss.androidapp.haowen.HaowenFragment;
import com.szss.androidapp.home.fragment.HomeFragment;
import com.szss.androidapp.home.fragment.HomeImageFragment;
import com.szss.androidapp.profile.fragment.ProfileFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

	private ArrayList<Fragment> mFragments;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_home:
					showFragment(0);
					return true;
				case R.id.navigation_haojia:
					showFragment(1);
					return true;
				case R.id.navigation_haowu:
					showFragment(2);
					return true;
				case R.id.navigation_haowen:
					showFragment(3);
					return true;
				case R.id.navigation_profile:
					showFragment(4);
					return true;
			}
			return false;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initSystemBarTint();
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		disableShiftMode(navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		initFragments();
		showFragment(0);
	}

	private void initFragments() {
		mFragments = new ArrayList<>();
		mFragments.add(new HomeFragment());
		mFragments.add(new HaojiaFragment());
		mFragments.add(new HomeImageFragment());
		mFragments.add(new HaowenFragment());
		mFragments.add(new ProfileFragment());
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
		} catch (NoSuchFieldException e) {
		} catch (IllegalAccessException e) {
		}
	}

}
