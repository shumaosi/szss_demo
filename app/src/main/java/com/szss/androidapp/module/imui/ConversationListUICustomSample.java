package com.szss.androidapp.module.imui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListUI;
import com.szss.androidapp.R;

/**
 * Created by wuwei on 2017/6/29.
 */

public class ConversationListUICustomSample extends IMConversationListUI {

	public ConversationListUICustomSample(Pointcut pointcut) {
		super(pointcut);
	}

	@Override
	public View getCustomConversationListTitle(Fragment fragment, Context context, LayoutInflater inflater) {
		RelativeLayout relativeLayout = new RelativeLayout(context);
		relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
		View view = inflater.inflate(R.layout.conversationtitle_view, relativeLayout, false);
		return view;
	}

	@Override
	public boolean needHideTitleView(Fragment fragment) {
		return false;
	}

}
