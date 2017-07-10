package com.szss.androidapp.module.haojia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.szss.androidapp.base.BaseSwiperefreshFragment;
import com.szss.androidapp.common.model.BannerModel;
import com.szss.androidapp.common.model.CardStyle2Model;
import com.szss.androidapp.common.model.CardStyle5Model;
import com.szss.androidapp.common.model.HorizontalListViewModel;
import com.szss.androidapp.common.model.SpaceModel;
import com.szss.androidapp.module.haowen.HaowenAdapter;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/22.
 */

public class HaojiaJinxuanFragment extends BaseSwiperefreshFragment {

	private HaowenAdapter mHaowenAdapter;
	private LinearLayoutManager mLinearLayoutManager;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initRecyclerView();
		initData();
	}

	@Override
	public void onRefresh() {
		setRefreshing(false);
	}

	private void initRecyclerView() {
		mLinearLayoutManager = new LinearLayoutManager(getContext());
		mHaowenAdapter = new HaowenAdapter();
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		mRecyclerView.setAdapter(mHaowenAdapter);
	}

	private void initData() {
		ArrayList list = new ArrayList();
		list.add(new BannerModel());
		list.add(new HorizontalListViewModel());
		list.add(new HorizontalListViewModel());
		list.add(new SpaceModel());
		list.add(new CardStyle5Model());
		list.add(new SpaceModel());
		list.add(new CardStyle2Model("http://s15.sinaimg.cn/middle/6cfc958ft9846c34bee2e&690",
				"单色却不单调 - 小米24寸90分旅行箱晒单", "#晒物 #拉杆箱", "4", "1"));
		list.add(new CardStyle2Model("http://www.pp3.cn/uploads/201403/1386294780270.jpg",
				"知你冷暖 懂你温度 2017年618空调选购大盘点",
				"#空调",
				"14", "285"));
		list.add(new CardStyle2Model("http://www.005.tv/uploads/allimg/160929/143R33345-14.jpg",
				"一分钟教你拍出旅行中的高逼格照片",
				"#经验 #摄影摄像",
				"8", "131"));
		list.add(new CardStyle2Model("http://img2.imgtn.bdimg.com/it/u=2955188281,1259009863&fm=26&gp=0.jpg",
				"高玩：决胜千里之外！搞定远程控制(视频演示) 篇一：神奇的开机棒",
				"#晒物 #数码配件", "77", "399"));
		mHaowenAdapter.addData(list);
	}

}
