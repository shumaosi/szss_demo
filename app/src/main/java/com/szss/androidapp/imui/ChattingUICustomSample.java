package com.szss.androidapp.imui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMChattingPageUI;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWMessage;
import com.szss.androidapp.R;

/**
 * 使用该主题的聊天界面自定义风格：［图片切割气泡、文字小猪气泡］风格
 * todo 聊天界面的自定义风格2：［图片切割气泡、文字小猪气泡］风格
 *
 * @author shuheng
 */
public class ChattingUICustomSample extends IMChattingPageUI {

	public ChattingUICustomSample(Pointcut pointcut) {
		super(pointcut);
	}

	/**
	 * 设置左边图片消息汽泡背景图，需要.9图
	 *
	 * @return 0: 默认背景 －1:透明背景（无背景） resId：使用resId对应图片做背景
	 */
	@Override
	public int getLeftImageMsgBackgroundResId() {
//        return R.drawable.demo_talk_pic_pop_l_bg;
//		return 0;
		return -1;
	}


	@Override
	public int getLeftTextMsgBackgroundResId() {
		return R.drawable.demo_talk_pop_l_bg;
//		return 0;
	}

	@Override
	public int getLeftGeoMsgBackgroundResId(YWConversation conversation) {
		return R.drawable.aliwx_comment_l_bg;
	}

	@Override
	public int getLeftCustomMsgBackgroundResId(YWConversation conversation) {
		return R.drawable.aliwx_comment_l_bg;
	}

	/**
	 * 设置右边图片消息汽泡背景图，需要.9图
	 *
	 * @return 0: 默认背景 －1:透明背景（无背景） resId：使用resId对应图片做背景
	 */
	@Override
	public int getRightImageMsgBackgroundResId() {
//        return R.drawable.demo_talk_pic_pop_r_bg;
//		return 0;
		return -1;
	}

	@Override
	public int getRightTextMsgBackgroundResId() {
		return R.drawable.demo_talk_pop_l_bg;
//		return 0;
	}

	@Override
	public int getRightGeoMsgBackgroundResId(YWConversation conversation) {
		return R.drawable.aliwx_comment_r_bg;
	}

	@Override
	public int getRightCustomMsgBackgroundResId(YWConversation conversation) {
		return R.drawable.aliwx_comment_r_bg;
	}

	@Override
	public boolean needRoundChattingImage() {
		return false;
	}

	/**
	 * 设置聊天界面图片圆角的边角半径的长度(单位：dp)
	 *
	 * @return
	 */
	@Override
	public float getRoundRadiusDps() {
		return 12.6f;
	}

	@Override
	public int getChattingBackgroundResId() {
		//聊天窗口背景，默认不显示
		return 0;
		// return R.drawable.demo_chatting_bg;
	}

	private static final int[] THUMNAIL_SIZE = {91700, 367500};
	private static final int[] ROUND_RADIUS_MULTIPLE = {2, 1};

	/**
	 * 智能的根据图片的尺寸计算合适的背景图尺寸
	 *
	 * @param currentSize
	 * @return
	 */
	public static int getBestMultipleTimes(int currentSize) {
		int min = Math.abs(currentSize - THUMNAIL_SIZE[0]);
		int j = 0;
		for (int i = 1; i < THUMNAIL_SIZE.length; i++) {
			if (Math.abs(currentSize - THUMNAIL_SIZE[i]) < min) {
				min = Math.abs(currentSize - THUMNAIL_SIZE[i]);
				j = i;
			}
		}
		return ROUND_RADIUS_MULTIPLE[j];
	}

	/**
	 * 是否隐藏标题栏
	 *
	 * @param fragment
	 * @param conversation
	 * @return
	 */
	@Override
	public boolean needHideTitleView(Fragment fragment, YWConversation conversation) {
//        if (conversation.getConversationType() == YWConversationType.Tribe){
//            return true;
//        }
		//@消息功能需要展示标题栏，暂不隐藏
		return false;
	}


	/**
	 * 定制图片预览页面titlebar右侧按钮点击事件
	 *
	 * @param fragment
	 * @param message  当前显示的图片对应的ywmessage对象
	 * @return true：使用用户定制的点击事件， false：使用默认的点击事件(默认点击事件为保持该图片到本地)
	 */
	@Override
	public boolean onImagePreviewTitleButtonClick(Fragment fragment, YWMessage message) {
		Context context = fragment.getActivity();
		Toast.makeText(context, "你点击了该按钮~", Toast.LENGTH_SHORT).show();
		return false;
	}

	/**
	 * 返回图片保存的目录
	 *
	 * @param fragment
	 * @param message
	 * @return 如果为null, 使用SDK默认的目录, 否则使用用户设置的目录
	 */
	@Override
	public String getImageSavePath(Fragment fragment, YWMessage message) {
//        return Environment
//                .getExternalStorageDirectory().getAbsolutePath()
//                + "/alibaba/WXOPENI/云旺相册";
		return null;
	}

	/**
	 * getView方法内，返回View之前，对［聊天界面的右边消息item的View］做最后调整,如调整View的Padding。
	 *
	 * @param msg
	 * @param rightItemParentView
	 * @param fragment
	 * @param conversation
	 */
	@Override
	public void modifyRightItemParentViewAfterSetValue(YWMessage msg, RelativeLayout rightItemParentView, Fragment fragment, YWConversation conversation) {
		if (msg != null && rightItemParentView != null && (msg.getSubType() == YWMessage.SUB_MSG_TYPE.IM_IMAGE || msg.getSubType() == YWMessage.SUB_MSG_TYPE.IM_GIF)) {
			rightItemParentView.setPadding(rightItemParentView.getPaddingLeft(), rightItemParentView.getPaddingTop(), 0, rightItemParentView.getPaddingBottom());
		}
	}

	/**
	 * getView方法内，返回View之前，对［聊天界面的左边消息item的View］做最后调整,如调整View的Padding。
	 *
	 * @param msg
	 * @param leftItemParentView
	 * @param fragment
	 * @param conversation
	 */
	@Override
	public void modifyLeftItemParentViewAfterSetValue(YWMessage msg, RelativeLayout leftItemParentView, Fragment fragment, YWConversation conversation) {

		if (msg != null && leftItemParentView != null && (msg.getSubType() == YWMessage.SUB_MSG_TYPE.IM_IMAGE || msg.getSubType() == YWMessage.SUB_MSG_TYPE.IM_GIF)) {
			leftItemParentView.setPadding(0, leftItemParentView.getPaddingTop(), leftItemParentView.getPaddingRight(), leftItemParentView.getPaddingBottom());
		}
	}
}
