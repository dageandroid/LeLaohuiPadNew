package dq.lelaohui.com.nettylibrary.socket;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.sun.commontransfer.adroid.ResponseHandler;
import com.sun.commontransfer.adroid.TransferMessageContentItem;
import com.sun.commontransfer.adroid.TransferMessageObject;
import com.sun.commontransfer.adroid.TransferObject;

import dq.lelaohui.com.nettylibrary.util.NetContant;

public class LlhResponseHandler implements ResponseHandler {
	private String TAG="LlhResponseHandler";
	private static LlhResponseHandler instance = new LlhResponseHandler();
	public static boolean isConnect=false;
	private Context context;

	public static LlhResponseHandler getInstance() {
		return instance;
	}
	public void setContext(Context context)
	{
		this.context=context;
	}
	@Override
	public void handleClosed() {
		isConnect=false;
		Log.i(TAG,"handleClosed.....");
	}

	@Override
	public void handleConnected() {
		isConnect=true;
		Log.i(TAG,"handleConnected.....");
	}

	@Override
	public void handleResponse(TransferObject arg0) {
		try 
		{
			//接收到消息
//			if(arg0.isMessageAction())
//			{
//	    		TransferMessageObject message = new TransferMessageObject(arg0);
//	    		String sender = message.getBodySender();
//
//	    		int x = message.getMessageContentItems().length;
//
//	    		TransferMessageContentItem[] items = message.getMessageContentItems();
//
//	    		for(int i = 0; i < items.length; i++)
//	    		{
//					if(TransferMessageObject.isTextContentItem(items[i]))
//						System.out.println(" received text msg:\n" + new String(items[i].content,"UTF-8") );
//	    		}
//			}else
				sendReturnMsg(arg0);
		} catch (Exception e) 
		{
			String ee = e.getMessage();
			System.out.println(e.getMessage());
		}		
	}



	@SuppressWarnings("unused")
	private boolean sendReturnMsg(final TransferObject obj) {
//		Log.i(TAG,"sendReturnMsg="+obj.toString());
		Log.i(TAG,"this.context==null="+(this.context==null));
		if(this.context==null){
			return false;
		}
		Bundle data = new Bundle();
		data.putString(Respon_Key.ACTION, obj.getHeadAction());
		data.putString(Respon_Key.SN, obj.getHeadSn());
		data.putString(Respon_Key.UID, obj.getHeadUID());
		data.putString(Respon_Key.HEAD, obj.getResponseHead().toString());
		data.putString(Respon_Key.BODY, obj.body.toString());
		setResponse(data,NetContant.NET_ACTION.RESP_ACTION);
		return true;
	}

	/**
	 *利用本地广播发送服务器数据，解耦
	 * @param data
	 */
	private void setResponse(Bundle data,String action) {
		Log.i(TAG, "setResponse: " +data);
		LocalBroadcastManager localBroadcast=LocalBroadcastManager.getInstance(this.context);
		Intent intent=new Intent();
		intent.setAction(action);
		intent.putExtras(data);
		localBroadcast.sendBroadcast(intent);
	}
	public static class Respon_Key{
		public static final String ACTION="action";
		public static final String SN="sn";
		public static final String UID="uid";
		public static final String HEAD ="head";
		public static final String BODY ="body";
	}
}
