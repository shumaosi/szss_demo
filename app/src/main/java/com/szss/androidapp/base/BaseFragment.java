package com.szss.androidapp.base;

import android.support.v4.app.Fragment;

import com.szss.androidapp.rxbus.RxBus;

/**
 * Created by wuwei on 2017/6/23.
 */

public class BaseFragment extends Fragment {

	@Override
	public void onDestroy() {
		super.onDestroy();
		RxBus.getInstance().unSubscribe();
	}

}
