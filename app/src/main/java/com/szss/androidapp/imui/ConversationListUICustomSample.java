package com.szss.androidapp.imui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListUI;

/**
 * Created by wuwei on 2017/6/29.
 */

public class ConversationListUICustomSample extends IMConversationListUI {

	public ConversationListUICustomSample(Pointcut pointcut) {
		super(pointcut);
	}

	@Override
	public View getCustomConversationListTitle(Fragment fragment, Context context, LayoutInflater inflater) {
		return super.getCustomConversationListTitle(fragment, context, inflater);
	}

	@Override
	public boolean needHideTitleView(Fragment fragment) {
		return false;
	}
}
