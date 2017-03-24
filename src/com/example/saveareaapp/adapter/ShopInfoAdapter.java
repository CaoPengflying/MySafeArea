package com.example.saveareaapp.adapter;

import java.util.List;

import com.example.saveareaapp.R;
import com.example.saveareaapp.adapter.AreaAdapter.HolderView;
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

public class ShopInfoAdapter extends BaseAdapter {
	
	private Context context;
	private List<Shop> list;

	public ShopInfoAdapter(){
		
	}
	public ShopInfoAdapter(Context context,List<Shop> list){
		this.context = context;
		this.list = list;
	}	
	public ShopInfoAdapter(Context context){
		this.context = context;
	}	
	public void AddLists(List<Shop> list){
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	public void SetLists(List<Shop> list){
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
					R.layout.adapter_shop_info, parent, false);
			holderView = new HolderView();
			holderView.tv_Shopname_AdShopInfo = (TextView) convertView.findViewById(R.id.tv_Shopname_AdShopInfo);
			holderView.tv_OwerName_AdShopInfo = (TextView) convertView.findViewById(R.id.tv_OwerName_AdShopInfo);
			holderView.tv_phone_AdShopInfo = (TextView) convertView.findViewById(R.id.tv_phone_AdShopInfo);
			holderView.tv_ShopAddress_AdShopInfo = (TextView) convertView.findViewById(R.id.tv_ShopAddress_AdShopInfo);
			convertView.setTag(holderView);
		}else{
			holderView = (HolderView) convertView.getTag();
		}
		

		setText(position,holderView);
		
		return convertView;
	}
	private void setText(int position, HolderView holderView) {
		holderView.tv_Shopname_AdShopInfo.setText(list.get(position).getName());
		holderView.tv_OwerName_AdShopInfo.setText(list.get(position).getOwner());
		holderView.tv_phone_AdShopInfo.setText(list.get(position).getPhone());
		holderView.tv_ShopAddress_AdShopInfo.setText(list.get(position).getAddress());
		
	}
	public class HolderView{
		TextView tv_Shopname_AdShopInfo;
		TextView tv_OwerName_AdShopInfo;
		TextView tv_phone_AdShopInfo;
		TextView tv_ShopAddress_AdShopInfo;
		
	}
}
