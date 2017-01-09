package dq.lelaohui.com.lelaohuipad.fragement.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.ShowCameraImgAdapter;
import dq.lelaohui.com.lelaohuipad.bean.ShowCamInfo;
import dq.lelaohui.com.lelaohuipad.port.CamreaImageSetInterface;
import dq.lelaohui.com.lelaohuipad.util.CallBackUtils;
import vstc2.nativecaller.NativeCaller;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Toast;

import com.llh.ipcarmear.BridgeService;
import com.llh.ipcarmear.PlayActivity;
import com.llh.ipcarmear.util.ContentCommon;
import com.llh.ipcarmear.util.SystemValue;


public class CamearQueryActivity extends Activity implements BridgeService.AddCameraInterface, OnItemSelectedListener, BridgeService.IpcamClientInterface,
		BridgeService.CallBackMessageInterface, CamreaImageSetInterface {
	private int option = ContentCommon.INVALID_OPTION;
	private int CameraType = ContentCommon.CAMERA_TYPE_MJPEG;
	private MyBroadCast receiver;
	private WifiManager manager = null;
	private static final String STR_DID = "did";
	private static final String STR_MSG_PARAM = "msgparam";
	private MyWifiThread myWifiThread = null;
	private boolean blagg = false;
	private Intent intentbrod = null;
	private WifiInfo info = null;
	private int tag = 0;
	private static final String DEVICE_NAME="admin";//设备姓名
	private static final String DEVICE_PASS="888888";//设备密码
	private AppCompatImageButton left_btn;

	class MyWifiThread extends Thread {
		@Override
		public void run() {
			while (blagg == true) {
				super.run();
				updateListHandler.sendEmptyMessage(100000);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class MyBroadCast extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {

			CamearQueryActivity.this.finish();
			Log.d("ip", "AddCameraActivity.this.finish()");
		}

	}

	class StartPPPPThread implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(100);
				startCameraPPPP();
			} catch (Exception e) {

			}
		}
	}

	private void startCameraPPPP() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}

		if (SystemValue.deviceId.toLowerCase().startsWith("vsta")) {
			NativeCaller
					.StartPPPPExt(
							SystemValue.deviceId,
							SystemValue.deviceName,
							SystemValue.devicePass,
							1,
							"",
							"EFGFFBBOKAIEGHJAEDHJFEEOHMNGDCNJCDFKAKHLEBJHKEKMCAFCDLLLHAOCJPPMBHMNOMCJKGJEBGGHJHIOMFBDNPKNFEGCEGCBGCALMFOHBCGMFK");
		} else {
			NativeCaller.StartPPPP(SystemValue.deviceId,
					SystemValue.deviceName, SystemValue.devicePass, 1, "");
		}

	}

	private ListView showImagelv;
	private AppCompatTextView title_tv;
	private String deviceCode,deviceName;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_query_device);
		CallBackUtils.setCallBack(this);
		manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		BridgeService.setAddCameraInterface(this);
		BridgeService.setCallBackMessage(this);
		receiver = new MyBroadCast();
		IntentFilter filter = new IntentFilter();
		filter.addAction("finish");
		registerReceiver(receiver, filter);
		intentbrod = new Intent("drop");
		showImagelv = (ListView) findViewById(R.id.showImagelv);
		title_tv=(AppCompatTextView)findViewById(R.id.title_tv);
		title_tv.setText("摄像机");
		left_btn=(AppCompatImageButton) findViewById(R.id.left_btn);
		left_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
		if (getIntent()!=null){
			deviceCode=	getIntent().getStringExtra("deviceCode");
			deviceName =getIntent().getStringExtra("deviceName");
			done(deviceCode);
		}
		bitmapLv = new ArrayList<ShowCamInfo>();

		if (bitmapLv.size() == 0) {
			bitmap = BitmapFactory.decodeResource(this.getApplicationContext()
					.getResources(), R.drawable.meeting_scene);
			ShowCamInfo showCamInfo=new ShowCamInfo();
			showCamInfo.setDeviceName(deviceName);
			showCamInfo.setBitmap(bitmap);
			bitmapLv.add(showCamInfo);
			adapter = new ShowCameraImgAdapter(getApplicationContext(),
					bitmapLv, false);
			showImagelv.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}

		showImagelv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(tag==1){
					Intent intent = new Intent(CamearQueryActivity.this,
							PlayActivity.class);
					startActivity(intent);
				}else{
					Toast.makeText(getApplicationContext(), "抱歉，您的设备不在线！", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onResume() {
		super.onResume();
		blagg = true;
	}
	@Override
	protected void onStop() {
		super.onStop();
		if (myWifiThread != null) {
			blagg = false;
		}
		NativeCaller.StopSearch();

	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
//		NativeCaller.Free();
//		Intent intent = new Intent();
//		intent.setClass(this, BridgeService.class);
//		stopService(intent);
		tag = 0;
	}


	Handler updateListHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 3) {
				Log.i(TAG,"showCamInfo---222222222----"+"");
				adapter = new ShowCameraImgAdapter(getApplicationContext(),
						bitmapLv, true);
				showImagelv.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		}
	};

	/**
	 * 摄像机在线时可以获取一张摄像机当前的画面图
	 */
	private void getSnapshot() {
		String msg = "snapshot.cgi?loginuse=admin&loginpas="
				+ SystemValue.devicePass + "&user=admin&pwd="
				+ SystemValue.devicePass;
		NativeCaller.TransferMessage(SystemValue.deviceId, msg, 1);
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			CamearQueryActivity.this.finish();
			return false;
		}
		return false;
	}
