package com.szss.androidapp.module.imui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMContactsOperation;
import com.alibaba.mobileim.contact.IYWContact;
import com.szss.androidapp.util.IMUtil;

/**
 * 联系人界面业务的定制点(根据需要实现相应的接口来达到定制联系人界面的业务)，不设置则使用云旺默认的实现
 * 调用方设置的回调，必须继承BaseAdvice 根据不同的需求实现 不同的 开放的 Advice
 * com.alibaba.mobileim.aop.pointcuts包下开放了不同的Advice.通过实现多个接口，组合成对不同的界面的定制
 * 需要在application中将这个Advice绑定。设置以下代码
 * AdviceBinder.bindAdvice(PointCutEnum.CONTACTS_OP_POINTCUT, ContactsOperationCustomSample.class);
 *
 * @author shuheng
 */
public class ContactsOperationCustomSample extends IMContactsOperation {

	private String TAG = ContactsOperationCustomSample.class.getSimpleName();

	public ContactsOperationCustomSample(Pointcut pointcut) {
		super(pointcut);
	}

	/**
	 * 是否同步联系人在线状态
	 *
	 * @param fragment 联系人页面fragment
	 * @param context  联系人页面context
	 * @return
	 */
	@Override
	public boolean enableSyncContactOnlineStatus(Fragment fragment, Context context) {
		return true;
	}

	/**
	 * 定制点击事件
	 *
	 * @param fragment
	 * @param contact
	 * @return true: 使用用户自定义点击事件；false：使用默认点击事件
	 */
	@Override
	public boolean onListItemClick(Fragment fragment, IYWContact contact) {
		Intent intent = IMUtil.mIMKit.getChattingActivityIntent(contact.getUserId());
		fragment.getActivity().startActivity(intent);
		return true;
	}

	/**
	 * 定制长按事件
	 *
	 * @param fragment
	 * @param contact
	 * @return true: 使用用户自定义长按事件；false：使用默认长按事件
	 */
	@Override
	public boolean onListItemLongClick(Fragment fragment, final IYWContact contact) {
		return true;
	}
}
