package com.szss.androidapp.home.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.szss.androidapp.R;
import com.szss.androidapp.base.WebActivity;
import com.szss.androidapp.model.NewsModel;
import com.szss.androidapp.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuwei on 2017/6/15.
 */

public class HomeNewsAdapter extends RecyclerView.Adapter {

	public static final String LOADING_MORE = "loading_more";

	public static final int TYPE_NEWS = 1;
	public static final int TYPE_LOADING = 2;


	private ArrayList<Object> mDatas = new ArrayList<>();

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		if (TYPE_NEWS == viewType) {
			View newsView = layoutInflater.inflate(R.layout.item_news, parent, false);
			return new NewsViewHolder(newsView);
		} else {
			View loadView = layoutInflater.inflate(R.layout.small_loading, parent, false);
			return new LoadingViewHolder(loadView);
		}
	}

	/**
	 * load more view
	 **/
	static class LoadingViewHolder extends RecyclerView.ViewHolder {

		public LoadingViewHolder(View itemView) {
			super(itemView);
		}
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
		if (TYPE_NEWS == getItemViewType(position)) {
			final NewsModel newsModel = (NewsModel) mDatas.get(position);
			NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
			newsViewHolder.title.setText(newsModel.desc);
			newsViewHolder.content.setText(newsModel.desc);
			newsViewHolder.date.setText(newsModel.publishedAt.toString());
			ArrayList<ImageInfo> imageInfos = new ArrayList<>();
			if (newsModel.images != null) {
				for (String image : newsModel.images) {
					ImageInfo info = new ImageInfo();
					info.setThumbnailUrl(image);
					info.setBigImageUrl(image);
					imageInfos.add(info);
				}
			}
			newsViewHolder.nineGridView.setSingleImageSize(Utils.getScreenSize(holder.itemView.getContext()).x);
			newsViewHolder.nineGridView.setAdapter(new NineGridViewClickAdapter(newsViewHolder.itemView.getContext(), imageInfos));
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					WebActivity.runActivity(holder.itemView.getContext(), newsModel.desc, newsModel.url);
				}
			});
		} else {

		}
	}

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	@Override
	public int getItemViewType(int position) {
		Object object = getDataList().get(position);
		if (object instanceof NewsModel) {
			return TYPE_NEWS;
		} else {
			return TYPE_LOADING;
		}
	}

	public ArrayList<Object> getDataList() {
		if (mDatas == null) {
			mDatas = new ArrayList<>();
		}
		return mDatas;
	}

	public void addDatas(List<NewsModel> newsModels) {
		getDataList().addAll(newsModels);
		notifyDataSetChanged();
	}

	public void addLoadingView() {
		if (!getDataList().contains(LOADING_MORE)) {
			getDataList().add(LOADING_MORE);
			try {
				notifyItemChanged(mDatas.size() - 1);
			} catch (Exception e) {
				notifyDataSetChanged();
			}
		}
	}

	public void removeLoadingView() {
		if (getDataList().contains(LOADING_MORE)) {
			getDataList().remove(LOADING_MORE);
			try {
				notifyItemChanged(mDatas.size());
			} catch (Exception e) {
				notifyDataSetChanged();
			}
		}
	}

	private class NewsViewHolder extends RecyclerView.ViewHolder {

		private TextView title;
		private TextView content;
		private NineGridView nineGridView;
		private TextView date;

		public NewsViewHolder(View itemView) {
			super(itemView);
			title = (TextView) itemView.findViewById(R.id.news_title);
			content = (TextView) itemView.findViewById(R.id.news_content);
			nineGridView = (NineGridView) itemView.findViewById(R.id.news_image);
			date = (TextView) itemView.findViewById(R.id.news_date);
		}
	}

}
