package com.szss.androidapp.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.szss.androidapp.R;
import com.szss.androidapp.base.BaseActivity;

/**
 * Created by wuwei on 2017/6/15.
 */

public class EntryActivity extends BaseActivity {

//	private TextView mLoginView;
	private TextView mRegistView;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entry_view);
//		mLoginView = (TextView) findViewById(R.id.login_view);
		mRegistView = (TextView) findViewById(R.id.regist_view);
		loginEvent();
	}

	@Override
	protected boolean translucentStatusBar() {
		return true;
	}

	private void loginEvent() {
		mRegistView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(EntryActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});
	}

	private void registEvent() {

	}

}
