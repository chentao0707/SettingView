package com.dtr.settingview.lib.entity;

import android.widget.FrameLayout;

public class SettingViewItemData {

	private int id;
	private FrameLayout itemView;
	private SettingData data;
	private boolean isClickable = true;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FrameLayout getItemView() {
		return itemView;
	}

	public void setItemView(FrameLayout itemView) {
		this.itemView = itemView;
	}

	public SettingData getData() {
		return data;
	}

	public void setData(SettingData data) {
		this.data = data;
	}

	public boolean isClickable() {
		return isClickable;
	}

	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
	}
}
