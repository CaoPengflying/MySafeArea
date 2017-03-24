package com.example.saveareaapp.util;

import android.bluetooth.BluetoothAdapter;

/**
 * Bluetooth ������
 * 
 * @author ifeegoo www.ifeegoo.com
 * 
 */
public class BluetoothManager
{

	/**
	 * ��ǰ Android �豸�Ƿ�֧�� Bluetooth
	 * 
	 * @return true��֧�� Bluetooth false����֧�� Bluetooth
	 */
	public static boolean isBluetoothSupported()
	{
		return BluetoothAdapter.getDefaultAdapter() != null ? true : false;
	}

	/**
	 * ��ǰ Android �豸�� bluetooth �Ƿ��Ѿ�����
	 * 
	 * @return true��Bluetooth �Ѿ����� false��Bluetooth δ����
	 */
	public static boolean isBluetoothEnabled()
	{
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();

		if (bluetoothAdapter != null)
		{
			return bluetoothAdapter.isEnabled();
		}

		return false;
	}

	/**
	 * ǿ�ƿ�����ǰ Android �豸�� Bluetooth 
	 * 
	 * @return true��ǿ�ƴ� Bluetooth���ɹ���false��ǿ�ƴ� Bluetooth ʧ��
	 */
	public static boolean turnOnBluetooth()
	{
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();

		if (bluetoothAdapter != null)
		{
			return bluetoothAdapter.enable();
		}

		return false;
	}
}
