package com.szss.androidapp.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResponseData {
	private boolean status;
	private int errCode = 0;
	private String description;
	private JSONObject data;
	private JSONArray jsonarrayData;
	private String server;

	private ResponseData() {
		// Hidden default constructor;
	}

	public static ResponseData responseParser(Object object) {

		if (object == null) {
			return null;
		}

		JSONObject jsonObj;

		if (object instanceof String) {
			try {
				jsonObj = new JSONObject((String) object);
			} catch (Exception e) {
				return null;
			}

		} else if (object instanceof JSONObject) {
			jsonObj = (JSONObject) object;

		} else {
			return null;
		}

		ResponseData response = new ResponseData();
		JSONUtil jsonUtil = new JSONUtil(jsonObj);
		try {
			response.status = jsonUtil.optBoolean("code");
			response.errCode = jsonUtil.optInteger("code");
			response.description = jsonUtil.optString("code");
			if (ParserUtil.isEmpty(response.description)) {
				response.description = jsonUtil.optString("detail");
			}
//			response.server = jsonUtil.optString("server");

			if (jsonObj.get("data") instanceof JSONObject) {
				response.data = jsonUtil.optJSONObject("data");
			} else {
				response.jsonarrayData = jsonUtil.optJSONArray("data");
			}

		} catch (Exception e) {

		}
		return response;


//		"data": {
//			"author": {
//				"des": "欢迎下载使用OkHttpUtils网络框架，如果遇到问题，欢迎反馈！如果觉得好用，不妨star一下，以便让更多的人了解使用！",
//						"email": "liaojeason@126.com",
//						"address": "北京",
//						"name": "jeasonlzy",
//						"github": "https://github.com/jeasonlzy0216",
//						"qq": "QQ交流群：489873144",
//						"fullname": "廖子尧"
//			},
//			"des": "请求服务器返回Json对象",
//					"method": "GET",
//					"url": "http://server.jeasonlzy.com/OkHttpUtils/jsonObject",
//					"ip": "2607:8700:101:5f42:0:0:0:8:45342"
//		},
//		"code": 0,
//				"msg": "请求成功"



	}

	public static boolean checkResult(ResponseData response) {
		return response != null && response.isStatus()
				&& (response.getData() != null || response.getJsonarrayData() != null);
	}

	@Override
	public String toString() {
		if (data == null && jsonarrayData == null) {
			return "NULL";
		} else {
			return errCode + " , " + (data == null ? jsonarrayData.toString() : data.toString());
		}
	}

	// region -------- Getter & Setter --------

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setErrCode(int code) {
		this.errCode = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public JSONArray getJsonarrayData() {
		return jsonarrayData;
	}

	public void setJsonarrayData(JSONArray jsonarrayData) {
		this.jsonarrayData = jsonarrayData;
	}

	public int getErrCode() {
		return errCode % 10000;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	// endregion

}
