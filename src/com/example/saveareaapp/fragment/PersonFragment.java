package com.example.saveareaapp.fragment;

import com.example.saveareaapp.R;
import com.example.saveareaapp.activity.LOGINActivity;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.LinearLayout;

public class PersonFragment extends Fragment implements OnClickListener {
	private LinearLayout ll_intologin_FrPer;
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_person, container,false);
		
	    init();//��ȡ�ؼ�������
	    
		return view;
	}
	private void init() {
		ll_intologin_FrPer = (LinearLayout)view.findViewById(R.id.ll_intologin_FrPer);
		ll_intologin_FrPer.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(),LOGINActivity.class);
		startActivity(intent);
		getActivity().finish();
	}
}
