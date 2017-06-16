package com.szss.androidapp.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuwei on 2017/6/15.
 */

public class Utils {

	public static String username_forum_expression = "[A-Za-z0-9_\\.\\s]{3,32}$";
	public static String email_expression = "\\w+([-+.]\\w+)*@\\w+([-+.]\\w+)*\\.\\w+([-+.]\\w+)*";


	public static boolean checkForumUsernameFormat(String username) {
		Pattern pattern;
		pattern = Pattern.compile(username_forum_expression);
		Matcher mc = pattern.matcher(username);
		return mc.matches();
	}

	public static boolean checkEmailFormat(String email) {
		Pattern pattern = Pattern.compile(email_expression);
		Matcher mc = pattern.matcher(email);
		return mc.matches();
	}

	public static Point getScreenSize(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point out = new Point();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			display.getSize(out);
		}else{
			int width = display.getWidth();
			int height = display.getHeight();
			out.set(width, height);
		}
		return out;
	}

}
