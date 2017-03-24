package com.example.saveareaapp.activity;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.example.saveareaapp.R;
import com.example.saveareaapp.bean.User;
import com.example.saveareaapp.config.Myconfig;
import com.example.saveareaapp.fragment.AnnunciateFragment;
import com.example.saveareaapp.fragment.InteractionFragment;
import com.example.saveareaapp.fragment.PersonFragment;
import com.example.saveareaapp.fragment.FistPageFragment;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.GetUser;
import com.example.saveareaapp.util.MapToString;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;

import android.R.layout;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends BaseFragmentActivity implements OnClickListener {
	
	private User user = new User();

	
	private int identity=0;//用户身份
	
	
	private LinearLayout ll_home_main,ll_interaction_main,ll_annunciate_main,ll_person_main,ll_send_main;

	private ImageView iv_home_main,iv_interaction_main,iv_annunciate_main,iv_person_main;
	private TextView tv_home_main,tv_interaction_main,tv_annunciate_main,tv_person_main;
	
	private FragmentManager fragment_main;
	
	private FistPageFragment fistPageFragment;//��ҳ
	private AnnunciateFragment annunciateFragment;//ͨ��
	private InteractionFragment interactionFragment;//����
	private PersonFragment personFragment;//�ҵ�
	private int page=0;
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_main);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		init();
		getintent();
		setMainAt();
		
		
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	/**
	 * 设置主页面的fragment
	 */
	private void setMainAt() {
		fragment_main = getSupportFragmentManager();
		FragmentTransaction Transaction = fragment_main.beginTransaction();
		
		switch(page){
		case 0:
        	setSelected();
        	iv_home_main.setImageResource(R.drawable.homeing);
        	tv_home_main.setTextColor(getResources().getColor(R.color.policebule));
        	fistPageFragment = new FistPageFragment(identity);
            Transaction.add(R.id.fragment_main, fistPageFragment).commit();
        	
        	
        	break;
		case 1:
        	setSelected();
        	iv_interaction_main.setImageResource(R.drawable.interactioning);
        	tv_interaction_main.setTextColor(getResources().getColor(R.color.policebule));
        	interactionFragment = new InteractionFragment();
            Transaction.add(R.id.fragment_main, interactionFragment).commit();
        	break;
		case 2:
        	setSelected();
        	iv_annunciate_main.setImageResource(R.drawable.annunciateing);
        	tv_annunciate_main.setTextColor(getResources().getColor(R.color.policebule));
        	annunciateFragment = new AnnunciateFragment();
            Transaction.add(R.id.fragment_main, annunciateFragment).commit();
        	break;
		case 3:
			setSelected();
			iv_person_main.setImageResource(R.drawable.personing);
        	tv_person_main.setTextColor(getResources().getColor(R.color.policebule));
			personFragment = new PersonFragment();
			Transaction.add(R.id.fragment_main, personFragment).commit();
			break;
		}
		
	}
	/**
	 * 获取登录页面的用户身份
	 */
	private void getintent() {
		GetUser get = new GetUser(this);
		user = get.getinfo();
		System.out.println("user="+JSON.toJSONString(user));
		identity = user.getType();
		
	}
	private void init() {
		ll_home_main = (LinearLayout) findViewById(R.id.ll_home_AtMain);
		ll_interaction_main = (LinearLayout) findViewById(R.id.ll_interaction_AtMain);
		ll_annunciate_main = (LinearLayout) findViewById(R.id.ll_annuciate_main);
		ll_person_main = (LinearLayout) findViewById(R.id.ll_person_main);
		ll_send_main = (LinearLayout) findViewById(R.id.ll_send_AtMain);
		
		iv_home_main = (ImageView) findViewById(R.id.iv_home_main);
		iv_interaction_main = (ImageView) findViewById(R.id.iv_interaction_main);
		iv_annunciate_main = (ImageView) findViewById(R.id.iv_annunciate_AtMain);
		iv_person_main = (ImageView) findViewById(R.id.iv_person_AtMain);
		tv_home_main = (TextView) findViewById(R.id.tv_home_main);
		tv_interaction_main = (TextView) findViewById(R.id.tv_interaction_main);
		tv_annunciate_main = (TextView) findViewById(R.id.tv_annunciate_main);
		tv_person_main = (TextView) findViewById(R.id.tv_person_main);
		
		
		
		
		ll_home_main.setOnClickListener(this);
		ll_interaction_main.setOnClickListener(this);
		ll_annunciate_main.setOnClickListener(this);
		ll_person_main.setOnClickListener(this);
		ll_send_main.setOnClickListener(this);
		
	}


	
	/**
	 * 设置为未选中
	 */
	private void setSelected() {
		iv_person_main.setImageResource(R.drawable.personed);
		iv_annunciate_main.setImageResource(R.drawable.annunciated);
    	iv_interaction_main.setImageResource(R.drawable.interactioned);
    	iv_home_main.setImageResource(R.drawable.homed);

    	tv_home_main.setTextColor(getResources().getColor(R.color.comment_Gray));
    	tv_person_main.setTextColor(getResources().getColor(R.color.comment_Gray));
    	tv_annunciate_main.setTextColor(getResources().getColor(R.color.comment_Gray));
    	tv_interaction_main.setTextColor(getResources().getColor(R.color.comment_Gray));
	}
	/**
	 * ����fragment
	 * @param fTransaction
	 */
	private void hideAllFragment(FragmentTransaction fTransaction) {
		if(fistPageFragment!=null)
			fTransaction.hide(fistPageFragment);
		if(interactionFragment!=null)
			fTransaction.hide(interactionFragment);
		if(annunciateFragment!=null)
			fTransaction.hide(annunciateFragment);
		if(personFragment!=null)
			fTransaction.hide(personFragment);
		
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.ll_send_AtMain){
			Intent intent = new Intent(this, SendInteractionActivity.class);
			startActivity(intent);
		}else{

		FragmentTransaction fTransaction = fragment_main.beginTransaction();
		hideAllFragment(fTransaction);
		switch(v.getId()){
		case R.id.ll_home_AtMain:
			page=0;
			setSelected();
			iv_home_main.setImageResource(R.drawable.homeing);
        	tv_home_main.setTextColor(getResources().getColor(R.color.policebule));
        
            if(fistPageFragment==null){
            	fistPageFragment = new FistPageFragment();
                fTransaction.add(R.id.fragment_main, fistPageFragment);
            }else{
            	fTransaction.show(fistPageFragment);
            }
        	break;

		case R.id.ll_interaction_AtMain:
			page=1;
			setSelected();
			iv_interaction_main.setImageResource(R.drawable.interactioneing);
        	tv_interaction_main.setTextColor(getResources().getColor(R.color.policebule));

        	if(interactionFragment==null){
        		interactionFragment = new InteractionFragment();
                fTransaction.add(R.id.fragment_main, interactionFragment);
        	}else{
        		fTransaction.show(interactionFragment);
            }
        	break;
        case R.id.ll_annuciate_main:
    			page=2;
    			setSelected();
    			iv_annunciate_main.setImageResource(R.drawable.annunciateing);
            	tv_annunciate_main.setTextColor(getResources().getColor(R.color.policebule));

            	if(annunciateFragment==null){
            		annunciateFragment = new AnnunciateFragment();
                    fTransaction.add(R.id.fragment_main, annunciateFragment);
            	}else{
            		fTransaction.show(annunciateFragment);
                }
            	break;
    	case R.id.ll_person_main:
    			page=3;
    			setSelected();
    			iv_person_main.setImageResource(R.drawable.personeing);
            	tv_person_main.setTextColor(getResources().getColor(R.color.policebule));

            	if(personFragment==null){
            		personFragment = new PersonFragment();
                    fTransaction.add(R.id.fragment_main, personFragment);
            	}else{
            		fTransaction.show(personFragment);
                }
            	break;

    		}

            fTransaction.commit();
		}
	}
	
	
	
	
	
	
	
	
}
