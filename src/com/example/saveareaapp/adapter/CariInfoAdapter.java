package com.example.saveareaapp.adapter;

import java.util.List;

import com.example.saveareaapp.R;
import com.example.saveareaapp.adapter.AreaAdapter.HolderView;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.Car;
import com.example.saveareaapp.view.yximageview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CariInfoAdapter extends BaseAdapter {
	
	private Context context;
	private List<Car> list;

	public CariInfoAdapter(){
		
	}
	
	public void AddLists(List<Car> list){
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	public void SetLists(List<Car> list){
		this.list = list;
		notifyDataSetChanged();
	}
	public CariInfoAdapter(Context context,List<Car> list){
		this.context = context;
		this.list = list;
	}
	public CariInfoAdapter(Context context){
		this.context = context;
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
					R.layout.adapter_car_info, parent, false);
			holderView = new HolderView();
			holderView.tv_name_AdCarInfo = (TextView) convertView.findViewById(R.id.tv_name_AdCarInfo);
			holderView.tv_colorAndBrand_AdCarInfo = (TextView) convertView.findViewById(R.id.tv_colorAndBrand_AdCarInfo);
			holderView.tv_CarNum_AdPopulationInfo = (TextView) convertView.findViewById(R.id.tv_CarNum_AdPopulationInfo);
			convertView.setTag(holderView);
		}else{
			holderView = (HolderView) convertView.getTag();
		}
		

		setText(position,holderView);
		
		return convertView;
	}
	private void setText(int position, HolderView holderView) {
		holderView.tv_name_AdCarInfo.setText(list.get(position).getOwner());
		holderView.tv_colorAndBrand_AdCarInfo.setText(list.get(position).getColor()+list.get(position).getBrand());
		holderView.tv_CarNum_AdPopulationInfo.setText(list.get(position).getPlateNum());
		
	}
	public class HolderView{
		TextView tv_name_AdCarInfo;
		TextView tv_colorAndBrand_AdCarInfo;
		TextView tv_CarNum_AdPopulationInfo;
		
	}
}
