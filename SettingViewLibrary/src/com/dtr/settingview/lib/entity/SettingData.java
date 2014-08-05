package com.dtr.settingview.lib.entity;

import android.graphics.drawable.Drawable;

public class SettingData {

	private int id;

	private int titleColor;
	private int subTitleColor;

	private int titleSize;
	private int subTitleSize;

	private Drawable info;
	private Drawable arrow;
	private Drawable check;
	private Drawable drawable;
	private Drawable background;

	private String title;
	private String subTitle;

	private boolean isChecked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(int titleColor) {
		this.titleColor = titleColor;
	}

	public int getSubTitleColor() {
		return subTitleColor;
	}

	public void setSubTitleColor(int subTitleColor) {
		this.subTitleColor = subTitleColor;
	}

	public int getTitleSize() {
		return titleSize;
	}

	public void setTitleSize(int titleSize) {
		this.titleSize = titleSize;
	}

	public int getSubTitleSize() {
		return subTitleSize;
	}

	public void setSubTitleSize(int subTitleSize) {
		this.subTitleSize = subTitleSize;
	}

	public Drawable getInfo() {
		return info;
	}

	public void setInfo(Drawable info) {
		this.info = info;
	}

	public Drawable getArrow() {
		return arrow;
	}

	public void setArrow(Drawable arrow) {
		this.arrow = arrow;
	}

	public Drawable getCheck() {
		return check;
	}

	public void setCheck(Drawable check) {
		this.check = check;
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	public Drawable getBackground() {
		return background;
	}

	public void setBackground(Drawable background) {
		this.background = background;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
