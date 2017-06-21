package com.szss.androidapp.common.model;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by wuwei on 2017/6/21.
 */

public class CardStyle1Model implements Serializable {

	private static final long serialVersionUID = 1740531025822079349L;

	private String photoUrl;
	private String title;
	private String userIconUrl;
	private String userName;
	private String replyNumber;
	private String viewNumber;
	//	https://b-ssl.duitang.com/uploads/item/201512/25/20151225094712_aFfvC.thumb.700_0.jpeg


	public CardStyle1Model() {

	}

	public CardStyle1Model(String photoUrl, String title, String userIconUrl, String userName, String replyNumber, String viewNumber) {
		this.photoUrl = photoUrl;
		this.title = title;
		this.userIconUrl = userIconUrl;
		this.userName = userName;
		this.replyNumber = replyNumber;
		this.viewNumber = viewNumber;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		try {
			out.writeObject(photoUrl);
			out.writeObject(title);
			out.writeObject(userIconUrl);
			out.writeObject(userName);
			out.writeObject(replyNumber);
			out.writeObject(viewNumber);
		} catch (Exception e) {

		}
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		try {
			photoUrl = (String) in.readObject();
			title = (String) in.readObject();
			userIconUrl = (String) in.readObject();
			userName = (String) in.readObject();
			replyNumber = (String) in.readObject();
			viewNumber = (String) in.readObject();
		} catch (Exception e) {

		}
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserIconUrl() {
		return userIconUrl;
	}

	public void setUserIconUrl(String userIconUrl) {
		this.userIconUrl = userIconUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReplyNumber() {
		return replyNumber;
	}

	public void setReplyNumber(String replyNumber) {
		this.replyNumber = replyNumber;
	}

	public String getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(String viewNumber) {
		this.viewNumber = viewNumber;
	}

}
