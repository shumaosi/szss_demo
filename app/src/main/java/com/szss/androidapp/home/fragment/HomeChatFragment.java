package com.szss.androidapp.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.mobileim.conversation.EServiceContact;
import com.szss.androidapp.R;
import com.szss.androidapp.base.EntryActivity;
import com.szss.androidapp.imui.ContactListActivity;
import com.szss.androidapp.imui.IMHelp;

/**
 * Created by wuwei on 2017/6/14.
 */

public class HomeChatFragment extends Fragment {

	//	private EditText mNameEditText;
//	private EditText mPasswordEditText;
	private Button mContactList;
	private Button mConversationList;
	private Button mCustomerSupport;

	public static HomeChatFragment newInstance() {
		HomeChatFragment homeChatFragment = new HomeChatFragment();
		return homeChatFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.chat_fragment, container, false);
//		mNameEditText = (EditText) view.findViewById(R.id.chat_fragment_username);
//		mPasswordEditText = (EditText) view.findViewById(R.id.chat_fragment_password);
		mContactList = (Button) view.findViewById(R.id.chat_fragment_contactlist);
		mConversationList = (Button) view.findViewById(R.id.chat_fragment_conversationlist);
		mCustomerSupport = (Button) view.findViewById(R.id.chat_fragment_customersupport);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		contactListAction();
		conversationListAction();
		customerSupportAction();
	}

	private void contactListAction() {
		mContactList.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(HomeChatFragment.this.getContext(), ContactListActivity.class));
			}
		});
	}

	private void conversationListAction() {
		mConversationList.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = IMHelp.mIMKit.getConversationActivityIntent();
				startActivity(intent);
			}
		});
	}

	private void customerSupportAction() {
		mCustomerSupport.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

//				final String target = "shu2"; //消息接收者ID
//				final String appkey = IMHelp.IM_APP_KEY; //消息接收者appKey
//				Intent intent = IMHelp.mIMKit.getChattingActivityIntent(target, appkey);
//				startActivity(intent);

				//userid是客服帐号，第一个参数是客户帐号，第二个是组ID，如果没有，传0
				EServiceContact contact = new EServiceContact("面皮大师001", 0);
				//如果需要发给指定的客服帐号，不需要Server进行分流(默认Server会分流)，请调用EServiceContact对象
				//的setNeedByPass方法，参数为false。
				//contact.setNeedByPass(false);
				Intent intent = IMHelp.mIMKit.getChattingActivityIntent(contact);
				startActivity(intent);
			}
		});
	}

}
