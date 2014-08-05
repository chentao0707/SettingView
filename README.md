#![Logo](https://github.com/SkillCollege/SettingView/blob/master/SettingViewExamples/res/drawable-hdpi/ic_launcher.png) SettingView

SettingView是类似主流应用的设置界面用到的控件，主要思路来源于iPhone的设置界面


##Examples Screenshots

![screenshots01](https://github.com/SkillCollege/SettingView/blob/master/ScreenShots/Screenshot_01.png)
![screenshots02](https://github.com/SkillCollege/SettingView/blob/master/ScreenShots/Screenshot_02.png)
![screenshots03](https://github.com/SkillCollege/SettingView/blob/master/ScreenShots/Screenshot_03.png)
![screenshots04](https://github.com/SkillCollege/SettingView/blob/master/ScreenShots/Screenshot_04.png)

##Features

>* 支持XML手动布局，XML属性设置

>* 支持动态添加

>* 数据源适配通过类似setAdapter方法提供

>* 当然你还可以扩展更多...


##Usage
####1.下载SettingViewLibrary导入Eclipse作为项目库

####2.在布局中添加自定义属性并引用SettingView

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFF7F7F7"
    android:gravity="center_horizontal"
    android:orientation="vertical" >

    <com.dtr.settingview.lib.SettingView
        android:id="@+id/main_setting_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:iOSStyle="false" />

</LinearLayout>
```

####3.Activity中设置数据源

```Java
private void initView() {
		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("仿QQ界面效果");

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(MainActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("仿iOS设置界面");

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(MainActivity.this));
		mListData.add(mItemViewData);

		mItemViewData = new SettingViewItemData();
		mItemData = new SettingData();
		mItemData.setTitle("单个Item布局效果");

		mItemViewData.setData(mItemData);
		mItemViewData.setItemView(new BasicItemViewH(MainActivity.this));
		mListData.add(mItemViewData);

		mSettingView.setAdapter(mListData);
	}
```

####4.设置事件监听

```Java
mSettingView.setOnSettingViewItemClickListener(new onSettingViewItemClickListener() {

			@Override
			public void onItemClick(int index) {
				// TODO Auto-generated method stub
				switch (index) {
				case 0:
					startActivity(new Intent(MainActivity.this, QQStyleActivity.class));
					break;
				case 1:
					startActivity(new Intent(MainActivity.this, IosStyleActivity.class));

					break;
				case 2:
					startActivity(new Intent(MainActivity.this, XMLLayoutActivity.class));

					break;

				default:
					break;
				}
			}
		});
```

####5.属性解释

>* iOSStyle：是否使用类似iPhone中的设置界面效果，也就是分割线的样式

>* title：标题文字

>* subTitle：副标题文字

>* titleColor：标题文字颜色

>* subTitleColor：副标题文字颜色

>* titleSize：标题文字大小

>* subTitleSize：副标题文字大小

>* drawable：控件ItemView的Icon资源

>* arrow：箭头的资源文件

>* check：当控件类型是选择框时的标记图片资源

>* image：当控件的副标题是图片类型时的默认图片资源

>* clickable：控件初始时是否可以点击，大部分都是可以点击的，只是开关控件会不同

>* checked：控件初始时是否是选中状态，作用于开关选项和单选选项

>* background：控件整体的背景资源

    
####6.回调方法：

```Java

// Item控件被点击时的监听方法，index即为操作时的下标
public interface onSettingViewItemClickListener {
	void onItemClick(int index);
}
	
// Item控件中为开关时的监听方法，index即为操作时的下标，isChecked即为是否选中
public interface onSettingViewItemSwitchListener {
	public void onSwitchChanged(int index, boolean isChecked);
}

```

###7.更多使用请参考SettingViewExamples示例程序


##License
```java
/*
 * Copyright (C) 2014 Chen Tao <1076559197@qq.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
```
