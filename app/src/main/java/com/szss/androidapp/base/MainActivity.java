package com.szss.androidapp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.szss.androidapp.R;
import com.szss.androidapp.home.fragment.HomeFragment;
import com.szss.androidapp.home.fragment.HomeImageFragment;
import com.szss.androidapp.profile.fragment.ProfileFragment;

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
				case R.id.navigation_dashboard:
					showFragment(1);
					return true;
				case R.id.navigation_profile:
					showFragment(2);
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
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		initFragments();
		showFragment(0);
	}

	private void initFragments() {
		mFragments = new ArrayList<>();
		mFragments.add(new HomeFragment());
		mFragments.add(new HomeImageFragment());
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
}
