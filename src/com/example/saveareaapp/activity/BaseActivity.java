package com.example.saveareaapp.activity;

import java.lang.reflect.Field;

import com.example.saveareaapp.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

@SuppressLint("InlinedApi")
public class BaseActivity extends Activity {

	private LinearLayout ll_statusbar;
	private int statusHeight;
	private int layoutId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		initViews();
		initState();
	}

	public int getLayoutId() {
		return layoutId;
	}
	
	public void setLayoutId(int layoutId){
		this.layoutId = layoutId;
	}

	public void initViews() {
		ll_statusbar = (LinearLayout) findViewById(R.id.ll_statusbar);
	}

	private void initState() {
		// 4.4�汾���ϵ�ϵͳ�������Ƴ���ʽЧ��
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// ͸��״̬��
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			ll_statusbar.setVisibility(View.VISIBLE);
			// ��ȡ��״̬���ĸ߶�
			statusHeight = getStatusBarHeight();
			// ��̬���������ز��ֵĸ߶Ⱥ���ɫ��ʹ���Բ����滻�ֻ�ԭʼ״̬��
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_statusbar
					.getLayoutParams();
			params.height = statusHeight;
			ll_statusbar.setLayoutParams(params);
			ll_statusbar.setBackgroundColor(getResources().getColor(R.color.policebule));
		}
	}

	/**
	 * ͨ����ķ�ʽ��ȡ״̬���߶�
	 */
	private int getStatusBarHeight() {
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			return getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
