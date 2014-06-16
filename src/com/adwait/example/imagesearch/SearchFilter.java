package com.adwait.example.imagesearch;

import java.io.Serializable;

public class SearchFilter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1116328757922739724L;
	String imgSize;
	String imgColor;
	String imgType;
	String site;
	boolean isSet;
	
	public SearchFilter() {
		isSet = false;
	}
	
	public boolean isSet() {
		return this.isSet;
	}
	
	public void setSize(String imageSize) {
		this.isSet = true;
		this.imgSize = new String(imageSize);
	}
	
	public String getSize() {
		return this.imgSize;
	}
	
	public void setColor(String imageColor) {
		this.isSet = true;
		this.imgColor = new String(imageColor);
	}
	
	public String getColor() {
		return this.imgColor;
	}
	
	public void setType(String imageType) {
		this.isSet = true;
		this.imgType = new String(imageType);
	}
	
	public String getType() {
		return this.imgType;
	}
	
	public void setSite(String site) {
		this.isSet = true;
		this.site = site;
	}
	
	public String getSite() {
		return this.site;
	}
	
	
}
