package com.dtr.settingview.lib.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.CheckBox;

import com.dtr.settingviewlib.R;

@SuppressLint("HandlerLeak")
public class SwitchButton extends CheckBox {
	private Paint mPaint;
	private RectF mSaveLayerRectF;
	private float mFirstDownY;
	private float mFirstDownX;
	private int mClickTimeout;
	private int mTouchSlop;
	private final int MAX_ALPHA = 255;
	private int mAlpha = MAX_ALPHA;
	private boolean mChecked = true;
	private boolean mBroadcasting;// 标示是否正在执行监听事件中
	private boolean mTurningOn;// 标示位置是否达到开启状态
	private PerformClick mPerformClick;
	private OnCheckedChangeListener mOnCheckedChangeListener;
	private OnCheckedChangeListener mOnCheckedChangeWidgetListener;
	private boolean mAnimating;// 标示是否继续执行移动动画
	private final float VELOCITY = 350;// 定义按钮动画移动的最大长度
	private float mVelocity;// 按钮动画移动的最大像素长度
	private float mAnimationPosition;// 按钮动画移动的当前位置
	private float mAnimatedVelocity;// 按钮动画移动的实际位移(+mVelocity/-mVelocity)
	private Bitmap bmBgGreen;// 绿色背景
	private Bitmap bmBgWhite;// 白色背景
	private Bitmap bmBtnNormal;// 未按下时按钮
	private Bitmap bmBtnPressed;// 按下时按钮
	private Bitmap bmCurBtnPic;// 当前显示的按钮图片
	private Bitmap bmCurBgPic;// 当前背景图片
	private float bgWidth;// 背景宽度
	private float bgHeight;// 背景宽度
	private float btnWidth;// 按钮宽度
	private float offBtnPos;// 按钮关闭时位置
	private float onBtnPos;// 按钮开启时位置
	private float curBtnPos;// 按钮当前位置
	private float startBtnPos;// 开始按钮位置

