package com.example.saveareaapp.activity;

import java.util.ArrayList;

import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.R.menu;
import com.example.saveareaapp.activity.AddInfoPropertyActivity.MyOnPageChangeListener;
import com.example.saveareaapp.adapter.ViewPageAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailInteractionActivity extends BaseActivity implements OnClickListener {
	private ImageView iv_back;
	private ViewPager vPager_DetailInteraction;
	private TextView tv_huifu_DetailInteraction;
	private LinearLayout ll_review_DetailInteraction;
	
	private ViewPageAdapter vpAdapter;
	private ArrayList<View> viewpagerList = new ArrayList<View>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_detail_interaction);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_detail_interaction);
		init();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtDetailInter);
		vPager_DetailInteraction = (ViewPager) findViewById(R.id.vPager_DetailInteraction);
		tv_huifu_DetailInteraction = (TextView) findViewById(R.id.tv_huifu_DetailInteraction);
		ll_review_DetailInteraction = (LinearLayout) findViewById(R.id.ll_review_DetailInteraction);
		tv_huifu_DetailInteraction.setTextColor(getResources().getColor(R.color.policebule));
		tv_huifu_DetailInteraction.setOnClickListener(new MyOnClickListener(0));
		ll_review_DetailInteraction.setOnClickListener(new MyOnClickListener(1));
		
		
		iv_back.setOnClickListener(this);
		LayoutInflater li = getLayoutInflater();
		
		viewpagerList.add(li.inflate(R.layout.page_guanfanghuifu, null));
		viewpagerList.add(li.inflate(R.layout.page_review, null));
		
		vpAdapter = new ViewPageAdapter(viewpagerList);
		vPager_DetailInteraction.setAdapter(vpAdapter);
		
		ll_review_DetailInteraction.setOnClickListener(new MyOnClickListener(1));
		tv_huifu_DetailInteraction.setOnClickListener(new MyOnClickListener(0));
		vPager_DetailInteraction.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	/** 
	 *     
	 * 头标点击监听 3 */
	private class MyOnClickListener implements OnClickListener{
       private int index=0;
       public MyOnClickListener(int i){
       	index=i;
       }
		public void onClick(View v) {

			tv_huifu_DetailInteraction.setTextColor(getResources().getColor(R.color.black));
			ll_review_DetailInteraction.setBackgroundColor(getResources().getColor(R.color.white));
			vPager_DetailInteraction.setCurrentItem(index);
			switch(index){
			case 0:
				tv_huifu_DetailInteraction.setTextColor(getResources().getColor(R.color.policebule));
				
				break;
			case 1:
				ll_review_DetailInteraction.setBackgroundColor(getResources().getColor(R.color.policebule));
				
				break;
			}
		}
		
	}
	public class MyOnPageChangeListener implements OnPageChangeListener{

    	
		public void onPageScrollStateChanged(int arg0) {
			
			
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
			
		}

		public void onPageSelected(int arg0) {
			
			tv_huifu_DetailInteraction.setTextColor(getResources().getColor(R.color.black));
			ll_review_DetailInteraction.setBackgroundColor(getResources().getColor(R.color.white));
			
			switch(arg0){
			case 0:
				tv_huifu_DetailInteraction.setTextColor(getResources().getColor(R.color.policebule));
				break;
			case 1:
				ll_review_DetailInteraction.setBackgroundColor(getResources().getColor(R.color.policebule));
				break;
			}
		}

    	
    }
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.iv_back_AtDetailInter){

			this.finish();
		}
		
	}
}
