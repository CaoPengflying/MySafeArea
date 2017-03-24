package com.example.saveareaapp.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.saveareaapp.R;
import com.example.saveareaapp.activity.DetailInteractionActivity;
import com.example.saveareaapp.adapter.InteractionAdapter;
import com.example.saveareaapp.bean.Interaction;
import com.example.saveareaapp.util.PullEvenText;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class InteractionFragment extends Fragment implements OnPullEventListener<ListView> {
	private View view;
	private PullToRefreshListView lv_interaction_FrInteraction;
	private InteractionAdapter adapter;
	private List<Interaction> list = new ArrayList<Interaction>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_interaction, container,false);
		
	    init();//��ȡ�ؼ�������
	    
		return view;
	}
	private void init() {
		lv_interaction_FrInteraction = (PullToRefreshListView) view.findViewById(R.id.lv_interaction_FrInteraction);
		Interaction i0 = new Interaction("阳光很好适合晒太阳,希望有关部门能尽快处理掉门口的垃圾就更好了。");
		Interaction i1 = new Interaction("污水到处排放又没有人管啊");
		Interaction i2 = new Interaction("消防栓坏了怎么没人修");
		Interaction i4 = new Interaction("小区环境怎的不好了，从自己做起");
		i0.setImage(R.drawable.new1, R.drawable.new2, R.drawable.new3, R.drawable.head1);
		i1.setImage(R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.head);
		i2.setImage(R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.head1);
		i4.setImage(R.drawable.n7, R.drawable.n8, R.drawable.n9, R.drawable.new3);
		i0.setReleaseName("不沉默");
		i1.setReleaseName("不穿裤子的云");
		i2.setReleaseName("打酱油");
		i4.setReleaseName("先生碎语");
		list.add(i0);
		list.add(i1);
		list.add(i2);
		list.add(i4);
		
		adapter = new InteractionAdapter(getActivity(), list);
		lv_interaction_FrInteraction.setAdapter(adapter);
		
		lv_interaction_FrInteraction.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), DetailInteractionActivity.class);
				startActivity(intent);
				
			}
		});
		
		// 设置下拉刷新和上拉加载的模式：默认只有下拉刷新一种模式
		// 如果需要两种模式，必须要调用setMode方法设置
		lv_interaction_FrInteraction.setMode(Mode.BOTH);
		// 设置下拉刷新和上拉加载的监听器
		lv_interaction_FrInteraction.setOnPullEventListener(this);
		
		
		
		
	}
	@Override
	public void onPullEvent(PullToRefreshBase<ListView> refreshView,
			State state, Mode direction) {
		PullEvenText.SetpullEcen(refreshView, state, direction,getActivity());
		
	}
}
