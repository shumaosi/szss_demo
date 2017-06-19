package com.szss.androidapp.util;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.szss.androidapp.R;

/**
 * Created by wuwei on 2017/6/15.
 */

public class ThemeUtil {

	/**
	 * Set ActionBar(include Toolbar & SupportActionBar)'s background color
	 */
	public static void setActionBarStyle(Activity activity) {
		Drawable drawable;
		drawable = new ColorDrawable(activity.getResources().getColor(R.color.orange_FF6B16));
		if (drawable == null) {
			return;
		}
		if (activity instanceof AppCompatActivity) {
			ActionBar bar = ((AppCompatActivity) activity).getSupportActionBar();
			if (bar != null) {
				bar.setBackgroundDrawable(drawable);
			}
		} else {
			android.app.ActionBar bar = activity.getActionBar();
			if (bar != null) {
				bar.setBackgroundDrawable(drawable);
			}
		}
	}

}
