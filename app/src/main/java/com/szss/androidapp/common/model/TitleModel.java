package com.szss.androidapp.common.model;

/**
 * Created by wuwei on 2017/6/21.
 */

public class TitleModel {

	private String mTitle;
	private boolean mIsShowMore;

	public TitleModel(String title, boolean isShowMore) {
		mTitle = title;
		mIsShowMore = isShowMore;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public boolean isShowMore() {
		return mIsShowMore;
	}

	public void setShowMore(boolean showMore) {
		mIsShowMore = showMore;
	}
}
