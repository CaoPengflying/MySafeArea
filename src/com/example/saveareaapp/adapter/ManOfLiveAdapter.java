package com.example.saveareaapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.saveareaapp.R;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.Resident;
import com.example.saveareaapp.view.yximageview;

import android.R.string;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ManOfLiveAdapter extends BaseAdapter {
	
	List<Resident> list = new ArrayList<Resident>();
	private Context context;

	public ManOfLiveAdapter (){
		
	}
	public ManOfLiveAdapter (List<Resident> resident,Context context){
		this.context = context;
		this.list = resident;
	}
public ManOfLiveAdapter (Context context){
	this.context = context;
}
	
	public void AddLists(List<Resident> resident){
		this.list.addAll(resident);
		notifyDataSetChanged();
	}
	public void SetLists(List<Resident> resident){
		this.list = resident;
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
					R.layout.adapter_idcard, parent, false);
			holderView = new HolderView();
			holderView.tv_sex_AdIdCard = (TextView) convertView.findViewById(R.id.tv_sex_AdIdCard);
			holderView.tv_name_AdIdCard = (TextView) convertView.findViewById(R.id.tv_name_AdIdCard);
			holderView.tv_native_AdIdCard = (TextView) convertView.findViewById(R.id.tv_native_AdIdCard);
			holderView.tv_brith_AdIdCard = (TextView) convertView.findViewById(R.id.tv_brith_AdIdCard);
			holderView.tv_address_AdIdCard = (TextView) convertView.findViewById(R.id.tv_address_AdIdCard);
			holderView.tv_idNum_AdIdCard = (TextView) convertView.findViewById(R.id.tv_idNum_AdIdCard);
			holderView.im_photo_AdIdCard = (ImageView) convertView.findViewById(R.id.im_photo_AdIdCard);
			convertView.setTag(holderView);
		}else{
			holderView = (HolderView) convertView.getTag();
		}
		

		setText(position,holderView);
		
		return convertView;
	}
		

	private void setText(int position, HolderView holderView) {
		String id = list.get(position).getCardID();
		String birth = id.substring(6, 14);
		String sexstring = id.substring(16);
		int sexint = Integer.parseInt(sexstring);
		String sex;
		if(sexint%2==0){
			sex="女";
		}else{
			sex="男";
			
		}
		
		holderView.tv_name_AdIdCard.setText(list.get(position).getName());
		holderView.tv_sex_AdIdCard.setText(sex);
		holderView.tv_native_AdIdCard.setText(list.get(position).getNation());
		holderView.tv_brith_AdIdCard.setText(birth);
		holderView.tv_address_AdIdCard.setText(list.get(position).getAddress());
		holderView.tv_idNum_AdIdCard.setText(list.get(position).getCardID());
		holderView.im_photo_AdIdCard.setImageBitmap(list.get(position).getPhoto());
	}


	public class HolderView{
		TextView tv_name_AdIdCard;
		TextView tv_sex_AdIdCard;
		TextView tv_native_AdIdCard;
		TextView tv_brith_AdIdCard;
		TextView tv_address_AdIdCard;
		TextView tv_idNum_AdIdCard;
		ImageView im_photo_AdIdCard;
	}
}
