package com.szss.androidapp.module.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.mobileim.conversation.EServiceContact;
import com.szss.androidapp.R;
import com.szss.androidapp.module.imui.ContactListActivity;
import com.szss.androidapp.util.IMUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuwei on 2017/6/14.
 */

public class HomeChatFragment extends Fragment {

	//	private EditText mNameEditText;
//	private EditText mPasswordEditText;
	private Button mContactList;
	private Button mConversationList;
	private Button mCustomerSupport;

	private ViewPager mViewPager;
	private TabLayout mTabLayout;
	private TextView mToolbarText;
	private FloatingActionButton mFloatingActionButton;

	public static HomeChatFragment newInstance() {
		HomeChatFragment homeChatFragment = new HomeChatFragment();
		return homeChatFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_fragment, container, false);
		mViewPager = (ViewPager) view.findViewById(R.id.main_viewpager);
		mTabLayout = (TabLayout) view.findViewById(R.id.main_tab_layout);
		mToolbarText = (TextView) view.findViewById(R.id.main_toolbar_text);
		mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
		mFloatingActionButton.setVisibility(View.GONE);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mToolbarText.setText("聊天");
		mViewPager.setAdapter(new ChatViewPagerAdapter(getChildFragmentManager()));
		mTabLayout.setupWithViewPager(mViewPager);
		mViewPager.setCurrentItem(0);
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
				Intent intent = IMUtil.mIMKit.getConversationActivityIntent();
				startActivity(intent);
			}
		});
	}

	private void customerSupportAction() {
		mCustomerSupport.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

//				final String target = "shu2"; //消息接收者ID
//				final String appkey = IMUtil.IM_APP_KEY; //消息接收者appKey
//				Intent intent = IMUtil.mIMKit.getChattingActivityIntent(target, appkey);
//				startActivity(intent);

				//userid是客服帐号，第一个参数是客户帐号，第二个是组ID，如果没有，传0
				EServiceContact contact = new EServiceContact("面皮大师001", 0);
				//如果需要发给指定的客服帐号，不需要Server进行分流(默认Server会分流)，请调用EServiceContact对象
				//的setNeedByPass方法，参数为false。
				//contact.setNeedByPass(false);
				Intent intent = IMUtil.mIMKit.getChattingActivityIntent(contact);
				startActivity(intent);
			}
		});
	}

	private static class ChatViewPagerAdapter extends FragmentStatePagerAdapter {

		private List<Pair<String, Fragment>> mFragmentList;

		private ChatViewPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragmentList = new ArrayList<>();
			mFragmentList.add(new Pair<String, Fragment>("联系人列表", IMUtil.mIMKit.getContactsFragment()));
			mFragmentList.add(new Pair<String, Fragment>("最近联系人", IMUtil.mIMKit.getConversationFragment()));
		}

		@Override
		public Fragment getItem(int position) {
			return mFragmentList.get(position).second;
		}

		@Override
		public int getCount() {
			return mFragmentList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mFragmentList.get(position).first;
		}
	}

}
