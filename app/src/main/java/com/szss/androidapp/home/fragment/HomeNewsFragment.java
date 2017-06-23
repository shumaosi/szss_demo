package com.szss.androidapp.home.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.ninegrid.NineGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.szss.androidapp.R;
import com.szss.androidapp.base.BaseSwiperefreshFragment;
import com.szss.androidapp.home.adapter.HomeNewsAdapter;
import com.szss.androidapp.model.NewsModel;
import com.szss.androidapp.model.NewsResponse;
import com.szss.androidapp.model.NewsCallback;
import com.szss.androidapp.util.GlideImageLoader;
import com.szss.androidapp.util.RecyclerViewOnScrollListener;
import com.szss.androidapp.util.ResponseData;
import com.szss.androidapp.util.Urls;

import java.util.List;

/**
 * Created by wuwei on 2017/6/15.
 */

public class HomeNewsFragment extends BaseSwiperefreshFragment {

	private RecyclerViewOnScrollListener mRecyclerViewOnScrollListener;
	private LinearLayoutManager mLinearLayoutManager;
	private String url;
	private int currentPage = 2;
	private HomeNewsAdapter mHomeNewsAdapter;
	private boolean isLoadMore = false;

	public static HomeNewsFragment newInstance() {
		return new HomeNewsFragment();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		NineGridView.setImageLoader(new GlideImageLoader());
		initRecyclerView();
		onRefresh();
		setOnScrollEvent();
	}

	@Override
	public void onRefresh() {

//		OkGo.<String>get(url+"1").tag(this).cacheKey("TabFragment_"+"Android").cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST).
//				execute(new StringCallback() {
//					@Override
//					public void onSuccess(Response<String> response) {
//						String url;
//						Log.i("ww",response.body());
//						ResponseData responseData = ResponseData.responseParser(response.body());
//
//					}
//				});

		OkGo.<NewsResponse<List<NewsModel>>>get(url + "1")
				.cacheKey("TabFragment_" + "Android")
				.cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
				.execute(new NewsCallback<NewsResponse<List<NewsModel>>>() {
					@Override
					public void onSuccess(Response<NewsResponse<List<NewsModel>>> response) {
						List<NewsModel> results = response.body().results;
						if (results != null) {
							currentPage = 2;
							mHomeNewsAdapter.addData(results);
						}
					}

					@Override
					public void onCacheSuccess(Response<NewsResponse<List<NewsModel>>> response) {
						super.onCacheSuccess(response);
					}

					@Override
					public void onError(Response<NewsResponse<List<NewsModel>>> response) {
						super.onError(response);
					}

					@Override
					public void onFinish() {
						super.onFinish();
						setRefreshing(false);
					}
				});
	}

	private void loadMoreData() {
		isLoadMore = true;
		mHomeNewsAdapter.addLoadingView();
		OkGo.<NewsResponse<List<NewsModel>>>get(url + currentPage)
				.cacheMode(CacheMode.NO_CACHE)
				.execute(new NewsCallback<NewsResponse<List<NewsModel>>>() {
					@Override
					public void onSuccess(Response<NewsResponse<List<NewsModel>>> response) {
						List<NewsModel> results = response.body().results;
						if (results != null && results.size() > 0) {
							currentPage++;
							mHomeNewsAdapter.addData(results);
						}
					}

					@Override
					public void onFinish() {
						super.onFinish();
						isLoadMore = false;
						mHomeNewsAdapter.removeLoadingView();
					}
				});
	}

	private void initRecyclerView() {
		url = Urls.URL_GANK_BASE + "Android" + "/" + 20 + "/";
		mHomeNewsAdapter = new HomeNewsAdapter();
		mLinearLayoutManager = new LinearLayoutManager(getContext());
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		mRecyclerView.setAdapter(mHomeNewsAdapter);
		mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
									   RecyclerView.State state) {
				outRect.bottom = 16;
			}

			@Override
			public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
				ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.gray_eeeeee));
				int count = parent.getChildCount();
				for (int i = 0; i < count; i++) {
					View v = parent.getChildAt(i);
					colorDrawable.setBounds(v.getLeft(), v.getBottom(), v.getRight(),
							v.getBottom() + 16);
					colorDrawable.draw(c);
				}
			}
		});
	}

	private void setOnScrollEvent() {
		try {
			mRecyclerViewOnScrollListener = new RecyclerViewOnScrollListener(mLinearLayoutManager) {

				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					super.onScrolled(recyclerView, dx, dy);
				}

				@Override
				public void onLoadMore(int current_page) {
					if (!isLoadMore) {
						loadMoreData();
					}
				}
			};
			mRecyclerView.addOnScrollListener(mRecyclerViewOnScrollListener);
		} catch (Exception e) {

		}
	}
}
