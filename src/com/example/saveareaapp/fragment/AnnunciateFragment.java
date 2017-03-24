package com.example.saveareaapp.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.saveareaapp.R;
import com.example.saveareaapp.activity.DetailAnnunciateActivity;
import com.example.saveareaapp.activity.DetailInteractionActivity;
import com.example.saveareaapp.adapter.AnnunciateAdapter;
import com.example.saveareaapp.adapter.InteractionAdapter;
import com.example.saveareaapp.bean.Interaction;
import com.example.saveareaapp.bean.Notice;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AnnunciateFragment extends Fragment {
	private View view;

	private ListView lv_annunciate_frAnnu;
	private AnnunciateAdapter adapter;
	private List<Notice> list = new ArrayList<Notice>();
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_annunciate, container,false);
		
	    init();//��ȡ�ؼ�������
	    
		return view;
	}
	private void init() {
		lv_annunciate_frAnnu = (ListView) view.findViewById(R.id.lv_annunciate_frAnnu);
		list.add(new Notice("小区将会大面积停水"));
		list.add(new Notice("小心电瓶车被盗"));
		list.add(new Notice("有犯罪嫌疑人出没"));
		list.add(new Notice("大家不要听信谣言"));
		adapter = new AnnunciateAdapter(getActivity(), list);
		lv_annunciate_frAnnu.setAdapter(adapter);

		
		lv_annunciate_frAnnu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), DetailAnnunciateActivity.class);
				startActivity(intent);
				
			}
		});
	}
}
