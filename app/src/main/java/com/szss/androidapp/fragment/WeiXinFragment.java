package com.szss.androidapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;

/**
 * Created by wuwei on 2017/6/14.
 */

public class WeiXinFragment extends Fragment {

	public static WeiXinFragment newInstance() {
		WeiXinFragment weiXinFragment = new WeiXinFragment();
		return weiXinFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.weixin_fragment, container, false);
		return view;
	}
}
