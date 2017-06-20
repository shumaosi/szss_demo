package com.szss.androidapp.model;

/**
 * Created by wuwei on 2017/6/16.
 */

public class HaowenItemModel {

	public enum ItemType {
		banner, huati, space, title, imageText
	}

	private ItemType mType;

	public HaowenItemModel(ItemType itemType) {
		this.mType = itemType;
	}

	public ItemType getType() {
		return mType;
	}

}
