package com.dtr.settingview.examples;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.dtr.settingview.lib.SettingButton;
import com.dtr.settingview.lib.SettingButton.onSettingButtonClickListener;
import com.dtr.settingview.lib.SettingButton.onSettingButtonSwitchListener;
import com.dtr.settingview.lib.SettingView;
import com.dtr.settingview.lib.entity.SettingData;
import com.dtr.settingview.lib.entity.SettingViewItemData;
import com.dtr.settingview.lib.item.BasicItemViewH;
import com.dtr.settingview.lib.item.CheckItemViewV;
import com.dtr.settingview.lib.item.ImageItemView;
import com.dtr.settingview.lib.item.SwitchItemView;

public class QQStyleActivity extends Activity {

	private SettingView mSettingView1 = null;
	private SettingView mSettingView2 = null;
	private SettingView mSettingView3 = null;
	private SettingButton mSettingButton1 = null;
	private SettingButton mSettingButton2 = null;
	private SettingButton mSettingButton3 = null;

	private SettingData mItemData = null;
	private SettingViewItemData mItemViewData = null;
	private List<SettingViewItemData> mListData = new ArrayList<SettingViewItemData>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qq_style);

		mSettingView1 = (SettingView) findViewById(R.id.qq_style_setting_view_01);
		mSettingView2 = (SettingView) findViewById(R.id.qq_style_setting_view_02);
		mSettingView3 = (SettingView) findViewById(R.id.qq_style_setting_view_03);
		mSettingButton1 = (SettingButton) findViewById(R.id.qq_style_setting_button1);
		mSettingButton2 = (SettingButton) findViewById(R.id.qq_style_setting_button2);
		mSettingButton3 = (SettingButton) findViewById(R.id.qq_style_setting_button3);

		mSettingButton1.setOnSettingButtonClickListener(new onSettingButtonClickListener() {

			@Override
			public void onSettingButtonClick() {
				// TODO Auto-generated method stub
				Toast.makeText(QQStyleActivity.this, "点击了SettingButton1", Toast.LENGTH_SHORT).show();
			}
		});

		mSettingButton2.setOnSettingButtonSwitchListener(new onSettingButtonSwitchListener() {

			@Override
			public void onSwitchChanged(boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Toast.makeText(QQStyleActivity.this, "SettingButton2开启", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(QQStyleActivity.this, "SettingButton2关闭", Toast.LENGTH_SHORT).show();
				}
			}
		});

		initView();
	}

	private void initView() {
		/* ==========================SettingView1========================== */
		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("流量更新");

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("2G/3G/4G下自动接收图片");
		mItemData.setChecked(true);

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new SwitchItemView(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("2G/3G/4G下自动接收魔法动画");
		mItemData.setChecked(false);

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new SwitchItemView(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("Wifi下自动在后台下载新版本");
		mItemData.setChecked(true);

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new SwitchItemView(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mSettingView1.setAdapter(mListData);
		/* ==========================SettingView1========================== */

		/* ==========================SettingView2========================== */
		mListData.clear();
		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("消息通知");

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("聊天记录");

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mSettingView2.setAdapter(mListData);
		/* ==========================SettingView2========================== */
		/* ==========================SettingView3========================== */
		mListData.clear();
		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("空间动态");
		mItemData.setDrawable(getResources().getDrawable(R.drawable.qq_icon01));

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("文件助手");
		mItemData.setDrawable(getResources().getDrawable(R.drawable.qq_icon02));

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("扫一扫");
		mItemData.setDrawable(getResources().getDrawable(R.drawable.qq_icon03));

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("附近的人");
		mItemData.setDrawable(getResources().getDrawable(R.drawable.qq_icon04));

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("游戏");
		mItemData.setDrawable(getResources().getDrawable(R.drawable.qq_icon05));

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(QQStyleActivity.this));
		mListData.add(mItemViewData);

		mSettingView3.setAdapter(mListData);
		/* ==========================SettingView3========================== */
		/* ==========================SettingButton1========================== */
		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("账号管理");
		mItemData.setInfo(getResources().getDrawable(R.drawable.icon06));

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new ImageItemView(QQStyleActivity.this));
		mSettingButton1.setAdapter(mItemViewData);
		/* ==========================SettingButton1========================== */
		/* ==========================SettingButton2========================== */
		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("接收消息");
		mItemData.setInfo(getResources().getDrawable(R.drawable.icon04));

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new SwitchItemView(QQStyleActivity.this));
		mSettingButton2.setAdapter(mItemViewData);
		/* ==========================SettingButton2========================== */
		/* ==========================SettingButton3========================== */
		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("强迫症患者");
		mItemData.setSubTitle("1076559197");
		mItemData.setDrawable(getResources().getDrawable(R.drawable.icon01));
		mItemData.setChecked(true);

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new CheckItemViewV(QQStyleActivity.this));

		mSettingButton3.setAdapter(mItemViewData);
		/* ==========================SettingButton3========================== */
	}
}
