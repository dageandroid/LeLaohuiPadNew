package com.lpushcore;


import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;

import java.lang.ref.SoftReference;

/**
 * Created by ThinkPad on 2016/11/15.
 */

public class PushRegCallBack implements XGIOperateCallback {
    SoftReference<String> accountSoftReferenct=null;
    private String TAG="ContentValues";

    public PushRegCallBack(String account){
    }
    @Override
    public void onSuccess(Object o, int i) {
        Log.i(TAG, "onSuccess: "+o+",i="+i);
    }

    @Override
    public void onFail(Object o, int i, String s) {
        Log.i(TAG, "onFail: "+o+",s="+s);
    }
}
