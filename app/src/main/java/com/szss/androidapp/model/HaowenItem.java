package com.szss.androidapp.model;

/**
 * Created by wuwei on 2017/6/16.
 */

public class HaowenItem {

	public enum ItemType {
		banner, huati, space, title, cardStyle1, cardStyle2
	}

	private ItemType mType;

	public HaowenItem(ItemType itemType) {
		this.mType = itemType;
	}

	public ItemType getType() {
		return mType;
	}

}
