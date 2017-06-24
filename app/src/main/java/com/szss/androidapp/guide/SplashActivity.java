package com.szss.androidapp.guide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.szss.androidapp.R;
import com.szss.androidapp.base.EntryActivity;
import com.szss.androidapp.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

	private Timer t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				h.post(new Runnable() {
					@Override
					public void run() {
						count();
					}
				});
			}
		};

		t = new Timer();
		t.schedule(task, 1000L, 1000L);

		findViewById(R.id.clickable).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				next();
			}
		});
	}

	int count = 3;
	Handler h = new Handler();

	private void count() {
		count--;
		((TextView) findViewById(R.id.textView2)).setText(String.valueOf(count));
		if (count == 0) {
			t.cancel();
			next();
		}
	}

	private void next() {
		if (SplashActivity.this.isFinishing()) {
			return;
		}

		startActivity(new Intent(SplashActivity.this, EntryActivity.class));
		finish();
	}

	@Override
	protected boolean translucentStatusBar() {
		return true;
	}
}
