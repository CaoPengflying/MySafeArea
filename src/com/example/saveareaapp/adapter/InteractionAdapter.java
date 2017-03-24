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
import android.widget.ImageView;
import android.widget.TextView;

public class InteractionAdapter extends BaseAdapter {
	
	
	private Context context;
	private List<Interaction> list;

	public InteractionAdapter(){
		
	}
	public InteractionAdapter(Context context,List<Interaction> list){
		this.context = context;
		this.list = list;
	}
	
	public void AddLists(List<Interaction> list){
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	public void SetLists(List<Interaction> list){
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
					R.layout.adapter_interaction, parent, false);
			holderView = new HolderView();
			holderView.tv_username_AdInteraction = (TextView) convertView.findViewById(R.id.tv_username_AdInteraction);
			holderView.tv_time_AdInteraction = (TextView) convertView.findViewById(R.id.tv_time_AdInteraction);
			holderView.tv_word_AdInteraction = (TextView) convertView.findViewById(R.id.tv_word_AdInteraction);
			holderView.tv_huifu_AdInteraction = (TextView) convertView.findViewById(R.id.tv_huifu_AdInteraction);
			holderView.tv_review_AdInteraction = (TextView) convertView.findViewById(R.id.tv_review_AdInteraction);
			holderView.tv_argee_AdInteraction = (TextView) convertView.findViewById(R.id.tv_argee_AdInteraction);
			holderView.iv_photo_1_AdInteraction = (ImageView) convertView.findViewById(R.id.iv_photo_1_AdInteraction);
			holderView.iv_photo_2_AdInteraction = (ImageView) convertView.findViewById(R.id.iv_photo_2_AdInteraction);
			holderView.iv_photo_3_AdInteraction = (ImageView) convertView.findViewById(R.id.iv_photo_3_AdInteraction);
			holderView.iv_head_AdInteraction = (ImageView) convertView.findViewById(R.id.iv_head_AdInteraction);
			holderView.iv_agree_AdInteraction = (ImageView) convertView.findViewById(R.id.iv_agree_AdInteraction);
			convertView.setTag(holderView);
		}else{
			holderView = (HolderView) convertView.getTag();
		}
		

		setText(position,holderView);
		setImage(position,holderView);
		
		return convertView;
	}
	private void setImage(int position, HolderView holderView) {

		
		
		holderView.iv_photo_1_AdInteraction.setBackgroundResource(list.get(position).getB());
		holderView.iv_photo_2_AdInteraction.setBackgroundResource(list.get(position).getA());
		holderView.iv_photo_3_AdInteraction.setBackgroundResource(list.get(position).getC());
		holderView.iv_head_AdInteraction.setImageResource(list.get(position).getD());
		//holderView.iv_agree_AdInteraction.setImageResource(list.get(position).getD());
		
	}
	private void setText(int position, HolderView holderView) {
		holderView.tv_username_AdInteraction.setText(list.get(position).getReleaseName());
		//holderView.tv_time_AdInteraction.setText(list.get(position).getOwner());
		holderView.tv_word_AdInteraction.setText(list.get(position).getContent());
		//holderView.tv_huifu_AdInteraction.setText(list.get(position).getAddress());
		//holderView.tv_review_AdInteraction.setText(list.get(position).getCardID());
		//holderView.tv_argee_AdInteraction.setText(list.get(position).getCardID());
		
	}
	public class HolderView{
		TextView tv_username_AdInteraction;//用户名
		TextView tv_time_AdInteraction;//发表时间
		TextView tv_word_AdInteraction;//发表内容和标题
		TextView tv_huifu_AdInteraction;//回复
		TextView tv_review_AdInteraction;//评论条数
		TextView tv_argee_AdInteraction;//点赞个数
		ImageView iv_photo_1_AdInteraction;//图片1
		ImageView iv_photo_2_AdInteraction;//2
		ImageView iv_photo_3_AdInteraction;//3
		ImageView iv_head_AdInteraction;//头像
		ImageView iv_agree_AdInteraction;//点赞
		
	}
}
