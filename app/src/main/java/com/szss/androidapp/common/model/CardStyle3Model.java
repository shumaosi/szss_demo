package com.szss.androidapp.common.model;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by wuwei on 2017/6/21.
 */

public class CardStyle3Model implements Serializable {

	private static final long serialVersionUID = 1740531025822079349L;

	public static int CARD_MARGING = 10;
	public static int ROW_NUMBER = 2;

	private String photoUrl;
	private String title;
	private String content;
	private String userName;
	private String source;
	private String viewNumber;

	public CardStyle3Model() {

	}

	public CardStyle3Model(String photoUrl, String title, String source, String viewNumber) {
		this.photoUrl = photoUrl;
		this.title = title;
		this.source = source;
		this.viewNumber = viewNumber;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		try {
			out.writeObject(photoUrl);
			out.writeObject(title);
			out.writeObject(content);
			out.writeObject(userName);
			out.writeObject(source);
			out.writeObject(viewNumber);
		} catch (Exception e) {

		}
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		try {
			photoUrl = (String) in.readObject();
			title = (String) in.readObject();
			content = (String) in.readObject();
			userName = (String) in.readObject();
			source = (String) in.readObject();
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(String viewNumber) {
		this.viewNumber = viewNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
