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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dtr.settingview.lib.entity.SettingData;
import com.dtr.settingviewlib.R;

public class CheckItemViewV extends FrameLayout {

	private LayoutInflater mInflater = null;

	private LinearLayout mItemViewContainer = null;

	private TextView mTitle = null;
	private TextView mSubTitle = null;

	private ImageView mCheck = null;
	private ImageView mDrawable = null;

	private View mItemView = null;
	private onCheckItemChangedListener mChangedListener = null;

	public CheckItemViewV(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public CheckItemViewV(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
		readAttrs(context, attrs);
	}

	private void init(Context context) {
		mInflater = LayoutInflater.from(context);
		mItemView = mInflater.inflate(R.layout.setting_view_check_item_v, null);
		addView(mItemView);

		mTitle = (TextView) mItemView.findViewById(R.id.setting_view_check_item_v_title);
		mSubTitle = (TextView) mItemView.findViewById(R.id.setting_view_check_item_v_subtitle);
		mDrawable = (ImageView) mItemView.findViewById(R.id.setting_view_check_item_v_icon);
		mCheck = (ImageView) mItemView.findViewById(R.id.setting_view_check_item_v_check);
		mItemViewContainer = (LinearLayout) mItemView.findViewById(R.id.setting_view_check_item_v_container);

		mItemViewContainer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mCheck.getVisibility() == View.GONE) {
					mCheck.setVisibility(View.VISIBLE);
					if (null != mChangedListener) {
						mChangedListener.onCheckItemChanged(true);
					}
				} else {
					mCheck.setVisibility(View.GONE);
					if (null != mChangedListener) {
						mChangedListener.onCheckItemChanged(false);
					}
				}
			}
		});
	}

	private void readAttrs(Context context, AttributeSet attrs) {
		if (null != attrs) {
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SettingViewItem);
			if (a.hasValue(R.styleable.SettingViewItem_check)) {
				Drawable drawable = a.getDrawable(R.styleable.SettingViewItem_check);
				if (null != drawable) {
					mCheck.setImageDrawable(drawable);
				} else {
					mCheck.setImageResource(R.drawable.setting_view_check);
				}
			}

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

			if (a.hasValue(R.styleable.SettingViewItem_subTitle)) {
				String subTitle = a.getString(R.styleable.SettingViewItem_subTitle);
				if (!TextUtils.isEmpty(subTitle)) {
					mSubTitle.setText(subTitle);
				}
			}

			if (a.hasValue(R.styleable.SettingViewItem_subTitleColor)) {
				ColorStateList colors = a.getColorStateList(R.styleable.SettingViewItem_subTitleColor);
				if (null != colors) {
					mSubTitle.setTextColor(colors);
				}
			}

			if (a.hasValue(R.styleable.SettingViewItem_subTitleSize)) {
				int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, a.getDimensionPixelSize(R.styleable.SettingViewItem_subTitleSize, 12), getResources()
						.getDisplayMetrics());
				mSubTitle.setTextSize(textSize);
			}

			if (a.hasValue(R.styleable.SettingViewItem_clickable)) {
				mItemViewContainer.setClickable(a.getBoolean(R.styleable.SettingViewItem_clickable, true));
			} else {
				mItemViewContainer.setClickable(true);
			}

			if (a.hasValue(R.styleable.SettingViewItem_checked)) {
				boolean isChecked = a.getBoolean(R.styleable.SettingViewItem_checked, false);
				if (isChecked) {
					mCheck.setVisibility(View.VISIBLE);
				} else {
					mCheck.setVisibility(View.GONE);
				}
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

			if (!TextUtils.isEmpty(data.getSubTitle())) {
				mSubTitle.setText(data.getSubTitle());
			} else {
				mTitle.setVisibility(View.GONE);
			}

			if (null != data.getDrawable()) {
				mDrawable.setImageDrawable(data.getDrawable());
			} else {
				mDrawable.setVisibility(View.GONE);
			}

			if (null != data.getCheck()) {
				mCheck.setImageDrawable(data.getCheck());
			} else {
				mCheck.setImageResource(R.drawable.setting_view_check);
			}

			if (null != data.getBackground()) {
				mItemViewContainer.setBackgroundDrawable(data.getBackground());
			} else {
				mItemViewContainer.setBackgroundResource(R.drawable.setting_view_item_selector);
			}

			if (data.getTitleColor() > 0) {
				mTitle.setTextColor(data.getTitleColor());
			}

			if (data.getTitleSize() > 0) {
				int titleSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, data.getTitleSize(), getResources().getDisplayMetrics());
				mTitle.setTextSize(titleSize);
			}

			if (data.getSubTitleColor() > 0) {
				mSubTitle.setTextColor(data.getSubTitleColor());
			}

			if (data.getSubTitleSize() > 0) {
				int subTitleSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, data.getSubTitleSize(), getResources().getDisplayMetrics());
				mSubTitle.setTextSize(subTitleSize);
			}

			if (data.isChecked()) {
				mCheck.setVisibility(View.VISIBLE);
			} else {
				mCheck.setVisibility(View.GONE);
			}
		}
	}

	public interface onCheckItemChangedListener {
		public void onCheckItemChanged(boolean isChecked);
	}

	public void setOnCheckItemChangedListener(onCheckItemChangedListener listener) {
		mChangedListener = listener;
	}
}
