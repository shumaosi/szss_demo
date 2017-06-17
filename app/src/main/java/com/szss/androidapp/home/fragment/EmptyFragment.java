package com.szss.androidapp.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szss.androidapp.R;

/**
 * Created by wuwei on 2017/5/17.
 */

public class EmptyFragment extends Fragment {

	private String mContent = "正在施工中";

	public EmptyFragment() {
	}

	public EmptyFragment(String content) {
		mContent = content;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.empty_fragment, container, false);
		((TextView) v.findViewById(R.id.textview)).setText(mContent);
		return v;
	}
}
