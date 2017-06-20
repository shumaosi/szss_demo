package com.szss.androidapp.profile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.szss.androidapp.R;
import com.szss.androidapp.model.ProfileItemModel;
import com.szss.androidapp.view.RoundedImageView;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by wuwei on 2017/6/16.
 */

public class ProfileAdapter extends RecyclerView.Adapter {

	public static final int TYPE_PROFILE_INFO = 1;
	public static final int TYPE_SPACE = 2;
	public static final int TYPE_FAVORABLE = 3;
	public static final int TYPE_SETTING = 4;
	public static final int TYPE_NOTIFICATION = 5;
	public static final int TYPE_MESSAGE = 6;


	private ArrayList<ProfileItemModel> mData;
	private ProfileActionClickListener mProfileActionClickListener;

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		if (TYPE_PROFILE_INFO == viewType) {
			View infoView = layoutInflater.inflate(R.layout.profileinfo_view, parent, false);
			return new ProfileInfoViewHolder(infoView);
		} else if (TYPE_SPACE == viewType) {
			View view = layoutInflater.inflate(R.layout.profile_space_view, parent, false);
			return new ProfileSpaceViewHolder(view);
		} else if (TYPE_FAVORABLE == viewType) {
			View view = layoutInflater.inflate(R.layout.profile_favorable_view, parent, false);
			return new ProfileFavorable(view);
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
				|| itemType == TYPE_MESSAGE) {
			bindImageTextViewHolderData((ProfileImageTextViewHolder) holder, itemType);
		} else if (itemType == TYPE_PROFILE_INFO) {

		}
		RxView.clicks(holder.itemView).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new Observer<Object>() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {

			}

			@Override
			public void onNext(@NonNull Object o) {
				if (mProfileActionClickListener != null) {
					mProfileActionClickListener.profileActionClick(itemType);
				}
			}

			@Override
			public void onError(@NonNull Throwable e) {

			}

			@Override
			public void onComplete() {

			}
		});
	}

	@Override
	public int getItemCount() {
		return getDataList().size();
	}

	@Override
	public int getItemViewType(int position) {
		ProfileItemModel profileItemModel = mData.get(position);
		switch (profileItemModel.getType()) {
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
			default:
				return TYPE_SPACE;
		}
	}

	public ArrayList getDataList() {
		if (mData == null) {
			mData = new ArrayList<>();
		}
		return mData;
	}

	public void addData(ArrayList<ProfileItemModel> list) {
		getDataList().addAll(list);
		notifyDataSetChanged();
	}

	private static class ProfileInfoViewHolder extends RecyclerView.ViewHolder {

		private RoundedImageView icon;
		private TextView name;
		private TextView des;

		public ProfileInfoViewHolder(View itemView) {
			super(itemView);
			icon = (RoundedImageView) itemView.findViewById(R.id.profileinfo_view_icon);
			name = (TextView) itemView.findViewById(R.id.profileinfo_view_name);
			des = (TextView) itemView.findViewById(R.id.profileinfo_view_des);
		}
	}

	private static class ProfileSpaceViewHolder extends RecyclerView.ViewHolder {

		public ProfileSpaceViewHolder(View itemView) {
			super(itemView);
		}
	}

	private static class ProfileFavorable extends RecyclerView.ViewHolder {

		public ProfileFavorable(View itemView) {
			super(itemView);
		}

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
		void profileActionClick(int viewType);
	}

}
