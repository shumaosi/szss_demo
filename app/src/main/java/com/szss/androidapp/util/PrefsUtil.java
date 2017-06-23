package com.szss.androidapp.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.szss.androidapp.application.SzssApp;

/**
 * Created by wuwei on 2017/6/23.
 */

public class PrefsUtil {

	public static final String ENTERPAGE_LASTVISIT = "enterpage_lastvisit";
	public static final String HOMEPAGE_LASTVISIT = "homepage_lastvisit";
	public static final String HAOJIAPAGE_LASTVISIT = "haojiapage_lastvisit";


	public static SharedPreferences getInstance() {
		return PreferenceManager.getDefaultSharedPreferences(SzssApp.getInstance().getApplicationContext());
	}

}
