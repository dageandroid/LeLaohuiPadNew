package com.llh.ipcarmear.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

/**
 * 获取图片工具类
 */

public class LLHUtils {
	private  static  final String TAG="LLHUtils";
	private final static String ALBUM_PATH = Environment.getExternalStorageDirectory() + "/namecard/";
	private final static String LOG_PATH = Environment.getExternalStorageDirectory().toString();
	
	public static ArrayList<Bitmap> arr = new ArrayList<Bitmap>(10);

	public static void deleteImage() {
		String name = readName();
		String img = ALBUM_PATH + name;
		try {
			File f = new File(img);
			if (f.delete()) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readName() {
		try {
			File f = new File(ALBUM_PATH);
			Log.i(TAG,"ALBUM_PATH=="+ALBUM_PATH+",LOG_PATH=="+LOG_PATH);
			if (f.isDirectory()) {
				File[] childFiles = bubbleSort(f.listFiles());
				if (childFiles == null || childFiles.length == 0) {
					return "";
				}
				return childFiles[childFiles.length - 1].getName();
			}
		} catch (Exception e) {
//			LogTool.i(e.toString());
			e.printStackTrace();
		}
		return "";
	}

	public static ArrayList<Bitmap> readAllBitmap() {
		try {
			File f = new File(ALBUM_PATH);
			arr.clear();
			if (f.isDirectory()) {
				File[] childFiles = f.listFiles();
				if (childFiles == null || childFiles.length == 0) {
					return null;
				}
				childFiles = bubbleSort(childFiles);

				for (int i = 0; i < childFiles.length; i++) {
					if (i < 10) {
						Bitmap b = readBitmap(childFiles[i].getName());
						Log.i(TAG,"childFiles[i].getName=="+childFiles[i].getName());
						arr.add(b);
					} else {
						// break;
						childFiles[i].delete();
					}

				}
			}
			Log.i(TAG, "arr.length=="+arr.size());
			return arr;
		} catch (Exception e) {
			Log.i(TAG,"e.toString()=="+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	public static Bitmap readBitmap(String name) {
		String img = ALBUM_PATH + File.separator + name;
		Log.i(TAG,"ALBUM_PATH=="+ALBUM_PATH+",LOG_PATH=="+LOG_PATH);
		try {
			FileInputStream fis = new FileInputStream(img);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static int getBtmapCount(int i) {
		try {
			File f = new File(ALBUM_PATH);
			if (f.isDirectory()) {
				File[] childFiles = f.listFiles();
				if (childFiles == null || childFiles.length == 0) {
					return 0;
				} else {
					return childFiles.length;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	public static File[] bubbleSort(File[] childFiles) {
		if (null == childFiles || childFiles.length == 0)
			return childFiles;
		File temp = null;
		for (int i = 0; i < childFiles.length - 1; i++) {
			for (int j = 0; j < childFiles.length - 1 - i; j++) {
				if (Long.parseLong(childFiles[j].getName().substring(0,
						childFiles[j].getName().indexOf("."))) < Long
						.parseLong(childFiles[j + 1].getName().substring(0,
								childFiles[j + 1].getName().indexOf(".")))) {
					temp = childFiles[j];
					childFiles[j] = childFiles[j + 1];
					childFiles[j + 1] = temp;
				}
			}
		}
		return childFiles;
	}
	/**
	 * 判断字符串是否为空
	 * 
	 * @Description:
	 * @param @param txt
	 * @return boolean true:字符串为空，false:非空
	 */
	public static boolean isEmpty(String txt) {
		boolean flag = false;
		if (txt == null || "".equals(txt))
			flag = true;
		return flag;
	}

}
