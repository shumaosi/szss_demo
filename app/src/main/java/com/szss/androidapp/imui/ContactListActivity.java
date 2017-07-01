package com.szss.androidapp.imui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.szss.androidapp.R;
import com.szss.androidapp.base.BaseActivity;
import com.szss.androidapp.base.EntryActivity;

/**
 * Created by wuwei on 2017/6/29.
 */

public class ContactListActivity extends BaseActivity {

	private Fragment contactListFragment;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setContentView(R.layout.contactlist_activity_view);
		super.onCreate(savedInstanceState);
		showFragment();
	}

	private void showFragment() {
		contactListFragment = IMHelp.mIMKit.getContactsFragment();
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		if (contactListFragment.isAdded()) {
			fragmentTransaction.show(contactListFragment);
		} else {
			fragmentTransaction.add(R.id.fragment_container, contactListFragment);
		}
		fragmentTransaction.commit();
	}
}
