package com.szss.androidapp.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szss.androidapp.R;
import com.szss.androidapp.model.ItemModel;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/5/17.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.BaseViewHolder> {

	private ArrayList<ItemModel> mDataList;

	public HomeListAdapter() {

		mDataList = new ArrayList<>();

//		mDataList.add(new ItemBean.ImageBean(R.drawable.image_item1));
//		mDataList.add(new ItemBean.ImageClickItemBean(R.drawable.image_qianggou,R.drawable.image_qiandao,R.drawable.image_wuliu,R.drawable.image_libao));
//		mDataList.add(new ItemBean.ImageBean(R.drawable.image_item2));
//		mDataList.add(new ItemBean.ImageBean(R.drawable.image_item3));
//		mDataList.add(new ItemBean.TitleBean("本周卖的最好的十款零食"));
//		mDataList.add(new ItemBean.DetailBean(R.drawable.detail_item_1, "超值爆款 回味无穷", "$18.9"));
//		mDataList.add(new ItemBean.DetailBean(R.drawable.detail_item_2, "碎催爆满 奶香浓郁", "$19.9"));
//		mDataList.add(new ItemBean.DetailBean(R.drawable.detail_item_3, "泰国芒果 酸甜健康", "$13.9"));
//		mDataList.add(new ItemBean.DetailBean(R.drawable.detail_item_4, "靖江口味 红润透亮", "$21.9"));
//		mDataList.add(new ItemBean.DetailBean(R.drawable.detail_item_5, "酥松绵软 好吃不黏牙", "$16.9"));
//		mDataList.add(new ItemBean.DetailBean(R.drawable.detail_item_6, "别带错了 我可是约辣", "$9.9"));
//		mDataList.add(new ItemBean.TitleBean("编辑精选"));
//		mDataList.add(new ItemBean.TopicBean(R.drawable.topic_item1, "战乱令屈原痛苦不堪，但吃却无法停止", "路漫漫其修远兮，吾将上下而求索。在端午吃一口香粽，你是否也会忆起满腹才华，以身殉国的屈原。"));
//		mDataList.add(new ItemBean.TopicBean(R.drawable.topic_item2, "那些好吃的美味都是玩出来的", "吃东西的方式还是一成不变？别担心，小时候的美味，也能吃出新花样~"));

		mDataList.add(new ItemModel("强大的缓存示例 -- 先联网获取数据,然后断开网络再进试试",//
				"1.OkGo的强大的缓存功能,让你代码无需关心数据来源,专注于业务逻辑的实现\n" +//
						"2.内置五种缓存模式满足你各种使用场景\n" +//
						"3.支持自定义缓存策略，可以按照自己的需求改写缓存逻辑\n" +//
						"4.支持自定义缓存过期时间"));

		mDataList.add(new ItemModel("基本功能",//
				"1.GET，HEAD，OPTIONS，POST，PUT，DELETE, PATCH, TRACE 请求方法演示\n" +//
						"2.请求服务器返回bitmap对象\n" +//
						"3.支持https请求\n" +//
						"4.支持同步请求\n" +//
						"5.支持301重定向"));

		mDataList.add(new ItemModel("自动解析JSON对象",//
				"1.自动解析JSONObject对象\n" + //
						"2.自动解析JSONArray对象"));

		mDataList.add(new ItemModel("文件下载",//
				"1.支持大文件或小文件下载，无论多大文件都不会发生OOM\n" +//
						"2.支持监听下载进度和下载网速\n" +//
						"3.支持自定义下载目录和下载文件名"));

		mDataList.add(new ItemModel("文件上传",//
				"1.支持上传单个文件\n" +//
						"2.支持同时上传多个文件\n" +//
						"3.支持多个文件多个参数同时上传\n" +//
						"4.支持大文件上传,无论多大都不会发生OOM\n" +//
						"5.支持监听上传进度和上传网速"));


	}

	@Override
	public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		BaseViewHolder vh;
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewhodler_textview, parent, false);
		vh = new BaseViewHolder(view);
		return vh;
	}

	@Override
	public void onBindViewHolder(BaseViewHolder holder, int position) {
		final ItemModel itemModel = mDataList.get(position);
		TextView tittle = (TextView) holder.itemView.findViewById(R.id.title);
		TextView message = (TextView) holder.itemView.findViewById(R.id.message);
		tittle.setText(itemModel.title);
		message.setText(itemModel.des);
	}

	@Override
	public int getItemCount() {
		return mDataList.size();
	}

	static class BaseViewHolder extends RecyclerView.ViewHolder {

		BaseViewHolder(View itemView) {
			super(itemView);
		}
	}

}
