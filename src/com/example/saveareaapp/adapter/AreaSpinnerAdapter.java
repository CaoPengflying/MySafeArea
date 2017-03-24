package com.example.saveareaapp.adapter;

import java.util.List;

import com.example.saveareaapp.R;
import com.example.saveareaapp.adapter.AreaAdapter.HolderView;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.Car;
import com.example.saveareaapp.bean.Interaction;
import com.example.saveareaapp.bean.Shop;
import com.example.saveareaapp.view.yximageview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AreaSpinnerAdapter extends BaseAdapter {
	
	private Context context;
	private List<Area> list;

	public AreaSpinnerAdapter(){
		
	}
	public AreaSpinnerAdapter(Context context,List<Area> list){
		this.context = context;
		this.list = list;
	}	
	public void AddLists(List<Area> list){
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	public void SetLists(List<Area> list){
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
					R.layout.text, parent, false);
			holderView = new HolderView();
			holderView.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
			convertView.setTag(holderView);
		}else{
			holderView = (HolderView) convertView.getTag();
		}
		

		setText(position,holderView);
		
		return convertView;
	}
	private void setText(int position, HolderView holderView) {
		holderView.tv_text.setText(list.get(position).getName());
		
	}
	public class HolderView{
		TextView tv_text;
	}
}
