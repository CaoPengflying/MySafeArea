package com.example.saveareaapp.util;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncTaskHelper {
	public interface OnDataDownloadListener {
		void onDataDownload(byte[] result);
	}

	public void downloadData(Context context, String url,String paramters,
			OnDataDownloadListener downloadListener) {
		new MyTask(downloadListener, context,paramters).execute(url);
	}

	private class MyTask extends AsyncTask<String, Void, byte[]> {
		private OnDataDownloadListener downloadListener;
		private Context context;
		private String paramters;

		public MyTask(OnDataDownloadListener downloadListener, Context context, String paramters) {
			this.downloadListener = downloadListener;
			this.context = context;
			this.paramters = paramters;
		}

		@Override
		protected byte[] doInBackground(String... params) {
			byte[] jsonData = HttpURLConnHelper.doPostSubmit(params[0],paramters);
			return jsonData;
		}
		
		

		@Override
		protected void onPostExecute(byte[] result) {
			super.onPostExecute(result);
			if (result != null) {
				// 通过回调接口来传递数据
				downloadListener.onDataDownload(result);
			} else {
				Toast.makeText(context, "加载超时，请重新连接网络连接", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}
