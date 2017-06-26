package com.szss.androidapp.util;

/**
 * Created by wuwei on 2017/6/26. 获取需要用逗号进行连接的内容。用于将 ArrayList<model> 拼接成 String fidList 的形式
 * <p>
 * see Util.splice(? extends Spliceable)
 */
public interface Spliceable {
	String getSpliceString();
}
