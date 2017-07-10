/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.szss.androidapp.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.szss.androidapp.R;
import com.szss.androidapp.common.view.ProgressWebView;
import com.szss.androidapp.util.Utils;

/**
 * Created by wuwei on 2017/6/20.
 */

public class WebActivity extends BaseActivity {

	public final static String URL = "url";
	public final static String TITLE = "title";
	public final static String CHANNEL = "channel";
	public final static String CHANNEL_MALL = "channel_mall";

	private Toolbar toolbar;
	private ProgressWebView webView;

	public static void runActivity(Context context, String title, String url) {
		Intent intent = new Intent(context, WebActivity.class);
		intent.putExtra(URL, url);
		intent.putExtra(TITLE, title);
		context.startActivity(intent);
	}

	public static void runActivity(Context context, String title, String channel, String url) {
		Intent intent = new Intent(context, WebActivity.class);
		intent.putExtra(URL, url);
		intent.putExtra(TITLE, title);
		intent.putExtra(CHANNEL, channel);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		webView = (ProgressWebView) findViewById(R.id.webView);
		String url = getIntent().getStringExtra(URL);
		String title = getIntent().getStringExtra(TITLE);
		String channel = getIntent().getStringExtra(CHANNEL);
		if (Utils.isEmpty(channel) || !CHANNEL_MALL.equals(channel)) {
			initToolBar(toolbar, true, title);
		} else {
			toolbar.setVisibility(View.GONE);
		}
//		final MaterialDialog materialDialog = new MaterialDialog.Builder(this)
//				.title("松鼠商城")
//				.content("正在加载")
//				.progress(true, 0)
//				.show();
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setCookie(url, "");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
//				materialDialog.dismiss();
			}
		});
		webView.loadUrl(url);
//		Slidr.attach(this);
	}

	@Override
	protected void onDestroy() {
		if (webView != null) {
			webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
			webView.clearHistory();
			((ViewGroup) webView.getParent()).removeView(webView);
			webView.destroy();
			webView = null;
		}
		super.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (android.R.id.home == item.getItemId()) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
