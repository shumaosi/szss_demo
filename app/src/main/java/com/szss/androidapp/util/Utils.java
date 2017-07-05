package com.szss.androidapp.util;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuwei on 2017/6/15.
 */

public class Utils {

	public static boolean checkForumUsernameFormat(String username) {
		Pattern pattern;
		String username_forum_expression = "[A-Za-z0-9_\\.\\s]{3,32}$";
		pattern = Pattern.compile(username_forum_expression);
		Matcher mc = pattern.matcher(username);
		return mc.matches();
	}

	public static boolean checkEmailFormat(String email) {
		String email_expression = "\\w+([-+.]\\w+)*@\\w+([-+.]\\w+)*\\.\\w+([-+.]\\w+)*";
		Pattern pattern = Pattern.compile(email_expression);
		Matcher mc = pattern.matcher(email);
		return mc.matches();
	}

	public static Point getScreenSize(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point out = new Point();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			display.getSize(out);
		} else {
			int width = display.getWidth();
			int height = display.getHeight();
			out.set(width, height);
		}
		return out;
	}

	public static String getDeviceType(Context context) {
		try {
			return URLEncoder.encode(Build.MODEL, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return URLEncoder.encode(Build.MODEL);
		}
	}

	// region -------- isEmpty --------

	public static boolean isAllEmpty(String... strs) {
		if (strs == null || strs.length == 0) {
			return true;
		}

		for (String str : strs) {
			if (!isEmpty(str)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isEmpty(CharSequence str) {
		return (str == null) || (str.length() == 0);
	}

	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(byte[] bytes) {
		return (bytes == null) || (bytes.length == 0);
	}

	public static boolean isEmpty(Uri uri) {
		return (uri == null) || (uri.toString().length() == 0);
	}

	public static boolean isEmpty(int i) {
		return i == 0;
	}

	public static boolean isEmpty(long i) {
		return i == 0;
	}

	public static boolean isEmpty(Integer i) {
		return (i == null) || (i.equals(0));
	}

	public static boolean isEmpty(View v) {
		return v == null;
	}

	public static boolean isEmpty(List array) {
		return array == null || array.size() == 0;
	}

	public static boolean isEmpty(HashMap array) {
		return array == null || array.size() == 0;
	}

	// endregion


	// 拼接字符串

	public static String spliceString(ArrayList<String> list) {
		if (list == null || list.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder("");
		boolean isFirst = true;
		for (String string : list) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(",");
			}
			sb.append(string);
		}

		return sb.toString();
	}

	public static String splice(List<? extends Spliceable> list) {
		if (Utils.isEmpty(list)) {
			return "";
		}
		StringBuilder sb = new StringBuilder("");
		ListIterator<? extends Spliceable> i = list.listIterator();
		try {
			while (i.hasNext()) {
				Spliceable s = i.next();
				if (s != null && !Utils.isEmpty(s.getSpliceString())) {
					if (sb.length() > 0) {
						sb.append(",");
					}
					sb.append(s.getSpliceString());
				}
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}

	/**
	 * hide the soft keyboard
	 */
	public static void hideSoftKeyb(Context context, View inputText) {
		try {
			if (context != null && inputText != null) {
				InputMethodManager imm = (InputMethodManager) context
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(inputText.getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * show the soft keyboard
	 */
	public static void showSoftKeyb(Context context, View inputText) {
		try {
			if (context != null && inputText != null) {
				InputMethodManager imm = (InputMethodManager) context
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(inputText, InputMethodManager.SHOW_IMPLICIT);
			}
		} catch (Exception e) {

		}
	}

}
