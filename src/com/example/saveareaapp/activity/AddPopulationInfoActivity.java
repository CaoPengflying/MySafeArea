package com.example.saveareaapp.activity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.saveareaapp.R;
import com.example.saveareaapp.adapter.AreaSpinnerAdapter;
import com.example.saveareaapp.adapter.ManOfLiveAdapter;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.House;
import com.example.saveareaapp.bean.Resident;
import com.example.saveareaapp.bean.User;
import com.example.saveareaapp.config.Myconfig;
import com.example.saveareaapp.config.Newconfig;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.BluetoothManager;
import com.example.saveareaapp.util.FastJsonTools;
import com.example.saveareaapp.util.GetUser;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;
import com.ivsign.android.IDCReader.IDCReaderSDK;

public class AddPopulationInfoActivity extends BaseActivity implements OnClickListener, OnItemSelectedListener {
	private User user;
	private boolean success=false;
	private Context context = this;
	
	private EditText et_address_AtAddPopulationInfo;//地址
	private EditText et_houseOwner_AtAddPopulationInfo;//房主
	private EditText et_idNum_AtAddPopulationInfo;//身份证号
	private EditText et_phone_AtAddPopulationInfo;//联系电话
	private Spinner sp_housUse_AtAddPopulationInfo;//房屋使用类型
	
	protected static final int REQUEST_CODE_ALBUM = 0;
	protected static int IS_FINISH = 0;
	
	private AsyncTaskHelper AddPopuHelper;//异步加载网络
	private OnDataDownloadListener AddPopuDataDownloadListener;//异步加载网络监听器
	
	

	private AsyncTaskHelper taskHelper;
	private OnDataDownloadListener downloadListener;
	private String url = "http://192.168.1.104:8080/UploadImageDemo/servlet/UploadShipServlet";
	
	
	private ImageView iv_back;
	private Button bt_save_AddPopulationInfo;//保存
	
	private List<Area> areas = new ArrayList<Area>();
	private Button bt_AddHouseMan_AddPopulationInfo;
	private ListView lv_idInfo_AtAddPopulationInfo;
	private List<Resident> residents = new ArrayList<Resident>();
	private int housetpe=0;
	private int areaID = 0;
	
	private ManOfLiveAdapter residentAdapter;
	private Spinner sp_selectArea_AddPopulationInfo;//选小区的
	
	//蓝牙读取身份证有关变量
	private BluetoothAdapter myBluetoothAdapter; // 蓝牙适配器
	private BluetoothServerSocket mBThServer;
	private BluetoothSocket mBTHSocket;
	private InputStream mmInStream;
	private OutputStream mmOutStream;
	private SharedPreferences sp; //存储蓝牙配对信息
	private int Readflage = -99;

