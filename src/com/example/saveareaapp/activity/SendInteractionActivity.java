package com.example.saveareaapp.activity;

import java.io.File;
import java.io.IOException;

import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.R.menu;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;
import com.zf.iosdialog.widget.ActionSheetDialog;
import com.zf.iosdialog.widget.ActionSheetDialog.OnSheetItemClickListener;
import com.zf.iosdialog.widget.ActionSheetDialog.SheetItemColor;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SendInteractionActivity extends BaseActivity implements OnClickListener {
	
	private ImageView iv_back_AtSendInter;
	private ImageView im_photo1,im_photo2,im_photo3;
	private String imageFilePath; // 图片保存路径
	private AsyncTaskHelper taskHelper;
	private OnDataDownloadListener downloadListener;
	private String url = "http://192.168.1.104:8080/UploadImageDemo/servlet/UploadShipServlet";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_send_interaction);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_send_interaction);
		init();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}

	private void init() {
		iv_back_AtSendInter = (ImageView) findViewById(R.id.iv_back_AtSendInter);
		im_photo1 = (ImageView) findViewById(R.id.im_photo1);
		im_photo2 = (ImageView) findViewById(R.id.im_photo2);
		im_photo3 = (ImageView) findViewById(R.id.im_photo3);
		
		im_photo2.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
		im_photo3.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
		
		
		
		iv_back_AtSendInter.setOnClickListener(this);
		im_photo1.setOnClickListener(this);
		im_photo2.setOnClickListener(this);
		im_photo3.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back_AtSendInter:
			this.finish();
			break;
		case R.id.im_photo1:
			getphoto();
		im_photo2.setLayoutParams(new LinearLayout.LayoutParams(165, 165));
		break;
		case R.id.im_photo2:
			getphoto();
		im_photo3.setLayoutParams(new LinearLayout.LayoutParams(165, 165));
			break;
		case R.id.im_photo3:
			getphoto();
			break;

		default:
			break;
		}
		
	}

	private void getphoto() {
		new ActionSheetDialog(this)
		.builder()
		.setCancelable(false)
		.setCanceledOnTouchOutside(false)
		.addSheetItem("拍照上传", SheetItemColor.Blue,
				new OnSheetItemClickListener() {
					@Override
					public void onClick(int which) {
						imageFilePath = Environment
								.getExternalStorageDirectory()
								.getAbsolutePath()
								+ "/filename.jpg";
						File temp = new File(imageFilePath);
						System.out.println("file = " + temp);
						Uri imageFileUri = Uri.fromFile(temp);// 获取文件的Uri
						Intent it = new Intent(
								MediaStore.ACTION_IMAGE_CAPTURE);// 跳转到相机Activity
						it.putExtra(
								android.provider.MediaStore.EXTRA_OUTPUT,
								imageFileUri);// 告诉相机拍摄完毕输出图片到指定的Uri
						startActivityForResult(it, 102);
					}
				})
		.addSheetItem("相册选择", SheetItemColor.Blue,
				new OnSheetItemClickListener() {
					@Override
					public void onClick(int which) {
						// 相册选取
						Intent intent1 = new Intent(
								Intent.ACTION_GET_CONTENT);
						intent1.setDataAndType(
								MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
								"image/*");
						startActivityForResult(intent1, 103);
					}
				}).show();
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 102:
			if (resultCode == Activity.RESULT_OK) {

				Bitmap bmp = BitmapFactory.decodeFile(imageFilePath);
				im_photo1.setImageBitmap(bmp);
			}
			break;
		case 103:
			Bitmap bm = null;
			// 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
			ContentResolver resolver = getContentResolver();

			try {
				Uri originalUri = data.getData(); // 获得图片的uri

				bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); // 显得到bitmap图片

				// 这里开始的第二部分，获取图片的路径：

				String[] proj = { MediaStore.Images.Media.DATA };

				// 好像是android多媒体数据库的封装接口，具体的看Android文档
				@SuppressWarnings("deprecation")
				Cursor cursor = managedQuery(originalUri, proj, null, null,
						null);
				// 获得用户选择的图片的索引值
				int column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				// 将光标移至开头 ，这个很重要，不小心很容易引起越界
				cursor.moveToFirst();
				// 最后根据索引值获取图片路径
				String path = cursor.getString(column_index);
				im_photo3.setImageURI(originalUri);

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
