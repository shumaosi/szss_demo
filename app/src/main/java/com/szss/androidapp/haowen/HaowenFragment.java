package com.szss.androidapp.haowen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szss.androidapp.R;
import com.szss.androidapp.common.model.BannerModel;
import com.szss.androidapp.common.model.CardStyle1Model;
import com.szss.androidapp.common.model.CardStyle2Model;
import com.szss.androidapp.common.model.HorizontalListViewModel;
import com.szss.androidapp.common.model.JiXuanModel;
import com.szss.androidapp.common.model.TitleModel;
import com.szss.androidapp.model.HaowenItem;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/20.
 */

public class HaowenFragment extends Fragment {

	private RecyclerView mRecyclerView;
	private HaowenAdapter mHaowenAdapter;
	private LinearLayoutManager mLinearLayoutManager;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_fragment, container, false);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initRecyclerView();
		initData();
	}

	private void initRecyclerView() {
		mHaowenAdapter = new HaowenAdapter();
		mLinearLayoutManager = new LinearLayoutManager(getContext());
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		mRecyclerView.setAdapter(mHaowenAdapter);
	}

	private void initData() {
		ArrayList<Object> list = new ArrayList<>();
		list.add(new BannerModel());
		list.add(new HorizontalListViewModel());
		list.add(new TitleModel("值精选", false));
		list.add(new JiXuanModel());
		list.add(new TitleModel("今日热门", true));
		list.add(new CardStyle1Model("https://b-ssl.duitang.com/uploads/item/201512/25/20151225094712_aFfvC.thumb.700_0.jpeg", "吃遍全国的54总特产牛肉干，告诉你那些值得回购", "http://img.zcool.cn/community/011fe955d9424a32f875a132e0173a.jpg", "魔都食鉴局", "609", "2.8k"));
		list.add(new CardStyle1Model("http://www.doodoo.ru/uploads/posts/2012-04/prikoly-na-fotax-37.jpg",
				"揭开\"神油\"遮丑布，到底符合什么标准才能好机油",
				"http://img1.cache.netease.com/catchpic/0/03/03917B3BBD142DA8D7E9BAB241C75366.jpg",
				"汽车奇谈高小强",
				"86",
				"248"));
		list.add(new CardStyle1Model("http://img.cheshi-img.com/product/1_1024/p/35300/35372/52e0a9823af8d.jpg",
				"一个iphone老用户的MI小米6手机体检",
				"http://pic.nipic.com/2007-12-06/2007126145745340_2.jpg",
				"cxr83",
				"137",
				"60"));
		list.add(new CardStyle1Model("http://my.3zitie.cn/shop/2015/2015316/img/2015031609284231824.jpg",
				"移动系统跟我走，windows to go 移动平台制作分享",
				"http://c.hiphotos.baidu.com/zhidao/pic/item/4afbfbedab64034f0afe7479afc379310a551d99.jpg",
				"Dean 喂不饱的够",
				"10",
				"173"));
		list.add(new TitleModel("好文推荐", false));
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
