package com.szss.androidapp.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.szss.androidapp.R;

/**
 * Created by wuwei on 2017/6/15.
 */

public class LoadingView extends LinearLayout {
	public static final int STYLE_SMALL = 0;
	public static final int STYLE_SMALL_WITH_TEXT = 1;
	public static final int STYLE_BIG = 2;
	/**
	 * default style:SMALL
	 **/
	private int mLoadingStyle = STYLE_SMALL;

	private static final int STYLE_BIG_WITH_TEXT = 3;
	private ProgressBar mProgressBar;
	private TextView mTextView;

	public LoadingView(Context context) {
		this(context, null);
	}

	public LoadingView(Context context, int loadingStyle) {
		super(context);
		mLoadingStyle = loadingStyle;
		init();
	}

	public LoadingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LoadingView(Context context, AttributeSet attrs,
					   int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.LoadingView);
		mLoadingStyle = typedArray.getInt(
				R.styleable.LoadingView_loadingStyle,
				STYLE_SMALL_WITH_TEXT);
		typedArray.recycle();
		init();
	}

	private void init() {
		setGravity(Gravity.CENTER);
		switch (mLoadingStyle) {
			case STYLE_SMALL:
				setPadding(
						getResources().getDimensionPixelSize(
								R.dimen.small_loading_padding_top),
						0,
						0,
						getResources().getDimensionPixelSize(
								R.dimen.small_loading_padding_bottom));
				mProgressBar = new ProgressBar(getContext());
				mProgressBar.setLayoutParams(new ViewGroup.LayoutParams(
						getResources().getDimensionPixelSize(
								R.dimen.small_loading_progress), getResources()
						.getDimensionPixelSize(
								R.dimen.small_loading_progress)));
				addView(mProgressBar);
				break;
			case STYLE_SMALL_WITH_TEXT:
				setPadding(
						0,
						getResources().getDimensionPixelSize(
								R.dimen.small_loading_padding_top),
						0,
						getResources().getDimensionPixelSize(
								R.dimen.small_loading_padding_bottom));
				mProgressBar = new ProgressBar(getContext());
				mProgressBar.setLayoutParams(new ViewGroup.LayoutParams(
						getResources().getDimensionPixelSize(
								R.dimen.small_loading_progress), getResources()
						.getDimensionPixelSize(
								R.dimen.small_loading_progress)));
				mTextView = new TextView(getContext());
				mTextView.setLayoutParams(new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				mTextView.setGravity(Gravity.CENTER);
				mTextView.setText(getContext().getString(R.string.loading));
				addView(mProgressBar);
				mTextView.setPadding(getResources()
						.getDimensionPixelSize(
								R.dimen.small_loading_padding_between), 0, 0, 0);
				addView(mTextView);
				break;
			case STYLE_BIG:
				mProgressBar = new ProgressBar(getContext());
				mProgressBar.setLayoutParams(new ViewGroup.LayoutParams(
						getResources().getDimensionPixelSize(
								R.dimen.big_loading_progress),
						getResources().getDimensionPixelSize(
								R.dimen.big_loading_progress)));
				addView(mProgressBar);
				break;
			case STYLE_BIG_WITH_TEXT:
				setOrientation(VERTICAL);
				mProgressBar = new ProgressBar(getContext());
				mProgressBar.setLayoutParams(new ViewGroup.LayoutParams(
						getResources().getDimensionPixelSize(
								R.dimen.big_loading_progress),
						getResources().getDimensionPixelSize(
								R.dimen.big_loading_progress)));
				mTextView = new TextView(getContext());
				mTextView.setLayoutParams(new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				mTextView.setGravity(Gravity.CENTER);
				mTextView.setText(getContext().getString(R.string.loading));
				addView(mProgressBar);
				addView(mTextView);
				break;
		}
	}

	public void setHeightInAbsListView(int height) {
		setGravity(Gravity.CENTER_HORIZONTAL);
		setLayoutParams(new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT, height));
	}

	public TextView getTextView() {
		return mTextView;
	}

	public ProgressBar getProgressBar() {
		return mProgressBar;
	}
}
