package com.szss.androidapp.haowen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;
import com.szss.androidapp.base.BaseRecycleViewAdapter;
import com.szss.androidapp.common.BannerViewHolder;
import com.szss.androidapp.common.CardStyle1ViewHolder;
import com.szss.androidapp.common.HorizontalListViewHolder;
import com.szss.androidapp.common.SpaceViewHolder;
import com.szss.androidapp.common.TitleViewHolder;
import com.szss.androidapp.model.HaowenItemModel;

/**
 * Created by wuwei on 2017/6/20.
 */

public class HaowenAdapter extends BaseRecycleViewAdapter {

	public static int TYPE_BANNER = 0;
	public static int TYPE_HUATI = 1;
	public static int TYPE_SPACE = 2;
	public static int TYPE_TITLE = 3;
	public static int TYPE_CARDSTYLE1 = 4;

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		if (viewType == TYPE_BANNER) {
			View view = layoutInflater.inflate(R.layout.banner_item, parent, false);
			return new BannerViewHolder(view);
		} else if (viewType == TYPE_HUATI) {
			View view = layoutInflater.inflate(R.layout.horizontal_list_item, parent, false);
			return new HorizontalListViewHolder(view);
		} else if (viewType == TYPE_SPACE) {
			View view = layoutInflater.inflate(R.layout.space_item, parent, false);
			return new SpaceViewHolder(view);
		} else if (viewType == TYPE_TITLE) {
			View view = layoutInflater.inflate(R.layout.title_item, parent, false);
			return new TitleViewHolder(view);
		} else if (viewType == TYPE_CARDSTYLE1) {
			View view = layoutInflater.inflate(R.layout.card_style1_item, parent, false);
			return new CardStyle1ViewHolder(view);
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		int viewType = getItemViewType(position);
		if (viewType == TYPE_BANNER) {
			((BannerViewHolder) holder).bindData();
		}
	}

	@Override
	public int getItemViewType(int position) {
		HaowenItemModel haowenItemModel = (HaowenItemModel) getDataList().get(position);
		switch (haowenItemModel.getType()) {
			case banner:
				return TYPE_BANNER;
			case huati:
				return TYPE_HUATI;
			case space:
				return TYPE_SPACE;
			case title:
				return TYPE_TITLE;
			case imageText:
				return TYPE_CARDSTYLE1;
			default:
				return TYPE_SPACE;
		}
	}

}