	public SwitchButton(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.checkboxStyle);
	}

	public SwitchButton(Context context) {
		this(context, null);
	}

	public SwitchButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		Resources resources = context.getResources();

		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.switch_button_width), getResources()
				.getDisplayMetrics());
		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.switch_button_height), getResources()
				.getDisplayMetrics());

		// get viewConfiguration
		mClickTimeout = ViewConfiguration.getPressedStateDuration() + ViewConfiguration.getTapTimeout();
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

		// get Bitmap
		bmBgGreen = BitmapFactory.decodeResource(resources, R.drawable.switch_btn_bg_green);
		bmBgWhite = BitmapFactory.decodeResource(resources, R.drawable.switch_btn_bg_white);
		bmBtnNormal = BitmapFactory.decodeResource(resources, R.drawable.switch_btn_normal);
		bmBtnPressed = BitmapFactory.decodeResource(resources, R.drawable.switch_btn_pressed);

		// size Bitmap
		bmBgGreen = Bitmap.createScaledBitmap(bmBgGreen, width, height, true);
		bmBgWhite = Bitmap.createScaledBitmap(bmBgWhite, width, height, true);
		bmBtnNormal = Bitmap.createScaledBitmap(bmBtnNormal, height, height, true);
		bmBtnPressed = Bitmap.createScaledBitmap(bmBtnPressed, height, height, true);

		bmCurBtnPic = bmBtnNormal;// 初始按钮图片
		bmCurBgPic = mChecked ? bmBgGreen : bmBgWhite;// 初始背景图片
		bgWidth = bmBgGreen.getWidth();// 背景宽度
		bgHeight = bmBgGreen.getHeight();// 背景高度
		btnWidth = bmBtnNormal.getWidth();// 按钮宽度
		offBtnPos = 0;// 关闭时在最左边
		onBtnPos = bgWidth - btnWidth;// 开始时在右边
		curBtnPos = mChecked ? onBtnPos : offBtnPos;// 按钮当前为初始位置

		// get density
		float density = resources.getDisplayMetrics().density;
		mVelocity = (int) (VELOCITY * density + 0.5f);// 动画距离
		mSaveLayerRectF = new RectF(0, 0, bgWidth, bgHeight);
	}

	@Override
	public void setEnabled(boolean enabled) {
		mAlpha = enabled ? MAX_ALPHA : MAX_ALPHA / 3;
		super.setEnabled(enabled);
	}

	public boolean isChecked() {
		return mChecked;
	}

	public void toggle() {
		setChecked(!mChecked);
	}

	private void setCheckedDelayed(final boolean checked) {
		postDelayed(new Runnable() {
			@Override
			public void run() {
				setChecked(checked);
			}
		}, 10);
	}

	public void setChecked(boolean checked) {
		if (mChecked != checked) {
			mChecked = checked;

			// 初始化按钮位置
			curBtnPos = checked ? onBtnPos : offBtnPos;
			// 改变背景图片
			bmCurBgPic = checked ? bmBgGreen : bmBgWhite;
			invalidate();

			if (mBroadcasting) {
				// NO-OP
				return;
			}
			// 正在执行监听事件
			mBroadcasting = true;
			if (mOnCheckedChangeListener != null) {
				mOnCheckedChangeListener.onCheckedChanged(SwitchButton.this, mChecked);
			}
			if (mOnCheckedChangeWidgetListener != null) {
				mOnCheckedChangeWidgetListener.onCheckedChanged(SwitchButton.this, mChecked);
			}
			// 监听事件结束
			mBroadcasting = false;
		}
	}

	public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
		mOnCheckedChangeListener = listener;
	}

	void setOnCheckedChangeWidgetListener(OnCheckedChangeListener listener) {
		mOnCheckedChangeWidgetListener = listener;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float x = event.getX();
		float y = event.getY();
		float deltaX = Math.abs(x - mFirstDownX);
		float deltaY = Math.abs(y - mFirstDownY);
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			ViewParent mParent = getParent();
			if (mParent != null) {
				// 通知父控件不要拦截本view的触摸事件
				mParent.requestDisallowInterceptTouchEvent(true);
			}
			mFirstDownX = x;
			mFirstDownY = y;
			bmCurBtnPic = bmBtnPressed;
			startBtnPos = mChecked ? onBtnPos : offBtnPos;
			break;
		case MotionEvent.ACTION_MOVE:
			float time = event.getEventTime() - event.getDownTime();
			curBtnPos = startBtnPos + event.getX() - mFirstDownX;
			if (curBtnPos >= onBtnPos) {
				curBtnPos = onBtnPos;
			}
			if (curBtnPos <= offBtnPos) {
				curBtnPos = offBtnPos;
			}
			mTurningOn = curBtnPos > bgWidth / 2 - btnWidth / 2;
			break;
		case MotionEvent.ACTION_UP:
			bmCurBtnPic = bmBtnNormal;
			time = event.getEventTime() - event.getDownTime();
			if (deltaY < mTouchSlop && deltaX < mTouchSlop && time < mClickTimeout) {
				if (mPerformClick == null) {
					mPerformClick = new PerformClick();
				}
				if (!post(mPerformClick)) {
					performClick();
				}
			} else {
				startAnimation(mTurningOn);
			}
			break;
		}
		invalidate();
		return isEnabled();
	}

	private class PerformClick implements Runnable {
		public void run() {
			performClick();
		}
	}

	@Override
	public boolean performClick() {
		startAnimation(!mChecked);
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.saveLayerAlpha(mSaveLayerRectF, mAlpha, Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
				| Canvas.CLIP_TO_LAYER_SAVE_FLAG);

		// 绘制底部图片
		canvas.drawBitmap(bmCurBgPic, 0, 0, mPaint);

		// 绘制按钮
		canvas.drawBitmap(bmCurBtnPic, curBtnPos, 0, mPaint);

		canvas.restore();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension((int) bgWidth, (int) bgHeight);
	}

	private void startAnimation(boolean turnOn) {
		mAnimating = true;
		mAnimatedVelocity = turnOn ? mVelocity : -mVelocity;
		mAnimationPosition = curBtnPos;
		new SwitchAnimation().run();
	}

	private void stopAnimation() {
		mAnimating = false;
	}

	private final class SwitchAnimation implements Runnable {
		@Override
		public void run() {
			if (!mAnimating) {
				return;
			}
			doAnimation();
			requestAnimationFrame(this);
		}
	}

	private void doAnimation() {
		mAnimationPosition += mAnimatedVelocity * ANIMATION_FRAME_DURATION / 1000;
		if (mAnimationPosition <= offBtnPos) {
			stopAnimation();
			mAnimationPosition = offBtnPos;
			setCheckedDelayed(false);
		} else if (mAnimationPosition >= onBtnPos) {
			stopAnimation();
			mAnimationPosition = onBtnPos;
			setCheckedDelayed(true);
		}
		curBtnPos = mAnimationPosition;
		invalidate();
	}

	private static final int MSG_ANIMATE = 1000;
	public static final int ANIMATION_FRAME_DURATION = 1000 / 60;

	public void requestAnimationFrame(Runnable runnable) {
		Message message = new Message();
		message.what = MSG_ANIMATE;
		message.obj = runnable;
		mHandler.sendMessageDelayed(message, ANIMATION_FRAME_DURATION);
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message m) {
			switch (m.what) {
			case MSG_ANIMATE:
				if (m.obj != null) {
					((Runnable) m.obj).run();
				}
				break;
			}
		}
	};
}
