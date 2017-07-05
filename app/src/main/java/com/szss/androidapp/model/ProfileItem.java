package com.szss.androidapp.model;

/**
 * Created by wuwei on 2017/6/16.
 */

public class ProfileItem {

	public enum ItemType {
		profileInfo, space, favorable, setting, notification, message, capture
	}

	private ItemType mType;

	public ProfileItem(ItemType itemType) {
		this.mType = itemType;
	}

	public ItemType getType() {
		return mType;
	}

}
