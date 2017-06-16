package com.szss.androidapp.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtil implements ParserUtil.IParser {

	// region -------- Default Values Define --------

	public static final String JSONUTIL_DEFAULT_STRING_VAL = "";
	public static final Integer JSONUTIL_DEFAULT_INTEGER_VAL = 0;
	public static final Boolean JSONUTIL_DEFAULT_BOOLEAN_VAL = false;
	public static final JSONObject JSONUTIL_DEFAULT_JSONOBJECT_VAL = null;
	public static final JSONArray JSONUTIL_DEFAULT_JSONARRAY_VAL = null;
	public static final Double JSONUTIL_DEFAULT_DOUBLE_VAL = 0.0d;
	public static final Float JSONUTIL_DEFAULT_FLOAT_VAL = 0.0f;

	// endregion

	private JSONObject jsonObject = null;

	public JSONUtil(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public void setJsonObject(JSONObject obj) {
		this.jsonObject = obj;
	}

	// region -------- Object Value --------

	/**
	 * Returns the value mapped by name, or {@code null} if no such mapping exists.
	 * 
	 * @param key
	 * @return
	 */
	public Object opt(String key) {
		if (jsonObject == null) {
			return null;
		} else {
			return jsonObject.opt(key);
		}
	}

	public boolean has(String key) {
		if (jsonObject == null) {
			return false;
		} else {
			return jsonObject.has(key);
		}
	}

	// endregion

	// region -------- Get String Value --------

	/**
	 * Returns the value mapped by name, or {@code JSONUTIL_DEFAULT_STRING_VAL} if no such mapping
	 * exists.
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public String optString(String key) {
		return optString(key, JSONUTIL_DEFAULT_STRING_VAL);
	}

	/**
	 * Returns the value mapped by name, or {@code defaultValue} if no such mapping exists.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	@Override
	public String optString(String key, String defaultValue) {

		try {
			if (jsonObject == null) {
				return defaultValue;
			}

			if (jsonObject.isNull(key)) {
				return defaultValue;
			}

			Object value = jsonObject.opt(key);
			return ParserUtil.optString(value, defaultValue);

		} catch (Exception e) {
			//
		}

		return defaultValue;
	}

	// endregion

	// region -------- Get Integer Value --------

	/**
	 * Returns the value mapped by name, or {@code JSONUTIL_DEFAULT_INTEGER_VAL} if no such mapping
	 * exists.
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Integer optInteger(String key) {
		return optInteger(key, JSONUTIL_DEFAULT_INTEGER_VAL);
	}

	/**
	 * Returns the value mapped by name, or {@code defaultValue} if no such mapping exists.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	@Override
	public Integer optInteger(String key, Integer defaultValue) {

		try {
			if (jsonObject == null) {
				return defaultValue;
			}

			if (jsonObject.isNull(key)) {
				return defaultValue;
			}

			Object value = jsonObject.opt(key);
			return ParserUtil.optInteger(value, defaultValue);

		} catch (Exception e) {
			//
		}

		return defaultValue;
	}

	// endregion

	public Long optLong(String key, Long defaultValue) {
		try {

			if (jsonObject == null) {
				return defaultValue;
			}

			if (jsonObject.isNull(key)) {
				return defaultValue;
			}

			int intVal = optInteger(key, defaultValue.intValue());
			return intVal == defaultValue.intValue() ? jsonObject.optLong(key, defaultValue) : intVal;

		} catch (Exception e) {
			//
		}

		return defaultValue;
	}

	// region -------- Get Boolean Value --------

	/**
	 * Returns the value mapped by name, or {@code JSONUTIL_DEFAULT_BOOLEAN_VAL} if no such mapping
	 * exists.
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Boolean optBoolean(String key) {
		return optBoolean(key, JSONUTIL_DEFAULT_BOOLEAN_VAL);
	}

	/**
	 * Returns the value mapped by name, or {@code defaultValue} if no such mapping exists.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	@Override
	public Boolean optBoolean(String key, Boolean defaultValue) {

		try {
			if (jsonObject == null) {
				return defaultValue;
			}

			if (jsonObject.isNull(key)) {
				return defaultValue;
			}

			Object value = jsonObject.opt(key);
			return ParserUtil.optBoolean(value, defaultValue);

		} catch (Exception e) {
			//
		}

		return defaultValue;
	}

	@Override
	public Double optDouble(String key) {
		return optDouble(key,JSONUTIL_DEFAULT_DOUBLE_VAL);
	}

	@Override
	public Double optDouble(String key, Double defaultValue) {
		try {
			if (jsonObject == null) {
				return defaultValue;
			}

			if (jsonObject.isNull(key)) {
				return defaultValue;
			}

			Object value = jsonObject.opt(key);
			return ParserUtil.optDouble(value, defaultValue);

		} catch (Exception e) {
			//
		}

		return defaultValue;
	}

	@Override
	public Float optFloat(String key) {
		return optFloat(key,JSONUTIL_DEFAULT_FLOAT_VAL);
	}

	@Override
	public Float optFloat(String key, Float defaultValue) {
		try {
			if (jsonObject == null) {
				return defaultValue;
			}

			if (jsonObject.isNull(key)) {
				return defaultValue;
			}

			Object value = jsonObject.opt(key);
			return ParserUtil.optFloat(value, defaultValue);

		} catch (Exception e) {
			//
		}

		return defaultValue;
	}

	// endregion

	// region -------- Get JSONObject Value --------

	public JSONObject optJSONObject(String key) {
		return optJSONObject(key, JSONUTIL_DEFAULT_JSONOBJECT_VAL);
	}

	public JSONObject optJSONObject(String key, JSONObject defaultValue) {
		try {

			if (jsonObject == null) {
				return defaultValue;
			}

			if (jsonObject.isNull(key)) {
				return defaultValue;
			}

			JSONObject value = jsonObject.optJSONObject(key);
			return value != null ? value : defaultValue;

		} catch (Exception e) {
			//
		}

		return defaultValue;
	}

	// endregion

	// region -------- Get JSONArray Value --------

	public JSONArray optJSONArray(String key) {
		return optJSONArray(key, JSONUTIL_DEFAULT_JSONARRAY_VAL);
	}

	public JSONArray optJSONArray(String key, JSONArray defaultValue) {
		try {

			if (jsonObject == null) {
				return defaultValue;
			}

			if (jsonObject.isNull(key)) {
				return defaultValue;
			}

			JSONArray value = jsonObject.optJSONArray(key);
			return value != null ? value : defaultValue;

		} catch (Exception e) {
			//
		}

		return defaultValue;
	}

	// endregion

}
