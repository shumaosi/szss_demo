package com.szss.androidapp.profile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.szss.androidapp.R;
import com.szss.androidapp.action.ActionName;
import com.szss.androidapp.base.BaseRecycleViewAdapter;
import com.szss.androidapp.common.viewholder.HorizontalListViewHolder;
import com.szss.androidapp.common.viewholder.SpaceViewHolder;
import com.szss.androidapp.model.ProfileItem;
import com.szss.androidapp.common.view.RoundedImageView;

/**
 * Created by wuwei on 2017/6/16.
 */

public class ProfileAdapter extends BaseRecycleViewAdapter {

	public static final int TYPE_PROFILE_INFO = 1;
	public static final int TYPE_SPACE = 2;
	public static final int TYPE_FAVORABLE = 3;
	public static final int TYPE_SETTING = 4;
	public static final int TYPE_NOTIFICATION = 5;
	public static final int TYPE_MESSAGE = 6;
	public static final int TYPE_CAPTURE = 7;

	private ProfileActionClickListener mProfileActionClickListener;

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		if (TYPE_PROFILE_INFO == viewType) {
			View infoView = layoutInflater.inflate(R.layout.profileinfo_view, parent, false);
			return new ProfileInfoViewHolder(infoView);
		} else if (TYPE_SPACE == viewType) {
			View view = layoutInflater.inflate(R.layout.space_item, parent, false);
			return new SpaceViewHolder(view);
		} else if (TYPE_FAVORABLE == viewType) {
			View view = layoutInflater.inflate(R.layout.horizontal_list_item, parent, false);
			return new HorizontalListViewHolder(view);
		} else {
			View view = layoutInflater.inflate(R.layout.profile_imagetext_view, parent, false);
			return new ProfileImageTextViewHolder(view);
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		final int itemType = getItemViewType(position);
		if (itemType == TYPE_SETTING
				|| itemType == TYPE_NOTIFICATION
				|| itemType == TYPE_MESSAGE
				|| itemType == TYPE_CAPTURE) {
			bindImageTextViewHolderData((ProfileImageTextViewHolder) holder, itemType);
		} else if (itemType == TYPE_PROFILE_INFO) {

		}
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (itemType == TYPE_CAPTURE) {
					if (mProfileActionClickListener != null) {
						mProfileActionClickListener.profileActionClick(ActionName.ProfilePage_CaptureAction, null);
					}
				}
			}
		});
	}

	@Override
	public int getItemViewType(int position) {
		ProfileItem profileItem = (ProfileItem) getDataList().get(position);
		switch (profileItem.getType()) {
			case profileInfo:
				return TYPE_PROFILE_INFO;
			case space:
				return TYPE_SPACE;
			case favorable:
				return TYPE_FAVORABLE;
			case setting:
				return TYPE_SETTING;
			case notification:
				return TYPE_NOTIFICATION;
			case message:
				return TYPE_MESSAGE;
			case capture:
				return TYPE_CAPTURE;
			default:
				return TYPE_SPACE;
		}
	}

	private class ProfileInfoViewHolder extends RecyclerView.ViewHolder {

		private RoundedImageView icon;
		private TextView name;
		private TextView des;

		public ProfileInfoViewHolder(View itemView) {
			super(itemView);
			icon = (RoundedImageView) itemView.findViewById(R.id.profileinfo_view_icon);
			name = (TextView) itemView.findViewById(R.id.profileinfo_view_name);
			des = (TextView) itemView.findViewById(R.id.profileinfo_view_des);
			icon.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mProfileActionClickListener != null) {
						mProfileActionClickListener.profileActionClick(ActionName.ProfilePage_UserInfoIconClickAction, icon);
					}
				}
			});
		}
	}

	private void bindProfileInfoViewHodler(final ProfileInfoViewHolder profileInfoViewHolder) {
		profileInfoViewHolder.icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
	}

	private static class ProfileImageTextViewHolder extends RecyclerView.ViewHolder {

		private ImageView icon;
		private TextView des;

		public ProfileImageTextViewHolder(View itemView) {
			super(itemView);
			icon = (ImageView) itemView.findViewById(R.id.profile_imagetext_view_image);
			des = (TextView) itemView.findViewById(R.id.profile_imagetext_view_text);
		}
	}

	private void bindImageTextViewHolderData(ProfileImageTextViewHolder profileImageTextViewHolder, int viewType) {
		int imageRes;
		String des;
		switch (viewType) {
			case TYPE_SETTING:
				imageRes = R.drawable.ic_settings_black_24dp;
				des = profileImageTextViewHolder.itemView.getContext().getString(R.string.setting);
				break;
			case TYPE_NOTIFICATION:
				imageRes = R.drawable.ic_notifications_black_24dp;
				des = profileImageTextViewHolder.itemView.getContext().getString(R.string.notification);
				break;
			case TYPE_MESSAGE:
				imageRes = R.drawable.ic_message_black_24dp;
				des = profileImageTextViewHolder.itemView.getContext().getString(R.string.message);
				break;
			case TYPE_CAPTURE:
				imageRes = R.drawable.ic_home_black_24dp;
				des = "扫码";
				break;
			default:
				imageRes = R.drawable.ic_settings_black_24dp;
				des = profileImageTextViewHolder.itemView.getContext().getString(R.string.setting);
				break;
		}
		profileImageTextViewHolder.icon.setImageResource(imageRes);
		profileImageTextViewHolder.des.setText(des);
	}

	public void setProfileActionClickListener(ProfileActionClickListener profileActionClickListener) {
		mProfileActionClickListener = profileActionClickListener;
	}

	public interface ProfileActionClickListener {
		void profileActionClick(ActionName actionName, View view);
	}

}