private static final String TAG="Camear";
	private void done(String deviceCode) {
		Log.i(TAG,"deviceCode=="+deviceCode);
		Intent in = new Intent();
		String strUser = DEVICE_NAME;
		String strPwd = DEVICE_PASS;
		String strDID = "VSTB067542HSYXP";
		if (strDID.length() == 0) {
			Toast.makeText(CamearQueryActivity.this,
					getResources().getString(R.string.input_camera_id),
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (strUser.length() == 0) {
			Toast.makeText(CamearQueryActivity.this,
					getResources().getString(R.string.input_camera_user),
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (option == ContentCommon.INVALID_OPTION) {
			option = ContentCommon.ADD_CAMERA;
		}
		in.putExtra(ContentCommon.CAMERA_OPTION, option);
		in.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
		in.putExtra(ContentCommon.STR_CAMERA_USER, strUser);
		in.putExtra(ContentCommon.STR_CAMERA_PWD, strPwd);
		in.putExtra(ContentCommon.STR_CAMERA_TYPE, CameraType);
		SystemValue.deviceName = strUser;
		SystemValue.deviceId = strDID;
		SystemValue.devicePass = strPwd;
		BridgeService.setIpcamClientInterface(this);
		NativeCaller.Init();
		new Thread(new StartPPPPThread()).start();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
		}
	}

	/**
	 * BridgeService callback
	 * **/
	@Override
	public void callBackSearchResultData(int sysver, String strMac,
			String strName, String strDeviceID, String strIpAddr, int port) {
		Log.e("AddCameraActivity", strDeviceID + strName);
	}

	private Handler PPPPMsgHandler = new Handler() {
		public void handleMessage(Message msg) {

			Bundle bd = msg.getData();
			int msgParam = bd.getInt(STR_MSG_PARAM);
			int msgType = msg.what;
			Log.i("aaa", "====" + msgType + "--msgParam:" + msgParam);
			String did = bd.getString(STR_DID);
			switch (msgType) {
			case ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS:
				int resid;
				switch (msgParam) {
				case ContentCommon.PPPP_STATUS_CONNECTING:// 0
					resid = R.string.pppp_status_connecting;
					tag = 2;
					break;
				case ContentCommon.PPPP_STATUS_CONNECT_FAILED:// 3
					resid = R.string.pppp_status_connect_failed;
					tag = 0;
					break;
				case ContentCommon.PPPP_STATUS_DISCONNECT:// 4
					resid = R.string.pppp_status_disconnect;
					tag = 0;
					break;
				case ContentCommon.PPPP_STATUS_INITIALING:// 1
					resid = R.string.pppp_status_initialing;
					tag = 2;
					break;
				case ContentCommon.PPPP_STATUS_INVALID_ID:// 5
					resid = R.string.pppp_status_invalid_id;
					tag = 0;
					break;
				case ContentCommon.PPPP_STATUS_ON_LINE:// 2 在线状态
					resid = R.string.pppp_status_online;
					// 摄像机在线之后读取摄像机类型
					String cmd = "get_status.cgi?loginuse=admin&loginpas="
							+ SystemValue.devicePass + "&user=admin&pwd="
							+ SystemValue.devicePass;
					NativeCaller.TransferMessage(did, cmd, 1);
					getSnapshot();
					tag = 1;
					break;
				case ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE:// 6
					resid = R.string.device_not_on_line;
					tag = 0;
					break;
				case ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT:// 7
					resid = R.string.pppp_status_connect_timeout;
					tag = 0;
					break;
				case ContentCommon.PPPP_STATUS_CONNECT_ERRER:// 8
					resid = R.string.pppp_status_pwd_error;
					tag = 0;
					break;
				default:
					resid = R.string.pppp_status_unknown;
				}
//				textView_top_show.setText(getResources().getString(resid));
				if (msgParam == ContentCommon.PPPP_STATUS_ON_LINE) {
					NativeCaller.PPPPGetSystemParams(did,
							ContentCommon.MSG_TYPE_GET_PARAMS);
				}
				if (msgParam == ContentCommon.PPPP_STATUS_INVALID_ID
						|| msgParam == ContentCommon.PPPP_STATUS_CONNECT_FAILED
						|| msgParam == ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE
						|| msgParam == ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT
						|| msgParam == ContentCommon.PPPP_STATUS_CONNECT_ERRER) {
					NativeCaller.StopPPPP(did);
				}
				break;
			case ContentCommon.PPPP_MSG_TYPE_PPPP_MODE:
				break;

			}

		}
	};

	@Override
	public void BSMsgNotifyData(String did, int type, int param) {
		Log.d("ip", "type:" + type + " param:" + param);
		Bundle bd = new Bundle();
		Message msg = PPPPMsgHandler.obtainMessage();
		msg.what = type;
		bd.putInt(STR_MSG_PARAM, param);
		bd.putString(STR_DID, did);
		msg.setData(bd);
		PPPPMsgHandler.sendMessage(msg);
		if (type == ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS) {
			intentbrod.putExtra("ifdrop", param);
			sendBroadcast(intentbrod);
		}

	}

	Bitmap bitmap = null;

	@Override
	public void BSSnapshotNotify(String did, byte[] bImage, int len) {
		Log.i("ip", "BSSnapshotNotify---len" + len + "bImage==" + bImage.length);
		bitmap = Bytes2Bimap(bImage);
		if (bitmap != null) {
			CallBackUtils.doCallBackMethod(bitmap);
		}
	}

	public Bitmap Bytes2Bimap(final byte[] b) {

		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	@Override
	public void callBackUserParams(String did, String user1, String pwd1,
			String user2, String pwd2, String user3, String pwd3) {

	}

	@Override
	public void CameraStatus(String did, int status) {

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	@Override
	public void CallBackGetStatus(String did, String resultPbuf, int cmd) {
		if (cmd == ContentCommon.CGI_IEGET_STATUS) {
			String cameraType = spitValue(resultPbuf, "upnp_status=");
			int intType = Integer.parseInt(cameraType);
			int type14 = (int) (intType >> 16) & 1;// 14位 来判断是否报警联动摄像机
			if (intType == 2147483647) {// 特殊值
				type14 = 0;
			}

			if (type14 == 1) {
				updateListHandler.sendEmptyMessage(2);
			}

		}
	}

	private String spitValue(String name, String tag) {
		String[] strs = name.split(";");
		for (int i = 0; i < strs.length; i++) {
			String str1 = strs[i].trim();
			if (str1.startsWith("var")) {
				str1 = str1.substring(4, str1.length());
			}
			if (str1.startsWith(tag)) {
				String result = str1.substring(str1.indexOf("=") + 1);
				return result;
			}
		}
		return -1 + "";
	}

	List<ShowCamInfo> bitmapLv = new ArrayList<ShowCamInfo>();
	ShowCameraImgAdapter adapter = null;

	@Override
	public void doSetDrawByte(Bitmap bitmap) {
		if (bitmap != null) {
			updateListHandler.sendEmptyMessage(3);
			bitmapLv = new ArrayList<ShowCamInfo>();
			ShowCamInfo showCamInfo=new ShowCamInfo();
			showCamInfo.setDeviceName(deviceName);
			showCamInfo.setBitmap(bitmap);
			bitmapLv.add(showCamInfo);
			Log.i(TAG,"showCamInfo---111111111111111111111----"+"");
		}

	}

}
