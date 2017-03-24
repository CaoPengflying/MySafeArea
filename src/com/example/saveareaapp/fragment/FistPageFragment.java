package com.example.saveareaapp.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.saveareaapp.R;
import com.example.saveareaapp.activity.AddInfoActivity;
import com.example.saveareaapp.activity.AddInfoPropertyActivity;
import com.example.saveareaapp.activity.DetailAnnunciateActivity;
import com.example.saveareaapp.activity.DetailInteractionActivity;
import com.example.saveareaapp.activity.DetailNewActivity;
import com.example.saveareaapp.activity.ExhibitionActivity;
import com.example.saveareaapp.activity.MesageActivity;
import com.example.saveareaapp.activity.ServiceActivity;
import com.example.saveareaapp.adapter.SpinnerAdapter;
import com.example.saveareaapp.bean.User;
import com.example.saveareaapp.config.Myconfig;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.MapToString;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;
import com.example.saveareaapp.view.SlideShowView;
import com.example.saveareaapp.view.VerticalTextview;
import com.example.saveareaapp.view.VerticalTextview.OnItemClickListener;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class FistPageFragment extends Fragment implements OnClickListener, OnItemClickListener {
	private User user = new User();
	
	private static int identity=0;
	private LinearLayout ll_test;
	private ImageView iv_message_FrfistPage;//��Ϣ
	private SlideShowView	slideshowView_FrfistPage;//ͼƬ�ֲ�
	private VerticalTextview tv_guangbo_FrfistPage;//�����ֲ�
	private LinearLayout ll_boast_FrfistPage,ll_query_FrfistPage,ll_broke_FrfistPage,
	ll_certificates_FrfistPage;//ƽ����������ѯ���������飬��֤����
	private Button bt_addinfo_FrfistPage;//��Ϣ�ɼ�
	private Button bt_service_FrfistPage;//�������
	private Button bt_exhibition_FrfistPage;//���չʾ
	
	private Spinner sp_selectArea_fistpage;
	private SpinnerAdapter adapter;
	
	private LinearLayout ll_interaction_1_fpf,ll_interaction_2_Frfistpage;//��������
	private LinearLayout ll_showId_FrFistpage;
	private ImageView iv_head_1_FrfistPage,iv_head_2_Frfistpage;//��������ͷ��
	private TextView tv_username_2_Frfistpage,tv_username_1_FrfistPage;//������������
	private TextView tv_time_2_Frfistpage,tv_time_1_FrfistPage;//��������ʱ��
	private TextView tv_word_2_Frfistpage,tv_word_1_FrfistPage;//������������
	private ImageView iv_policeInteraction_21_Frfistpage,iv_policeInteraction_22_Frfistpage,iv_policeInteraction_23_Frfistpage,
	iv_policeInteraction_11_Frfistpage,iv_policeInteraction_12_Frfistpage,iv_policeInteraction_13_Frfistpage;
	private TextView tv_huifu_1_Frfistpage,tv_huifu_2_Frfistpage ;//�����ظ�����Ҫƴ�����磺�ٷ��ظ���5����
	private TextView tv_review_1_Frfistpage,tv_review_2_Frfistpage ;//�������۸���
	private TextView tv_agree_1_Frfistpage,tv_agree_2_Frfistpage ;//�������޸���
	private ImageView iv_agree_1_Frfistpage,iv_agree_2_Frfistpage;//���ް�ť
	
	

	private AsyncTaskHelper asyncTaskHelper;//异步加载网络
	private OnDataDownloadListener onDataDownloadListener;//异步加载网络监听器
	
	private ArrayList<String> listText;
	private View view;
	
	
	public FistPageFragment(){
		
	}
	public FistPageFragment(int identity){
		this.identity = identity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_fistpage, container,false);
		
	    init();//��ȡ�ؼ�������
	    initText();
	    showNew();
	    showActivity();
		return view;
	}

	/**
	 * 不同的身份展示不同的界面
	 */
	private void showActivity() {
		System.out.println(identity);
		if(identity==0){
			ll_showId_FrFistpage.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
		}
		
	}
	private void showNew() {
		/**
         * ����ͼƬ��Ҫ�Ǵ���ͼƬ·����������int���͵ģ�Ҳ �����񲩿��е�һ����String���͵�
         */
        List<Integer> imageUris=new ArrayList<Integer>();
        imageUris.add(R.drawable.one);  
        imageUris.add(R.drawable.two);  
        imageUris.add(R.drawable.three);
        /**
         * Ϊ�ؼ�����ͼƬ
         */
        slideshowView_FrfistPage.setImageUris(imageUris);
        slideshowView_FrfistPage.setOnClickListener(this);
        
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
		super.onResume();
		tv_guangbo_FrfistPage.startAutoScroll();
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		tv_guangbo_FrfistPage.stopAutoScroll();
	}
	/**
	 * 显示轮播广告
	 */
	private void initText() {
		listText = new ArrayList<String>();
		listText.add("紧急通告！小区内有窃贼，个位小心");
		listText.add("8月5号的矛盾纠纷已解决");
		listText.add("广场舞大妈跳舞得奖了");
		
		tv_guangbo_FrfistPage.setTextList(listText);
		tv_guangbo_FrfistPage.setText(12, 4, getResources().getColor(R.color.gray));
		tv_guangbo_FrfistPage.setTextStillTime(10000);// ����ͣ��ʱ�����
		tv_guangbo_FrfistPage.setAnimTime(4000);// ���ý�����˳���ʱ����
		
	}
	private void init() {
		
		sp_selectArea_fistpage = (Spinner) view.findViewById(R.id.sp_selectArea_fistpage);
		slideshowView_FrfistPage = (SlideShowView) view.findViewById(R.id.slideshowView_FrfistPage);
		tv_guangbo_FrfistPage = (VerticalTextview) view.findViewById(R.id.tv_guangbo_FrfistPage);
		bt_addinfo_FrfistPage = (Button) view.findViewById(R.id.bt_addinfo_FrfistPage);
		bt_service_FrfistPage = (Button) view.findViewById(R.id.bt_service_FrfistPage);
		bt_exhibition_FrfistPage = (Button) view.findViewById(R.id.bt_exhibition_FrfistPage);
		ll_interaction_1_fpf = (LinearLayout) view.findViewById(R.id.ll_interaction_1_fpf);
		ll_showId_FrFistpage = (LinearLayout) view.findViewById(R.id.ll_showId_FrFistpage);
		ll_interaction_2_Frfistpage = (LinearLayout) view.findViewById(R.id.ll_interaction_2_Frfistpage);
		iv_message_FrfistPage = (ImageView) view.findViewById(R.id.iv_message_FrfistPage);
		
		
		iv_message_FrfistPage.setOnClickListener(this);
		bt_addinfo_FrfistPage.setOnClickListener(this);
		bt_service_FrfistPage.setOnClickListener(this);
		bt_exhibition_FrfistPage.setOnClickListener(this);
		ll_interaction_1_fpf.setOnClickListener(this);
		ll_interaction_2_Frfistpage.setOnClickListener(this);
		
		slideshowView_FrfistPage.setOnClickListener(this);
		
		tv_guangbo_FrfistPage.setOnItemClickListener(this);
		
		ArrayList<String> list  = new ArrayList<String>();
		list.add("七里湖派出所");
		list.add("庐山区派出所");
		list.add("二亩派出所");
		list.add("南湖派出所");
		
		adapter = new SpinnerAdapter(getActivity(), list);
		sp_selectArea_fistpage.setAdapter(adapter);
		asyncTaskHelper = new AsyncTaskHelper();
		getHomePageInfo();

		
	}
