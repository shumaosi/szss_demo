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
package com.szss.androidapp.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wuwei on 2017/6/15.
 */

public class NewsModel implements Serializable {
	private static final long serialVersionUID = 6753210234564872868L;
	public String _id;
	public Date createdAt;
	public String desc;
	public List<String> images;
	public Date publishedAt;
	public String source;
	public String type;
	public String url;
	public boolean used;
	public String who;
}
