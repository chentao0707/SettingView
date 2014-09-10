package com.dtr.settingview.lib.item;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dtr.settingview.lib.entity.SettingData;
import com.dtr.settingviewlib.R;

public class SwitchItemView extends FrameLayout {

	private LayoutInflater mInflater = null;

	private LinearLayout mItemViewContainer = null;

	private TextView mTitle = null;

	private SwitchButton mSwitch = null;
	private ImageView mDrawable = null;

	private View mItemView = null;
	private onSwitchItemChangedListener mChangedListener = null;

	public SwitchItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public SwitchItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
		readAttrs(context, attrs);
	}

	private void init(Context context) {
		mInflater = LayoutInflater.from(context);
		mItemView = mInflater.inflate(R.layout.setting_view_switch_item, null);
		addView(mItemView);

		mTitle = (TextView) mItemView.findViewById(R.id.setting_view_switch_item_title);
		mDrawable = (ImageView) mItemView.findViewById(R.id.setting_view_switch_item_icon);
		mSwitch = (SwitchButton) mItemView.findViewById(R.id.setting_view_switch_item_switch);
		mItemViewContainer = (LinearLayout) mItemView.findViewById(R.id.setting_view_switch_item_container);

		mItemViewContainer.setClickable(false);

		mSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (null != mChangedListener) {
					mChangedListener.onSwitchItemChanged(isChecked);
				}
			}
		});
	}

	private void readAttrs(Context context, AttributeSet attrs) {
		if (null != attrs) {
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SettingViewItem);

			if (a.hasValue(R.styleable.SettingViewItem_background)) {
				Drawable drawable = a.getDrawable(R.styleable.SettingViewItem_background);
				if (null != drawable) {
					mItemViewContainer.setBackgroundDrawable(drawable);
				} else {
					mItemViewContainer.setBackgroundResource(R.drawable.setting_view_item_selector);
				}
			}

			if (a.hasValue(R.styleable.SettingViewItem_drawable)) {
				Drawable drawable = a.getDrawable(R.styleable.SettingViewItem_drawable);
				if (null != drawable) {
					mDrawable.setImageDrawable(drawable);
				} else {
					mDrawable.setVisibility(View.GONE);
				}
			} else {
				mDrawable.setVisibility(View.GONE);
			}

			if (a.hasValue(R.styleable.SettingViewItem_title)) {
				String title = a.getString(R.styleable.SettingViewItem_title);
				if (!TextUtils.isEmpty(title)) {
					mTitle.setText(title);
				}
			}

			if (a.hasValue(R.styleable.SettingViewItem_titleColor)) {
				ColorStateList colors = a.getColorStateList(R.styleable.SettingViewItem_titleColor);
				if (null != colors) {
					mTitle.setTextColor(colors);
				}
			}

			if (a.hasValue(R.styleable.SettingViewItem_titleSize)) {
				int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, a.getDimensionPixelSize(R.styleable.SettingViewItem_titleSize, 16), getResources()
						.getDisplayMetrics());
				mTitle.setTextSize(textSize);
			}

			if (a.hasValue(R.styleable.SettingViewItem_clickable)) {
				mItemViewContainer.setClickable(a.getBoolean(R.styleable.SettingViewItem_clickable, false));
			} else {
				mItemViewContainer.setClickable(true);
			}

			if (a.hasValue(R.styleable.SettingViewItem_checked)) {
				mSwitch.setChecked(a.getBoolean(R.styleable.SettingViewItem_checked, false));
			}

			a.recycle();
		}
	}

	public void fillData(SettingData data) {
		if (null != data) {
			if (!TextUtils.isEmpty(data.getTitle())) {
				mTitle.setText(data.getTitle());
			} else {
				mTitle.setVisibility(View.GONE);
			}

			if (null != data.getDrawable()) {
				mDrawable.setImageDrawable(data.getDrawable());
			} else {
				mDrawable.setVisibility(View.GONE);
			}

			if (null != data.getBackground()) {
				mItemViewContainer.setBackgroundDrawable(data.getBackground());
			} else {
				mItemViewContainer.setBackgroundResource(R.drawable.setting_view_item_selector);
			}

			mSwitch.setChecked(data.isChecked());

			if (data.getTitleColor() > 0) {
				mTitle.setTextColor(data.getTitleColor());
			}

			if (data.getTitleSize() > 0) {
				int titleSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, data.getTitleSize(), getResources().getDisplayMetrics());
				mTitle.setTextSize(titleSize);
			}
		}
	}

	public interface onSwitchItemChangedListener {
		public void onSwitchItemChanged(boolean isChecked);
	}

	public void setOnSwitchItemChangedListener(onSwitchItemChangedListener listener) {
		mChangedListener = listener;
	}

	public TextView getmTitle() {
		return mTitle;
	}

	public SwitchButton getmSwitch() {
		return mSwitch;
	}

	public ImageView getmDrawable() {
		return mDrawable;
	}
}
