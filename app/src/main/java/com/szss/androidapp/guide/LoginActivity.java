package com.szss.androidapp.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.szss.androidapp.R;
import com.szss.androidapp.base.EntryActivity;
import com.szss.androidapp.base.BaseActivity;

/**
 * Created by wuwei on 2017/6/15.
 */

public class LoginActivity extends BaseActivity {

	private Toolbar toolbar;
	private View mWeixinBtn;
	private View mQQBtn;
	private EditText mUserNameEt;
	private EditText mPasswordEt;
	private Button mLoginBtn;
	private TextView mForgetPasswordBtn;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initToolBar();
		wenxinLoginEvent();
		qqLoginEvent();
		loginEvent();
		forGetPasswordEvent();
	}

	@Override
	protected boolean translucentStatusBar() {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (android.R.id.home == item.getItemId()) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initView() {
		setContentView(R.layout.login_view);
		mWeixinBtn = findViewById(R.id.wexinlogin_layout);
		mQQBtn = findViewById(R.id.qqlogin_layout);
		mLoginBtn = (Button) findViewById(R.id.login_begin_button);
		mUserNameEt = (EditText) findViewById(R.id.login_username_et);
		mPasswordEt = (EditText) findViewById(R.id.login_password_et);
		mForgetPasswordBtn = (TextView) findViewById(R.id.forget_password_tv);
	}

	private void initToolBar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar mActionBar = getSupportActionBar();
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(false);
		toolbar.setTitle(R.string.login);
		toolbar.setBackgroundResource(R.color.transparent);
	}

	private void loginEvent() {
//		if (mUserNameEt.getText().length() == 0 || mPasswordEt.getText().length() == 0) {
//			mLoginBtn.setEnabled(false);
//		} else {
//			mLoginBtn.setEnabled(true);
//		}
		mLoginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, EntryActivity.class);
				startActivity(intent);
			}
		});
	}

	private void qqLoginEvent() {
		mQQBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	private void wenxinLoginEvent() {
		mWeixinBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	private void forGetPasswordEvent() {
		mForgetPasswordBtn.setText(Html.fromHtml("<u>" + this.getString(R.string.forget_password)
				+ "</u>"));
		mForgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

}
