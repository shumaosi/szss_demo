package com.szss.androidapp.imui;

import android.app.Application;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.aop.AdviceBinder;
import com.alibaba.mobileim.aop.PointCutEnum;
import com.alibaba.mobileim.ui.chat.widget.YWSmilyMgr;
import com.alibaba.wxlib.util.SysUtil;

/**
 * Created by wuwei on 2017/6/29.
 */

public class IMHelp {

	//	public static String IM_APP_KEY = "23015524";
//	public static String USERID = "testpro1";
//	public static String PASSWORD = "taobao1234";
	public static String IM_APP_KEY = "24526749";
	public static String USERID = "maosi";
	public static String PASSWORD = "123456";
	public static YWIMKit mIMKit;


	public static void initIMSDK(Application application) {
		AdviceBinder.bindAdvice(PointCutEnum.CHATTING_FRAGMENT_UI_POINTCUT, ChattingUICustomSample.class);
		AdviceBinder.bindAdvice(PointCutEnum.CONVERSATION_FRAGMENT_UI_POINTCUT, ConversationListUICustomSample.class);
		AdviceBinder.bindAdvice(PointCutEnum.CONTACTS_OP_POINTCUT, ContactsOperationCustomSample.class);
		AdviceBinder.bindAdvice(PointCutEnum.NOTIFICATION_POINTCUT, NotificationInitSampleHelper.class);
		//必须首先执行这部分代码, 如果在":TCMSSevice"进程中，无需进行云旺（OpenIM）和app业务的初始化，以节省内存;
		//添加自定义表情的初始化
		YWSmilyMgr.setSmilyInitNotify(new YWSmilyMgr.SmilyInitNotify() {
			@Override
			public void onDefaultSmilyInitOk() {
				SmilyCustomSample.init();
			}
		});
		SysUtil.setApplication(application);
		if (SysUtil.isTCMSServiceProcess(application)) {
			return;
		}
		//第一个参数是Application Context
		//这里的APP_KEY即应用创建时申请的APP_KEY，同时初始化必须是在主进程中
		if (SysUtil.isMainProcess()) {
			YWAPI.init(application, IM_APP_KEY);
		}
	}

}
