package com.dtr.settingview.lib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dtr.settingview.lib.entity.SettingViewItemData;
import com.dtr.settingview.lib.item.BasicItemViewH;
import com.dtr.settingview.lib.item.BasicItemViewV;
import com.dtr.settingview.lib.item.CheckItemViewH;
import com.dtr.settingview.lib.item.CheckItemViewV;
import com.dtr.settingview.lib.item.ImageItemView;
import com.dtr.settingview.lib.item.SwitchItemView;
import com.dtr.settingview.lib.item.SwitchItemView.onSwitchItemChangedListener;
import com.dtr.settingviewlib.R;

public class SettingButton extends LinearLayout {

	private ImageView mTopDivider;
	private ImageView mBottomDivider;

	private onSettingButtonClickListener mSettingButtonClickListener;
	private onSettingButtonSwitchListener mSettingButtonSwitchListener;

	public SettingButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
		mTopDivider = new ImageView(context);
		mTopDivider.setBackgroundResource(R.drawable.divider);

		mBottomDivider = new ImageView(context);
		mBottomDivider.setBackgroundResource(R.drawable.divider);
	}

	public void setAdapter(SettingViewItemData data) {
		LinearLayout.LayoutParams dividerLps = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		// Add Top Divider
		addView(mTopDivider, dividerLps);
		// Add Content
		initItemView(data);
		// Add Bottom Divider
		addView(mBottomDivider, dividerLps);
	}

	private void initItemView(SettingViewItemData data) {
		FrameLayout itemView = data.getItemView();

		if (itemView instanceof SwitchItemView) {
			((SwitchItemView) itemView).fillData(data.getData());
			itemView.setClickable(false);
			((SwitchItemView) itemView).setOnSwitchItemChangedListener(new onSwitchItemChangedListener() {

				@Override
				public void onSwitchItemChanged(boolean isChecked) {
					// TODO Auto-generated method stub
					if (null != mSettingButtonSwitchListener) {
						mSettingButtonSwitchListener.onSwitchChanged(isChecked);
					}
				}
			});

		} else {
			itemView.setClickable(data.isClickable());
			itemView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (null != mSettingButtonClickListener) {
						mSettingButtonClickListener.onSettingButtonClick();
					}
				}
			});

			if (itemView instanceof BasicItemViewH) {
				((BasicItemViewH) itemView).fillData(data.getData());
			} else if (itemView instanceof BasicItemViewV) {
				((BasicItemViewV) itemView).fillData(data.getData());
			} else if (itemView instanceof ImageItemView) {
				((ImageItemView) itemView).fillData(data.getData());
			} else if (itemView instanceof CheckItemViewH) {
				((CheckItemViewH) itemView).fillData(data.getData());
			} else if (itemView instanceof CheckItemViewV) {
				((CheckItemViewV) itemView).fillData(data.getData());
			}
		}

		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.setting_view_min_height), getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);

		addView(itemView, lps);
	}

	public interface onSettingButtonClickListener {
		void onSettingButtonClick();
	}

	public void setOnSettingButtonClickListener(onSettingButtonClickListener listener) {
		this.mSettingButtonClickListener = listener;
	}

	public interface onSettingButtonSwitchListener {
		public void onSwitchChanged(boolean isChecked);
	}

	public void setOnSettingButtonSwitchListener(onSettingButtonSwitchListener listener) {
		this.mSettingButtonSwitchListener = listener;
	}

	public FrameLayout getItemView() {
		return (FrameLayout) getChildAt(1);
	}

	public void modifyTitle(String title) {
		FrameLayout itemView = getItemView();
		if (itemView instanceof SwitchItemView) {
			((SwitchItemView) itemView).getmTitle().setText(title);
		} else {
			if (itemView instanceof BasicItemViewH) {
				((BasicItemViewH) itemView).getmTitle().setText(title);
			} else if (itemView instanceof BasicItemViewV) {
				((BasicItemViewV) itemView).getmTitle().setText(title);
			} else if (itemView instanceof ImageItemView) {
				((ImageItemView) itemView).getmTitle().setText(title);
			} else if (itemView instanceof CheckItemViewH) {
				((CheckItemViewH) itemView).getmTitle().setText(title);
			} else if (itemView instanceof CheckItemViewV) {
				((CheckItemViewV) itemView).getmTitle().setText(title);
			}
		}
	}

	public void modifySubTitle(String subTitle) {
		FrameLayout itemView = getItemView();
		if (itemView instanceof BasicItemViewH) {
			((BasicItemViewH) itemView).getmSubTitle().setText(subTitle);
		} else if (itemView instanceof BasicItemViewV) {
			((BasicItemViewV) itemView).getmSubTitle().setText(subTitle);
		} else if (itemView instanceof CheckItemViewV) {
			((CheckItemViewV) itemView).getmSubTitle().setText(subTitle);
		}
	}

	public void modifyDrawable(Drawable drawable) {
		FrameLayout itemView = getItemView();
		if (itemView instanceof SwitchItemView) {
			((SwitchItemView) itemView).getmDrawable().setImageDrawable(drawable);
		} else {
			if (itemView instanceof BasicItemViewH) {
				((BasicItemViewH) itemView).getmDrawable().setImageDrawable(drawable);
			} else if (itemView instanceof BasicItemViewV) {
				((BasicItemViewV) itemView).getmDrawable().setImageDrawable(drawable);
			} else if (itemView instanceof ImageItemView) {
				((ImageItemView) itemView).getmDrawable().setImageDrawable(drawable);
			} else if (itemView instanceof CheckItemViewH) {
				((CheckItemViewH) itemView).getmDrawable().setImageDrawable(drawable);
			} else if (itemView instanceof CheckItemViewV) {
				((CheckItemViewV) itemView).getmDrawable().setImageDrawable(drawable);
			}
		}
	}

	public void modifyInfo(Drawable drawable) {
		FrameLayout itemView = getItemView();
		if (itemView instanceof ImageItemView) {
			((ImageItemView) itemView).getmImage().setImageDrawable(drawable);
		}
	}
}