	private byte[] cmd_find = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
			(byte) 0x96, 0x69, 0x00, 0x03, 0x20, 0x01, 0x22 };
	private byte[] cmd_selt = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
			(byte) 0x96, 0x69, 0x00, 0x03, 0x20, 0x02, 0x21 };
	private byte[] cmd_read = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
			(byte) 0x96, 0x69, 0x00, 0x03, 0x30, 0x01, 0x32 };
	private byte[] recData = new byte[1500];
	// 身份证读取机型号
	private String DEVICE_NAME1 = "CVR-100B";
	private String DEVICE_NAME2 = "IDCReader";
	private String DEVICE_NAME3 = "COM2";
	private String DEVICE_NAME4 = "BOLUTEK";

	private UUID MY_UUID = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private String[] decodeInfo = new String[10];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_add_population_info);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_add_population_info);
		init();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	/**
	 * 发送添加信息
	 */
	private void send(){
		
		if(et_address_AtAddPopulationInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入地址", Toast.LENGTH_SHORT).show();
			return;
		}
		if(et_houseOwner_AtAddPopulationInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入房主姓名", Toast.LENGTH_SHORT).show();
			return;
		}
		if(et_idNum_AtAddPopulationInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入身份证号", Toast.LENGTH_SHORT).show();
			return;
		}
		if(et_phone_AtAddPopulationInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入联系方式", Toast.LENGTH_SHORT).show();
			return;
		}
		
		
		
		AddPopuDataDownloadListener = new OnDataDownloadListener() {
			
			@Override
			public void onDataDownload(byte[] result) {
				String jsonString = "{\"success\":true,\"msg\":\"OK\"}";
				Map<String , Object> map = FastJsonTools.getMap(jsonString);
				success = (Boolean) map.get("success");
				if(success){
					Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT)
					.show();
					AddPopulationInfoActivity.this.finish();
				}else{

					Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT)
					.show();
				}
			}
		};
		
		
		
		
		House house = new House();
		
		user = new GetUser(this).getinfo();
		house.setAdderID(user.getId());
		house.setAdderName(user.getName());
		house.setAreaID(areas.get(areaID).getId());
		house.setAreaName(areas.get(areaID).getName());
		house.setIsDelete(false);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		house.setCreateDate(sdf.format(date));
		getphotoUrl();
		
		house.setResidents(residents);
		
		for (Resident resident : residents) {
			resident.setAdderID(user.getId());
			resident.setCreateDate(sdf.format(date));
			resident.setIsDelete(false);
		}
		
		house.setAddress(et_address_AtAddPopulationInfo.getText().toString());
		house.setOwner(et_houseOwner_AtAddPopulationInfo.getText().toString());
		house.setOwnerCardID(et_idNum_AtAddPopulationInfo.getText().toString());
		house.setPhone(et_phone_AtAddPopulationInfo.getText().toString());
		house.setType(housetpe);
		house.setCreateDate(sdf.format(date));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("house", house);
		
		
		String params = JSON.toJSONString(map);
		Newconfig carsconfig = new Newconfig(0);
		carsconfig.setParams(params);
		System.out.println("发送房屋信息："+carsconfig.getParamters());

		String url = Myconfig.ADRESS + "HouseServlet";
		AddPopuHelper.downloadData(this, url, carsconfig.getParamters(), AddPopuDataDownloadListener);
		
	}
	/**
	 * 把居住人头像存到七牛返回图片地址
	 */
	private void getphotoUrl() {
//		for(int i=0;i<residents.size();i++){
//			
//			String imageFilePath = Environment
//					.getExternalStorageDirectory()
//					.getAbsolutePath()
//					+ "/filename.jpg";
//			downloadListener = new OnDataDownloadListener() {
//
//				@Override
//				public void onDataDownload(String result) {
//					// 接收图片存在服务器的url
//					Toast.makeText(context, "图片url= " + result,
//							Toast.LENGTH_LONG).show();
//				}
//			};
//			taskHelper.downloadData(this, url, imageFilePath,
//					downloadListener); // 上传图片到服务器
//		}
			
	}
	
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtAddPopulationInfo);
		bt_AddHouseMan_AddPopulationInfo = (Button) findViewById(R.id.bt_AddHouseMan_AddPopulationInfo);
		bt_save_AddPopulationInfo = (Button) findViewById(R.id.bt_save_AddPopulationInfo);
		sp_selectArea_AddPopulationInfo = (Spinner) findViewById(R.id.sp_selectArea_AtAddPopulationInfo);
		lv_idInfo_AtAddPopulationInfo = (ListView) findViewById(R.id.lv_idInfo_AtAddPopulationInfo);
		
		et_address_AtAddPopulationInfo = (EditText)findViewById(R.id.et_address_AtAddPopulationInfo);
		et_houseOwner_AtAddPopulationInfo = (EditText)findViewById(R.id.et_houseOwner_AtAddPopulationInfo);
		et_idNum_AtAddPopulationInfo = (EditText)findViewById(R.id.et_idNum_AtAddPopulationInfo);
		et_phone_AtAddPopulationInfo = (EditText)findViewById(R.id.et_phone_AtAddPopulationInfo);
		sp_housUse_AtAddPopulationInfo = (Spinner)findViewById(R.id.sp_housUse_AtAddPopulationInfo);
		sp_housUse_AtAddPopulationInfo.setOnItemSelectedListener(this);
		
		AddPopuHelper = new AsyncTaskHelper();
		bt_AddHouseMan_AddPopulationInfo.setOnClickListener(this);
		iv_back.setOnClickListener(this);
		bt_save_AddPopulationInfo.setOnClickListener(this);

		

		Intent intent = getIntent();
		areas.addAll( (List<Area>) intent.getSerializableExtra("areas"));

		AreaSpinnerAdapter adapter = new AreaSpinnerAdapter(this, areas);

		residentAdapter = new ManOfLiveAdapter(this);
		lv_idInfo_AtAddPopulationInfo.setAdapter(residentAdapter);
		sp_selectArea_AddPopulationInfo.setAdapter(adapter);
		
		sp_selectArea_AddPopulationInfo.setOnItemSelectedListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.bt_AddHouseMan_AddPopulationInfo:

			  IS_FINISH=showAlertDialog(IS_FINISH);
			
			break;
		case R.id.iv_back_AtAddPopulationInfo:
			this.finish();
			break;
		case R.id.bt_save_AddPopulationInfo:
			send();
			break;
		}
		
	}
	private int showAlertDialog(int iS_FINISH2) {
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setView(new EditText(this));
		dialog.show(); 
		dialog.setContentView(R.layout.input_idcard);
		Button bt_read_inputIdCard = (Button)dialog.findViewById(R.id.bt_read_inputIdCard);
		Button bt_save_inputIdCard = (Button)dialog.findViewById(R.id.bt_save_inputIdCard);
		Button bt_exit_inputIdCard = (Button)dialog.findViewById(R.id.bt_exit_inputIdCard);
		final TextView tv_noice_inputIdcard = (TextView)dialog.findViewById(R.id.tv_noice_inputIdcard);
		final ImageView iv_head = (ImageView) dialog.findViewById(R.id.iv_head_InputIdCard);
		final EditText et_name_InputIdCard = (EditText) dialog.findViewById(R.id.et_name_InputIdCard);
		final EditText et_nation_InputIdCard = (EditText) dialog.findViewById(R.id.et_nation_InputIdCard);
		final EditText et_address_InputIdCard = (EditText) dialog.findViewById(R.id.et_address_InputIdCard);
		final EditText et_idnumber_InputIdCard = (EditText) dialog.findViewById(R.id.et_idnumber_InputIdCard);
		final EditText et_sex_InputIdCard = (EditText) dialog.findViewById(R.id.et_sex_InputIdCard);
		final EditText et_birth_InputIdCard = (EditText) dialog.findViewById(R.id.et_birth_InputIdCard);
		
		bt_read_inputIdCard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getConn();
			}
			/**
			 * 用于连接蓝牙
			 */
			private void getConn() {
				if(!BluetoothManager.isBluetoothEnabled()){
					if(BluetoothManager.turnOnBluetooth()){
						tv_noice_inputIdcard.setText("蓝牙已开启！请将身份证从设备拿出，10秒后再重新读取！");
					}
				}
				myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
				Set<BluetoothDevice> pairedDevices = myBluetoothAdapter
						.getBondedDevices();
				if (pairedDevices.size() > 0) {
					for (Iterator<BluetoothDevice> iterator = pairedDevices.iterator(); iterator
							.hasNext();) {
						BluetoothDevice device = (BluetoothDevice) iterator.next();
						if (DEVICE_NAME1.equals(device.getName())
								|| DEVICE_NAME2.equals(device.getName())
								|| DEVICE_NAME3.equals(device.getName())
								|| DEVICE_NAME4.equals(device.getName())) {
							try {
								myBluetoothAdapter.enable();
								Intent discoverableIntent = new Intent(
										BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);// 使得蓝牙处于可发现模式，持续时间150s
								discoverableIntent.putExtra(
										BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
										150);
								mBTHSocket = device
										.createRfcommSocketToServiceRecord(MY_UUID);

								int sdk = Integer.parseInt(Build.VERSION.SDK);
								if (sdk >= 10) {
									mBTHSocket = device
											.createInsecureRfcommSocketToServiceRecord(MY_UUID);
								} else {
									mBTHSocket = device
											.createRfcommSocketToServiceRecord(MY_UUID);
								}

								mBThServer = myBluetoothAdapter
										.listenUsingRfcommWithServiceRecord(
												"myServerSocket", MY_UUID);
								mBTHSocket.connect();
								mmInStream = mBTHSocket.getInputStream();
								mmOutStream = mBTHSocket.getOutputStream();

							} catch (IOException e) {
								tv_noice_inputIdcard.setText("设备连接异常！");
							}
							if ((mmInStream != null) && (mmInStream != null)) {
								tv_noice_inputIdcard.setText("设备连接成功！");
								getCardInfo(); // 读取身份证信息
							} else {
								tv_noice_inputIdcard.setText("设备连接失败！");
							}
							break;
						}else{
							tv_noice_inputIdcard.setText("未发现设备，请打开设置->蓝牙->搜索设备，为\"CVR-200B\"进行配对，配对密码1234！");
						}
					}
				}else{
					tv_noice_inputIdcard.setText("未发现设备，请打开设置->蓝牙->搜索设备，为\"CVR-200B\"进行配对，配对密码1234！");
				}
			}
			/**
			 * 获取身份证信息
			 */
			private void getCardInfo() {
				int readcount = 15;
				try 
				{ 
					while(readcount > 1)
					{
						readCard(); //读卡
						readcount = readcount - 1;
						if(Readflage > 0)
						{
							readcount = 0;
							// 读取成功，将数据显示
							et_address_InputIdCard.setText(decodeInfo[4]);
							et_idnumber_InputIdCard.setText(decodeInfo[5]);
							et_name_InputIdCard.setText(decodeInfo[0]);
							et_nation_InputIdCard.setText(decodeInfo[2]);
							et_birth_InputIdCard.setText(decodeInfo[3]);
							et_sex_InputIdCard.setText(decodeInfo[1]);
//							tv_info.setText("姓名：" + decodeInfo[0] + "\n" + "性别：" + decodeInfo[1] + "\n" + "民族：" + decodeInfo[2] + "\n"
//					        		+ "出生日期：" + decodeInfo[3] + "\n" + "地址：" + decodeInfo[4] + "\n" + "身份号码：" + decodeInfo[5] + "\n"
//					        		+ "签发机关：" + decodeInfo[6] + "\n" + "有效期限：" + decodeInfo[7] + "-" + decodeInfo[8] + "\n"
//					        		+ decodeInfo[9] + "\n");        					
							if(Readflage == 1)
							{
								FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory() + "/wltlib/zp.bmp");
								Bitmap bmp = BitmapFactory.decodeStream(fis);
								fis.close();
								// 显示照片
								iv_head.setImageBitmap(bmp);
							}
							else
							{
								tv_noice_inputIdcard.append("照片解码失败，请检查路径" + Environment.getExternalStorageDirectory() + "/wltlib/");
								iv_head.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.head1));
							}        					
						}
						else
						{        					
							iv_head.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.head1));
							if(Readflage == -2)
							{
								tv_noice_inputIdcard.setText("蓝牙连接异常");
							}
							if(Readflage == -3)
							{
								tv_noice_inputIdcard.setText("无卡或卡片已读过。请将身份证从设备拿出，10秒后再重新读取！");
							}
							if(Readflage == -4)
							{
								tv_noice_inputIdcard.setText("无卡或卡片已读过，请将身份证从设备拿出，10秒后再重新读取！");
							}
							if(Readflage == -5)
							{
								tv_noice_inputIdcard.setText("读卡失败");
							}
							if(Readflage == -99)
							{
								tv_noice_inputIdcard.setText("操作异常");
							}
						}        				
						Thread.sleep(100);	
					}        				
					
				} catch (IOException e) {
					tv_noice_inputIdcard.setText("读取数据异常！");
					iv_head.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.head1));
				} catch (InterruptedException e) {
					tv_noice_inputIdcard.setText("读取数据异常！");
					iv_head.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.head1));
				} 
			}
			/**
			 * 读取 身份证信息
			 */
			private void readCard() {

				try 
				{        			
					if((mmInStream == null)||(mmInStream == null))
			        {	        	
						Readflage = -2;//连接异常
						return;
			        }			
					mmOutStream.write(cmd_find);
					Thread.sleep(200);					
					int datalen = mmInStream.read(recData);
					if(recData[9] == -97)
					{						
						mmOutStream.write(cmd_selt);
						Thread.sleep(200);					
						datalen = mmInStream.read(recData);
						if(recData[9] == -112)
						{
							mmOutStream.write(cmd_read);
							Thread.sleep(1000);	
							byte[] tempData = new byte[1500];
							if(mmInStream.available()>0)
							{
								datalen = mmInStream.read(tempData);
							}
							else
							{
								Thread.sleep(500);	
								if(mmInStream.available()>0)
								{
									datalen = mmInStream.read(tempData);
								}
							}
							int flag = 0;
							if(datalen <1294)
							{
								for(int i = 0;i<datalen ;i++,flag++)
								{									
									recData[flag] = tempData[i];
								}								
								Thread.sleep(1000);
								if(mmInStream.available()>0)
								{
									datalen = mmInStream.read(tempData);
								}
								else
								{
									Thread.sleep(500);
									if(mmInStream.available()>0)
									{
										datalen = mmInStream.read(tempData);
									}									
								}
								for(int i = 0;i<datalen ;i++,flag++)
								{									
									recData[flag] = tempData[i];
								}
								
							}
							else
							{
								for(int i = 0;i<datalen ;i++,flag++)
								{									
									recData[flag] = tempData[i];
								}	
							}
							tempData = null;
							if(flag == 1295)
							{								
								if(recData[9] == -112)
								{		
									
									byte[] dataBuf = new byte[256];								
									for(int i = 0; i < 256; i++)
									{
										dataBuf[i] = recData[14 + i];
									}
									String TmpStr = new String(dataBuf, "UTF16-LE");
									TmpStr = new String(TmpStr.getBytes("UTF-8"));
									decodeInfo[0] = TmpStr.substring(0, 15);
									decodeInfo[1] = TmpStr.substring(15, 16);
									decodeInfo[2] = TmpStr.substring(16, 18);
									decodeInfo[3] = TmpStr.substring(18, 26);
									decodeInfo[4] = TmpStr.substring(26, 61);
									decodeInfo[5] = TmpStr.substring(61, 79);
									decodeInfo[6] = TmpStr.substring(79, 94);
									decodeInfo[7] = TmpStr.substring(94, 102);
									decodeInfo[8] = TmpStr.substring(102, 110);
									decodeInfo[9] = TmpStr.substring(110, 128);
									if (decodeInfo[1].equals("1"))
										decodeInfo[1] = "男";
									else
										decodeInfo[1] = "女";
									try
									{
										int code = Integer.parseInt(decodeInfo[2].toString());
										decodeInfo[2] = decodeNation(code);
									}
									catch (Exception e)
									{
										decodeInfo[2] = "";
									}
									
									//照片解码									
									try
									{	
										int ret = IDCReaderSDK.Init();
										if (ret == 0)
										{	
											byte[] datawlt = new byte[1384];
											byte[] byLicData = {(byte)0x05,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x5B,(byte)0x03,(byte)0x33,(byte)0x01,(byte)0x5A,(byte)0xB3,(byte)0x1E,(byte)0x00};
											for(int i = 0; i < 1295; i++)
											{
											 	datawlt[i] = recData[i];
											}
											int t = IDCReaderSDK.unpack(datawlt,byLicData);
											if(t == 1)
											{
												Readflage = 1;//读卡成功
											}
											else
											{
												Readflage = 6;//照片解码异常
											}											
										}
										else
										{
											Readflage = 6;//照片解码异常
										}										
									}
									catch(Exception e)
									{								
										Readflage = 6;//照片解码异常
									}								
									
								}
								else
								{								
									Readflage = -5;//读卡失败！
								}
							}
							else
							{
								Readflage = -5;//读卡失败
							}					
						}
						else
						{							
							Readflage = -4;//选卡失败
						}
					}
					else
					{						
						Readflage = -3;//寻卡失败		        
					}
					
				} catch (IOException e) {
					Readflage = -99;//读取数据异常
				} catch (InterruptedException e) {
					Readflage = -99;//读取数据异常
				}
					
			}
			/**
			 * 解析民族
			 * @param code
			 * @return
			 */
			private String decodeNation(int code) {
				String nation;
				switch (code)
				{
				case 1: 
					nation = "汉";
					break;
				case 2: 
					nation = "蒙古";
					break;
				case 3:
					nation = "回";
					break;
				case 4:
					nation = "藏";
					break;
				case 5: 
					nation = "维吾尔";
					break;
				case 6:
					nation = "苗";
					break;
				case 7:
					nation = "彝";
					break;
				case 8: 
					nation = "壮";
					break;
				case 9:
					nation = "布依";
					break;
				case 10:
					nation = "朝鲜";
					break;
				case 11:
					nation = "满";
					break;
				case 12:
					nation = "侗";
					break;
				case 13:
					nation = "瑶";
					break;
				case 14:
					nation = "白";
					break;
				case 15:
					nation = "土家";
					break;
				case 16:
					nation = "哈尼";
					break;
				case 17:
					nation = "哈萨克";
					break;
				case 18:
					nation = "傣";
					break;
				case 19:
					nation = "黎";
					break;
				case 20:
					nation = "傈僳";
					break;
				case 21:
					nation = "佤";
					break;
				case 22:
					nation = "畲";
					break;
				case 23:
					nation = "高山";
					break;
				case 24:
					nation = "拉祜";
					break;
				case 25: 
					nation = "水";
					break;
				case 26: 
					nation = "东乡";
					break;
				case 27: 
					nation = "纳西";
					break;
				case 28: 
					nation = "景颇";
					break;
				case 29: 
					nation = "柯尔克孜";
					break;
				case 30: 
					nation = "土";
					break;
				case 31: 
					nation = "达斡尔";
					break;
				case 32: 
					nation = "仫佬";
					break;
				case 33: 
					nation = "羌";
					break;
				case 34: 
					nation = "布朗";
					break;
				case 35: 
					nation = "撒拉";
					break;
				case 36: 
					nation = "毛南";
					break;
				case 37: 
					nation = "仡佬";
					break;
				case 38: 
					nation = "锡伯";
					break;
				case 39:
					nation = "阿昌";
					break;
				case 40: 
					nation = "普米";
					break;
				case 41:
					nation = "塔吉克";
					break;
				case 42:
					nation = "怒";
					break;
				case 43:
					nation = "乌孜别克";
					break;
				case 44:
					nation = "俄罗斯";
					break;
				case 45: 
					nation = "鄂温克";
					break;
				case 46: 
					nation = "德昂";
					break;
				case 47: 
					nation = "保安";
					break;
				case 48:
					nation = "裕固";
					break;
				case 49: 
					nation = "京";
					break;
				case 50: 
					nation = "塔塔尔";
					break;
				case 51:
					nation = "独龙";
					break;
				case 52:
					nation = "鄂伦春";
					break;
				case 53: 
					nation = "赫哲";
					break;
				case 54: 
					nation = "门巴";
					break;
				case 55: 
					nation = "珞巴";
					break;
				case 56:
					nation = "基诺";
					break;
				case 97:
					nation = "其他";
					break;
				case 98: 
					nation = "外国血统中国籍人士";
					break;
				default:
					nation = "";
					break;
				}
				return nation;
			
			}
		});
		
		
		bt_save_inputIdCard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Resident resident = new Resident();
				resident.setCardID(et_idnumber_InputIdCard.getText().toString());
				resident.setName(et_name_InputIdCard.getText().toString());
				resident.setNation(et_nation_InputIdCard.getText().toString());
				resident.setAddress(et_address_InputIdCard.getText().toString());
				resident.setBirthday(et_birth_InputIdCard.getText().toString());
				resident.setSex(et_sex_InputIdCard.getText().toString());
				//resident.setPhoto();          
				//iv_head.setDrawingCacheEnabled(true);
				//iv_head.getDrawingCache();
				resident.setPhoto(((BitmapDrawable)iv_head.getDrawable()).getBitmap());
				if(!resident.getCardID().equals("")){
				residents.add(resident);
				residentAdapter.SetLists(residents);
				
				dialog.dismiss(); 
				}
				
			}
		});
		bt_exit_inputIdCard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				dialog.dismiss(); 
				
			}
		});
		
		
		return  1;
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		switch (view.getId()) {
		case R.id.sp_selectArea_AtAddPopulationInfo:
			areaID = position;
			break;
		case R.id.sp_housUse_AtAddPopulationInfo:
			housetpe = position;
			break;
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
