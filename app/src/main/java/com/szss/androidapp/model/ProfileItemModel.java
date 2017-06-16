package com.szss.androidapp.model;

/**
 * Created by wuwei on 2017/6/16.
 */

public class ProfileItemModel {

	public enum ItemType {
		profileInfo, space, setting, notification, message
	}

	private ItemType mType;

	public ProfileItemModel(ItemType itemType) {
		this.mType = itemType;
	}

	public ItemType getType() {
		return mType;
	}

}
