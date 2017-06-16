package com.szss.androidapp.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.ninegrid.NineGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.szss.androidapp.R;
import com.szss.androidapp.adapter.NewsAdapter;
import com.szss.androidapp.model.NewsModel;
import com.szss.androidapp.model.NewsResponse;
import com.szss.androidapp.model.NewsCallback;
import com.szss.androidapp.util.GlideImageLoader;
import com.szss.androidapp.util.RecyclerViewOnScrollListener;
import com.szss.androidapp.util.Urls;

import java.util.List;

/**
 * Created by wuwei on 2017/6/15.
 */

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RecyclerViewOnScrollListener mRecyclerViewOnScrollListener;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLinearLayoutManager;
	private String url;
	private int currentPage = 2;
	private NewsAdapter mNewsAdapter;
	private boolean isLoadMore = false;

	public static NewsFragment newInstance() {
		return new NewsFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.swiperefresh_fragment, container, false);
		mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.refreshLayout_recyclerview);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		NineGridView.setImageLoader(new GlideImageLoader());
		initRecyclerView();
		initSwipeRefresh();
		setOnScrollEvent();
	}

	@Override
	public void onRefresh() {
		OkGo.<NewsResponse<List<NewsModel>>>get(url + "1")
				.cacheKey("TabFragment_" + "Android")
				.cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
				.execute(new NewsCallback<NewsResponse<List<NewsModel>>>() {
					@Override
					public void onSuccess(Response<NewsResponse<List<NewsModel>>> response) {
						List<NewsModel> results = response.body().results;
						if (results != null) {
							currentPage = 2;
							mNewsAdapter.addDatas(results);
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
		mNewsAdapter.addLoadingView();
		OkGo.<NewsResponse<List<NewsModel>>>get(url + currentPage)
				.cacheMode(CacheMode.NO_CACHE)
				.execute(new NewsCallback<NewsResponse<List<NewsModel>>>() {
					@Override
					public void onSuccess(Response<NewsResponse<List<NewsModel>>> response) {
						List<NewsModel> results = response.body().results;
						if (results != null && results.size() > 0) {
							currentPage++;
							mNewsAdapter.addDatas(results);
						}
					}

					@Override
					public void onFinish() {
						super.onFinish();
						isLoadMore = false;
						mNewsAdapter.removeLoadingView();
					}
				});
	}

	private void initSwipeRefresh() {
		mSwipeRefreshLayout.setRefreshing(true);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
		onRefresh();
	}

	private void initRecyclerView() {
		url = Urls.URL_GANK_BASE + "Android" + "/" + 20 + "/";
		mNewsAdapter = new NewsAdapter();
		mLinearLayoutManager = new LinearLayoutManager(getContext());
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		mRecyclerView.setAdapter(mNewsAdapter);
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

	private void setRefreshing(final boolean refreshing) {
		mSwipeRefreshLayout.post(new Runnable() {
			@Override
			public void run() {
				mSwipeRefreshLayout.setRefreshing(refreshing);
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
