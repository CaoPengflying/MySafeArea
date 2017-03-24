package com.example.saveareaapp.view;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

@SuppressLint("HandlerLeak")
public class VerticalTextview extends TextSwitcher implements
		ViewSwitcher.ViewFactory {

	private static final int FLAG_START_AUTO_SCROLL = 0;
	private static final int FLAG_STOP_AUTO_SCROLL = 1;

	private float mTextSize = 16;
	private int mPadding = 5;
	private int textColor = Color.BLACK;

	/**
	 * @param textSize
	 *            瀛楀�?
	 * @param padding
	 *            鍐呰竟璺�?	 * @param textColor
	 *            瀛椾綋棰滆壊
	 */
	public void setText(float textSize, int padding, int textColor) {
		mTextSize = textSize;
		mPadding = padding;
		this.textColor = textColor;
	}

	private OnItemClickListener itemClickListener;
	private Context mContext;
	private int currentId = -1;
	private ArrayList<String> textList;
	private Handler handler;

	public VerticalTextview(Context context) {
		this(context, null);
		mContext = context;
	}

	public VerticalTextview(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		textList = new ArrayList<String>();
	}

	public void setAnimTime(long animDuration) {
		setFactory(this);
		Animation in = new TranslateAnimation(animDuration,0, 0, 0);
		in.setDuration(animDuration - 2000);
		in.setInterpolator(new AccelerateInterpolator());
		Animation out = new TranslateAnimation(0, -animDuration, 0, 0);
		out.setDuration(animDuration);
		out.setInterpolator(new AccelerateInterpolator());
		setInAnimation(in);
		setOutAnimation(out);
	}

	/**
	 * 闂撮殧鏃堕棿
	 * 
	 * @param time
	 */
	public void setTextStillTime(final long time) {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case FLAG_START_AUTO_SCROLL:
					if (textList.size() > 0) {
						currentId++;
						setText(textList.get(currentId % textList.size()));
					}
					handler.sendEmptyMessageDelayed(FLAG_START_AUTO_SCROLL,
							time);
					break;
				case FLAG_STOP_AUTO_SCROLL:
					handler.removeMessages(FLAG_START_AUTO_SCROLL);
					break;
				}
			}
		};
	}

	/**
	 * 璁剧疆鏁版嵁婧�
	 * 
	 * @param titles
	 */
	public void setTextList(ArrayList<String> titles) {
		textList.clear();
		textList.addAll(titles);
		currentId = -1;
	}

	/**
	 * 寮�婊氬姩
	 */
	public void startAutoScroll() {
		handler.sendEmptyMessage(FLAG_START_AUTO_SCROLL);
	}

	/**
	 * 鍋滄婊氬姩
	 */
	public void stopAutoScroll() {
		handler.sendEmptyMessage(FLAG_STOP_AUTO_SCROLL);
	}

	@Override
	public View makeView() {
		TextView t = new TextView(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.MATCH_PARENT);
		t.setLayoutParams(params);
		t.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		t.setSingleLine(true);
		t.setPadding(mPadding, 0, mPadding, 0);
		t.setTextColor(textColor);
		t.setTextSize(mTextSize);

		t.setClickable(true);
		t.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (itemClickListener != null && textList.size() > 0
						&& currentId != -1) {
					itemClickListener.onItemClick(currentId % textList.size());
				}
			}
		});
		return t;
	}

	/**
	 * 璁剧疆鐐瑰嚮浜嬩欢鐩戝惉
	 * 
	 * @param itemClickListener
	 */
	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	/**
	 * 杞挱鏂囨湰鐐瑰嚮鐩戝惉鍣�
	 */
	public interface OnItemClickListener {
		/**
		 * 鐐瑰嚮鍥炶皟
		 * 
		 * @param position
		 *            褰撳墠鐐瑰嚮ID
		 */
		void onItemClick(int position);
	}

}
