package com.szss.androidapp.rxbus;

import com.lzy.imagepicker.bean.ImageItem;

/**
 * Created by wuwei on 2017/6/23.
 */

public class UpdateProfileIconEvent {

	private ImageItem mImageItem;

	public UpdateProfileIconEvent(ImageItem imageItem) {
		mImageItem = imageItem;
	}

	public ImageItem getImageItem() {
		return mImageItem;
	}

	public void setImageItem(ImageItem imageItem) {
		mImageItem = imageItem;
	}
}