private void getHomePageInfo() {
		
		onDataDownloadListener = new OnDataDownloadListener() {
			
			@Override
			public void onDataDownload(byte[] result) {
				String reString = "{\"datas\":{\"interactions\":[{\"ID\":1,\"title\":\"小区的垃圾要收了\",\"content\":\"昨天的风真大，这不，13日中午大风就袭击了埠河镇的一个家庭农场，一栋板房被大风吹垮了，屋顶更是飞到了几十米外。\",\"releaseID\":4,\"releaseTime\":\"2018-01-08\",\"photos\":[{\"id\":1,\"objId\":1,\"path\":\"d:/\",\"type\":1}],\"agreeNum\":10,\"isDelete\":0,\"replies\":\"\",\"releaseName\":\"\"}],\"news\":[{\"id\":1,\"title\":\"4.12省领导慰问\",\"content\":\"两会结束后，领导关心基层的生活，前来慰问\",\"realeaseID\":1,\"headlineMarker\":true,\"isDelete\":false,\"releaseTime\":\"2014-02-03\",\"photos\":[{\"id\":1,\"objId\":1,\"path\":\"d:/\",\"type\":1}],\"releaseName\":\"张干警\"},{\"id\":2,\"title\":\"3.09省领导检查\",\"content\":\"我们申请了先进派出所\",\"realeaseID\":1,\"headlineMarker\":true,\"isDelete\":false,\"releaseTime\":\"2014-02-03\",\"photos\":[{\"id\":1,\"objId\":1,\"path\":\"d:/\",\"type\":1}],\"releaseName\":\"张干警\"}],\"notices\":[{\"id\":1,\"title\":\"4.12省领导慰问\",\"content\":\"两会结束后，领导关心基层的生活，前来慰问\",\"realeaseID\":1,\"headlineMarker\":true,\"isDelete\":false,\"releaseTime\":\"2014-02-03\",\"releaseName\":\"张干警\"},{\"id\":2,\"title\":\"3.09省领导检查\",\"content\":\"我们申请了先进派出所\",\"realeaseID\":1,\"headlineMarker\":true,\"isDelete\":false,\"releaseTime\":\"2014-02-03\",\"releaseName\":\"张干警\"}]},\"success\":true,\"msg\":\"OK\"}";
				
			
			}
		};
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("method", 0);
		String paramters = MapToString.getUrlParamsByMap(map);
		String url = Myconfig.ADRESS + "HomePageServlet";
		asyncTaskHelper.downloadData(getActivity(), url, paramters, onDataDownloadListener);
		
	}
	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch(v.getId()){
		case R.id.iv_message_FrfistPage:
			intent = new Intent(getActivity(), DetailAnnunciateActivity.class);
			break;
		case R.id.bt_addinfo_FrfistPage:
			if(identity==1){
				intent = new Intent(getActivity(), AddInfoPropertyActivity.class);
			}else{
			intent = new Intent(getActivity(), AddInfoActivity.class);
			}
			break;
		case R.id.bt_service_FrfistPage:
			intent = new Intent(getActivity(), ServiceActivity.class);
			break;
		case R.id.bt_exhibition_FrfistPage:
			intent = new Intent(getActivity(), ExhibitionActivity.class);
			break;
		case R.id.ll_interaction_1_fpf:
			intent = new Intent(getActivity(), DetailInteractionActivity.class);
			break;
		case R.id.ll_interaction_2_Frfistpage:
			intent = new Intent(getActivity(), DetailInteractionActivity.class);
			break;
		case R.id.slideshowView_FrfistPage:
			intent = new Intent(getActivity(), DetailNewActivity.class);
			break;
		}
		startActivity(intent);
		
	}


	@Override
	public void onItemClick(int position) {
		Intent intent_guangbo = new Intent(getActivity(),DetailAnnunciateActivity.class);
		startActivity(intent_guangbo);
		
	}
}
