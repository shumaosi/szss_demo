package com.szss.androidapp.common.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.szss.androidapp.R;
import com.szss.androidapp.base.WebActivity;
import com.szss.androidapp.common.model.NewsModel;
import com.szss.androidapp.util.Utils;

import java.util.ArrayList;

/**
 * Created by wuwei on 2017/6/26.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

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

	public void bindData(final NewsModel newsModel) {
		title.setText(newsModel.desc);
		content.setText(newsModel.desc);
		date.setText(newsModel.publishedAt.toString());
		ArrayList<ImageInfo> imageInfos = new ArrayList<>();
		if (newsModel.images != null) {
			for (String image : newsModel.images) {
				ImageInfo info = new ImageInfo();
				info.setThumbnailUrl(image);
				info.setBigImageUrl(image);
				imageInfos.add(info);
			}
		}
		nineGridView.setSingleImageSize(Utils.getScreenSize(itemView.getContext()).x);
		nineGridView.setAdapter(new NineGridViewClickAdapter(itemView.getContext(), imageInfos));
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebActivity.runActivity(itemView.getContext(), newsModel.desc, newsModel.url);
			}
		});
	}

}
