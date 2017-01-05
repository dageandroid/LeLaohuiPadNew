package dq.lelaohui.com.lelaohuipad.util;

import android.graphics.Bitmap;

import dq.lelaohui.com.lelaohuipad.port.CamreaImageSetInterface;


public class CallBackUtils {

    private static CamreaImageSetInterface mCallBack;
  
    public static void setCallBack(CamreaImageSetInterface callBack) {  
        mCallBack = callBack;  
    }  
    //CallBackUtils即将发送的数据
    public static void doCallBackMethod(Bitmap bitmap){  
        mCallBack.doSetDrawByte(bitmap);  
    }  
}
