package com.example.saveareaapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.saveareaapp.R;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.view.yximageview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AreaAdapter extends BaseAdapter {
	
	List<Area> list = new ArrayList<Area>();
	private Context context;

	public AreaAdapter (){
		
	}
	public AreaAdapter (List<Area> areas,Context context){
		this.context = context;
		this.list = areas;
	}
	
	public void AddLists(List<Area> areas){
		this.list.addAll(areas);
		notifyDataSetChanged();
	}
	public void SetLists(List<Area> areas){
		this.list = areas;
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
					R.layout.adapter_gridview, parent, false);
			holderView = new HolderView();
			holderView.txt_icon = (TextView) convertView.findViewById(R.id.txt_icon);
			holderView.img_icon = (yximageview) convertView.findViewById(R.id.img_icon);
			convertView.setTag(holderView);
		}else{
			holderView = (HolderView) convertView.getTag();
		}
		

		setText(position,holderView);
		
		return convertView;
	}
		

	private void setText(int position, HolderView holderView) {
		
		
		//holderView.img_icon.setImageResource(context.getResources().getInteger(R.drawable.timg));
		holderView.txt_icon.setText(list.get(position).getName());
	}


	public class HolderView{
		TextView txt_icon;
		yximageview img_icon;
	}
}
