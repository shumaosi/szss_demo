package com.szss.androidapp.module.imui;

import com.alibaba.mobileim.fundamental.model.YWIMSmiley;
import com.alibaba.mobileim.ui.chat.widget.YWSmilyMgr;
import com.szss.androidapp.R;

/**
 * 自定义表情示例
 * 自定义表情，SDK允许开发者替换（新增）现有的表情内容
 * <p>
 * 先约定一下几个概念
 * 1.表情快捷键(shortCuts)，用于在协议中传输的表情符号
 * 2.表情含义(meanings)，表情中文含义，一般用于显示在通知栏中
 * 3.表情资源ID号(smilyRes)，这个好理解，就是Android的资源ID
 * <p>
 * 开发者通过设置以上三个值来进行表情的功能的扩展，以上三个值的个数是一致的
 */
public class SmilyCustomSample {

	/**
	 * 该方式初始化表情会默认覆盖SDK自带表情包
	 */
	public static void init() {
		hideDefaultSmiley();
		addEmojiSmiley();
		addImageSmiley();
	}

	/**
	 * 导入新的表情包示例代码
	 */
	private static void addEmojiSmiley() {
		YWIMSmiley smiley = new YWIMSmiley(emojiResArray, shortCuts, meanings, 4, 3);
		YWSmilyMgr.setSmileyIndicatorRes(smiley, R.drawable.emoji_smile);
		YWSmilyMgr.addNewSmiley(smiley);
	}

	/**
	 * 添加新的表情包,该方法添加的表情是图片类型
	 */
	private static void addImageSmiley() {
		YWIMSmiley smiley = new YWIMSmiley(imageResArray);
		smiley.setHorizontalCount(4);
		smiley.setVerticalCount(2);
		YWSmilyMgr.setSmileyIndicatorRes(smiley, R.drawable.emoji173);
		YWSmilyMgr.setSmileySize(YWIMSmiley.SMILEY_TYPE_IMAGE, 150);
		YWSmilyMgr.addNewSmiley(smiley);
	}

	/**
	 * 隐藏默认表情，注意此方法需要在添加前调用
	 */
	private static void hideDefaultSmiley() {
		YWSmilyMgr.hideDefaultSmiley();
	}

	/**
	 * 添加默认表情
	 */
	private static void addDefaultSmiley() {
		YWSmilyMgr.addDefaultSmiley();
	}

	public static int[] emojiResArray = new int[]{
			R.drawable.emoji1571,
			R.drawable.emoji1572,
			R.drawable.emoji1573,
			R.drawable.emoji1574,
			R.drawable.emoji1575,
			R.drawable.emoji1576,
			R.drawable.emoji1577,
			R.drawable.emoji1578,
			R.drawable.emoji1579,
			R.drawable.emoji1580,
			R.drawable.emoji1581,
			R.drawable.emoji1582,
			R.drawable.emoji1583,
			R.drawable.emoji1584,
			R.drawable.emoji1585};

	public static int[] imageResArray = new int[]{
			R.drawable.emoji173,
			R.drawable.image1,
			R.drawable.image2,
			R.drawable.image3,
			R.drawable.image4,
			R.drawable.image5,
			R.drawable.image6,
			R.drawable.image7,
			R.drawable.image8,
			R.drawable.image9,
			R.drawable.image10,
			R.drawable.image11,
			R.drawable.image12,
			R.drawable.image13,
			R.drawable.image14,
			R.drawable.image15,
			R.drawable.launch_page1};

	private static String[] shortCuts = new String[]{"/:^_^", "/:^$^", "/:Q",
			"/:815", "/:809", "/:^O^", "/:081", "/:087", "/:086", "/:H",
			"/:012", "/:806", "/:b", "/:^x^", "/:814"};

	private static String[] meanings = new String[]{"微笑", "害羞", "吐舌头", "偷笑",
			"爱慕", "大笑", "跳舞", "飞吻", "安慰", "抱抱", "加油", "胜利", "强", "亲亲", "花痴"};
}
