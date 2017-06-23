/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.szss.androidapp.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.ninegrid.NineGridView;
import com.szss.androidapp.R;

import java.io.File;

/**
 * Created by wuwei on 2017/6/15.
 */

public class GlideImageLoaderUtil implements ImageLoader, NineGridView.ImageLoader {

	private static final long serialVersionUID = -5229366824486709422L;

	@Override
	public void onDisplayImage(Context context, ImageView imageView, String url) {
		Glide.with(context).load(url)//
				.placeholder(R.drawable.ic_default_color)// 这行貌似是glide的bug,在部分机型上会导致第一次图片不在中间  ，当图片地址错误时显示默认图片
				.error(R.drawable.ic_default_color)//
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)//
				.into(imageView);
	}

	@Override
	public Bitmap getCacheImage(String url) {
		return null;
	}

	@Override
	public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
		Glide.with(activity).load(new File(path))//
				.placeholder(R.drawable.ic_default_color)//
				.error(R.drawable.ic_default_color)//
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)//
				.into(imageView);
	}

	@Override
	public void clearMemoryCache() {
	}
}
