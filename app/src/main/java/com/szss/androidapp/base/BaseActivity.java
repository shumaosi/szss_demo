package com.szss.androidapp.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lzy.imagepicker.view.SystemBarTintManager;
import com.szss.androidapp.R;
import com.szss.androidapp.util.CrashHandler;

/**
 * Created by wuwei on 2017/6/14.
 */

public class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this));
		initSystemBarTint();
	}

	/**
	 * 设置状态栏颜色
	 */
	protected void initSystemBarTint() {
		Window window = getWindow();
		if (translucentStatusBar()) {
			// 设置状态栏全透明
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
				window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
				window.setStatusBarColor(Color.TRANSPARENT);
			} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			}
			return;
		}
		// 沉浸式状态栏
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			//5.0以上使用原生方法
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(setStatusBarColor());
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			//4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			tintManager.setStatusBarTintEnabled(true);
			tintManager.setStatusBarTintColor(setStatusBarColor());
		}
	}

	/**
	 * 获取主题色
	 */
	public int getColorPrimary() {
		TypedValue typedValue = new TypedValue();
		getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
		return typedValue.data;
	}

	/**
	 * 获取深主题色
	 */
	public int getDarkColorPrimary() {
		TypedValue typedValue = new TypedValue();
		getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
		return typedValue.data;
	}

	/**
	 * 子类可以重写改变状态栏颜色
	 */
	protected int setStatusBarColor() {
		return getDarkColorPrimary();
	}

	/**
	 * 子类可以重写决定是否使用透明状态栏
	 */
	protected boolean translucentStatusBar() {
		return false;
	}

	/**
	 * 初始化 Toolbar
	 */
	public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
		toolbar.setTitle(title);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
	}

}
