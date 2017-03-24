package com.example.saveareaapp.adapter;

import java.util.List;

import com.example.saveareaapp.R;
import com.example.saveareaapp.adapter.AreaAdapter.HolderView;
import com.example.saveareaapp.bean.Car;
import com.example.saveareaapp.bean.House;
import com.example.saveareaapp.bean.Interaction;
import com.example.saveareaapp.view.yximageview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopulationInfoAdapter extends BaseAdapter {
	
	private Context context;
	private List<House> list;

	public PopulationInfoAdapter(){
		
	}
	public PopulationInfoAdapter(Context context,List<House> list){
		this.context = context;
		this.list = list;
	}	
	public PopulationInfoAdapter(Context context){
		this.context = context;
	}	
	public void AddLists(List<House> list){
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	public void SetLists(List<House> list){
		this.list = list;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.adapter_population_info, parent, false);
			holderView = new HolderView();
			holderView.tv_name_AdPopulationInfo = (TextView) convertView.findViewById(R.id.tv_name_AdPopulationInfo);
			//holderView.tv_phone_AdPopulationInfo = (TextView) convertView.findViewById(R.id.tv_phone_AdPopulationInfo);
			holderView.tv_address_AdPopulationInfo = (TextView) convertView.findViewById(R.id.tv_address_AdPopulationInfo);
			holderView.tv_idNum_AdPopulationInfo = (TextView) convertView.findViewById(R.id.tv_idNum_AdPopulationInfo);
			convertView.setTag(holderView);
		}else{
			holderView = (HolderView) convertView.getTag();
		}
		

		setText(position,holderView);
		
		return convertView;
	}
	private void setText(int position, HolderView holderView) {
		//holderView.tv_phone_AdPopulationInfo.setText(list.get(position).getPhone());
		holderView.tv_name_AdPopulationInfo.setText(list.get(position).getOwner());
		holderView.tv_address_AdPopulationInfo.setText(list.get(position).getAddress());
		holderView.tv_idNum_AdPopulationInfo.setText(list.get(position).getOwnerCardID());
		
	}
	public class HolderView{
		TextView tv_name_AdPopulationInfo;
		TextView tv_phone_AdPopulationInfo;
		TextView tv_address_AdPopulationInfo;
		TextView tv_idNum_AdPopulationInfo;
		
	}
}
