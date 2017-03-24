package com.example.saveareaapp.util;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;

public class PullEvenText {

	public static void SetpullEcen(PullToRefreshBase<ListView> refreshView,
			State state, Mode direction,Context context){
		if (state.equals(State.PULL_TO_REFRESH)) {
			if (direction.equals(Mode.PULL_DOWN_TO_REFRESH)) {
				refreshView.getLoadingLayoutProxy().setPullLabel("下拉刷新");// 设置下拉刷新时文本显示
			}
			if (direction.equals(Mode.PULL_UP_TO_REFRESH)) {
				refreshView.getLoadingLayoutProxy().setPullLabel("上拉加载");// 设置上拉加载时文本显示
			}
			refreshView.getLoadingLayoutProxy().setReleaseLabel("释放刷新");// 设置释放时文本显示
			refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新"); // 设置刷新时文本显示
			String label = DateUtils.formatDateTime(context.getApplicationContext(),  
                   System.currentTimeMillis(),  
                  DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);  
           refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("time"+":"+ label); //  设置更新时间
		}
	}
	
}
