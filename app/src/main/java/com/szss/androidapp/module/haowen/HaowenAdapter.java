package com.szss.androidapp.module.haowen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;
import com.szss.androidapp.base.BaseRecycleViewAdapter;
import com.szss.androidapp.common.model.BannerModel;
import com.szss.androidapp.common.model.CardStyle1Model;
import com.szss.androidapp.common.model.CardStyle2Model;
import com.szss.androidapp.common.model.CardStyle5Model;
import com.szss.androidapp.common.model.HorizontalListViewModel;
import com.szss.androidapp.common.model.CardStyle4Model;
import com.szss.androidapp.common.model.TitleModel;
import com.szss.androidapp.common.viewholder.BannerViewHolder;
import com.szss.androidapp.common.viewholder.CardStyle1ViewHolder;
import com.szss.androidapp.common.viewholder.CardStyle2ViewHolder;
import com.szss.androidapp.common.viewholder.CardStyle5ViewHolder;
import com.szss.androidapp.common.viewholder.HorizontalListViewHolder;
import com.szss.androidapp.common.viewholder.CardStyle4ViewHolder;
import com.szss.androidapp.common.viewholder.SpaceViewHolder;
import com.szss.androidapp.common.viewholder.TitleViewHolder;

/**
 * Created by wuwei on 2017/6/20.
 **/

public class HaowenAdapter extends BaseRecycleViewAdapter {

	public static int TYPE_BANNER = 0;
	public static int TYPE_HORIZONTALLISTVIEW = 1;
	public static int TYPE_SPACE = 2;
	public static int TYPE_TITLE = 3;
	public static int TYPE_CARDSTYLE1 = 4;
	public static int TYPE_CARDSTYLE2 = 5;
	public static int TYPE_CARDSTYLE3 = 6;
	public static int TYPE_CARDSTYLE4 = 7;

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		if (viewType == TYPE_BANNER) {
			View view = layoutInflater.inflate(R.layout.banner_item, parent, false);
			return new BannerViewHolder(view);
		} else if (viewType == TYPE_HORIZONTALLISTVIEW) {
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
		} else if (viewType == TYPE_CARDSTYLE2) {
			View view = layoutInflater.inflate(R.layout.card_style2_item, parent, false);
			return new CardStyle2ViewHolder(view);
		} else if (viewType == TYPE_CARDSTYLE3) {
			View view = layoutInflater.inflate(R.layout.card_style4_item, parent, false);
			return new CardStyle4ViewHolder(view);
		} else if (viewType == TYPE_CARDSTYLE4) {
			View view = layoutInflater.inflate(R.layout.card_style5_item, parent, false);
			return new CardStyle5ViewHolder(view);
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		int viewType = getItemViewType(position);
		if (viewType == TYPE_BANNER) {
			((BannerViewHolder) holder).bindData();
		} else if (viewType == TYPE_TITLE) {
			((TitleViewHolder) holder).bindData((TitleModel) getItem(position));
		} else if (viewType == TYPE_CARDSTYLE1) {
			((CardStyle1ViewHolder) holder).bindData((CardStyle1Model) getItem(position));
		} else if (viewType == TYPE_CARDSTYLE2) {
			((CardStyle2ViewHolder) holder).bindData((CardStyle2Model) getItem(position));
		} else if (viewType == TYPE_CARDSTYLE3) {
			((CardStyle4ViewHolder) holder).bindData((CardStyle4Model) getItem(position));
		} else if (viewType == TYPE_CARDSTYLE4) {
			((CardStyle5ViewHolder) holder).bindData((CardStyle5Model) getItem(position));
		}
	}

	@Override
	public int getItemViewType(int position) {
		Object object = getDataList().get(position);
		if (object instanceof BannerModel) {
			return TYPE_BANNER;
		} else if (object instanceof HorizontalListViewModel) {
			return TYPE_HORIZONTALLISTVIEW;
		} else if (object instanceof TitleModel) {
			return TYPE_TITLE;
		} else if (object instanceof CardStyle1Model) {
			return TYPE_CARDSTYLE1;
		} else if (object instanceof CardStyle2Model) {
			return TYPE_CARDSTYLE2;
		} else if (object instanceof CardStyle4Model) {
			return TYPE_CARDSTYLE3;
		} else if (object instanceof CardStyle5Model) {
			return TYPE_CARDSTYLE4;
		} else {
			return TYPE_SPACE;
		}
	}

}
