package com.szss.androidapp.util;


import android.net.Uri;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

public class ParserUtil {

	public static final String DEF_STRING_VAL = "";
	public static final Integer DEF_INTEGER_VAL = 0;
	public static final Double DEF_DOUBLE_VAL = 0.0d;
	public static final Boolean DEF_BOOLEAN_VAL = false;
	public static final Float DEF_FLOAT_VAL = 0.0f;

	public static String optString(Object oriVal) {
		return optString(oriVal, DEF_STRING_VAL);
	}

	public static String optString(Object oriVal, String defVal) {
		try {

			if (oriVal == null) {
				return defVal;
			}

			if (oriVal instanceof String) {
				return (String) oriVal;
			}

			if (oriVal instanceof byte[]) {
				return bytes2String((byte[]) oriVal, defVal);
			}

			if (oriVal instanceof Object[]) {
				return objectArray2String((Object[]) oriVal, defVal);
			}

			return oriVal.toString();

		} catch (Exception e) {
			return defVal;
		}
	}

	public static Float optFloat(Object oriVal){
		return optFloat(oriVal,DEF_FLOAT_VAL);
	}

	public static Float optFloat(Object oriVal, Float defVal){
		try {

			if (oriVal == null) {
				return defVal;
			}

			if (oriVal instanceof Integer) {
				return ((Integer) oriVal).floatValue();
			}

			if (oriVal instanceof Number) {
				return ((Number) oriVal).floatValue();
			}

			if (oriVal instanceof Boolean) {
				return ((Boolean) oriVal) ? 1.0f : 0.0f;
			}

			String tmpStr = "";
			if (oriVal instanceof byte[]) {
				tmpStr = bytes2String((byte[]) oriVal, "");
			} else if (oriVal instanceof String) {
				tmpStr = (String) oriVal;
			}

			if (!isEmpty(tmpStr)) {
				Float result;
				try {
					result = Float.valueOf(tmpStr);
				} catch (NumberFormatException e) {
					result = defVal;
				}

				return result;
			}

			return defVal;

		} catch (Exception e) {
			return defVal;
		}
	}

	public static Double optDouble(Object oriVal){
		return optDouble(oriVal,DEF_DOUBLE_VAL);
	}

	public static Double optDouble(Object oriVal, Double defVal){
		try {

			if (oriVal == null) {
				return defVal;
			}

			if (oriVal instanceof Integer) {
				return ((Integer) oriVal).doubleValue();
			}

			if (oriVal instanceof Number) {
				return ((Number) oriVal).doubleValue();
			}

			if (oriVal instanceof Boolean) {
				return ((Boolean) oriVal) ? 1.0d : 0.0d;
			}

			String tmpStr = "";
			if (oriVal instanceof byte[]) {
				tmpStr = bytes2String((byte[]) oriVal, "");
			} else if (oriVal instanceof String) {
				tmpStr = (String) oriVal;
			}

			if (!isEmpty(tmpStr)) {
				Double result;
				try {
					result = Double.valueOf(tmpStr);
				} catch (NumberFormatException e) {
					result = defVal;
				}

				return result;
			}

			return defVal;

		} catch (Exception e) {
			return defVal;
		}
	}

	public static Integer optInteger(Object oriVal) {
		return optInteger(oriVal, DEF_INTEGER_VAL);
	}

	public static Integer optInteger(Object oriVal, Integer defVal) {
		try {

			if (oriVal == null) {
				return defVal;
			}

			if (oriVal instanceof Integer) {
				return (Integer) oriVal;
			}

			if (oriVal instanceof Number) {
				return ((Number) oriVal).intValue();
			}

			if (oriVal instanceof Boolean) {
				return ((Boolean) oriVal) ? 1 : 0;
			}

			String tmpStr = "";
			if (oriVal instanceof byte[]) {
				tmpStr = bytes2String((byte[]) oriVal, "");
			} else if (oriVal instanceof String) {
				tmpStr = (String) oriVal;
			}

			if (!isEmpty(tmpStr)) {
				Integer result;
				try {
					result = Integer.valueOf(tmpStr);
				} catch (NumberFormatException e) {
					result = defVal;
				}

				return result;
			}

			return defVal;

		} catch (Exception e) {
			return defVal;
		}
	}

	public static Boolean optBoolean(Object oriVal) {
		return optBoolean(oriVal, DEF_BOOLEAN_VAL);
	}

	public static Boolean optBoolean(Object oriVal, Boolean defVal) {
		try {

			if (oriVal == null) {
				return defVal;
			}

			if (oriVal instanceof Boolean) {
				return (Boolean) oriVal;
			}

			if (oriVal instanceof Integer) {
				return (Integer) oriVal > 0;
			}

			String tmpStr = "";
			if (oriVal instanceof byte[]) {
				tmpStr = bytes2String((byte[]) oriVal, "");
			} else if (oriVal instanceof String) {
				tmpStr = (String) oriVal;
			}

			if (!isEmpty(tmpStr)) {
				return tmpStr.equalsIgnoreCase("true") || Integer.valueOf(tmpStr) > 0;
			}

			return defVal;

		} catch (Exception e) {
			return defVal;
		}
	}

	public interface IParser {
		boolean has(String key);

		String optString(String key);

		String optString(String key, String defaultValue);

		Integer optInteger(String key);

		Integer optInteger(String key, Integer defaultValue);

		Boolean optBoolean(String key);

		Boolean optBoolean(String key, Boolean defaultValue);

		Double optDouble(String key);

		Double optDouble(String key, Double defaultValue);

		Float optFloat(String key);

		Float optFloat(String key, Float defaultValue);

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

	public static boolean notEmpty(String str){
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

	/**
	 * convert bytes[] to String
	 */
	public static String bytes2String(byte[] bytes, String defVal) {

		if (bytes == null) {
			return defVal;
		}

		String result = null;

		try {
			result = new String(bytes, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			result = new String(bytes);

		} catch (Exception e) {
			result = defVal;
		}

		return result;
	}

	/**
	 * convert Object[] to String
	 */
	public static String objectArray2String(Object[] oriVal, String defVal) {
		if (oriVal == null) {
			return defVal;
		}

		StringBuilder sb = new StringBuilder("");

		boolean isFirst = true;
		for (Object obj : oriVal) {
			try {
				if (isFirst) {
					isFirst = false;
				} else {
					sb.append(",");
				}
				sb.append(obj.toString());
			} catch (Exception e) {
				//
			}
		}

		return sb.toString();
	}

}
