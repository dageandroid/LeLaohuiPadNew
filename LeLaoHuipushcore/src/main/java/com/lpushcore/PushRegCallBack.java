package com.lpushcore;


import com.tencent.android.tpush.XGIOperateCallback;

import java.lang.ref.SoftReference;

/**
 * Created by ThinkPad on 2016/11/15.
 */

public class PushRegCallBack implements XGIOperateCallback {
    SoftReference<String> accountSoftReferenct=null;
    public PushRegCallBack(String account){
        accountSoftReferenct=new SoftReference<String>(account);
    }
    @Override
    public void onSuccess(Object o, int i) {

    }

    @Override
    public void onFail(Object o, int i, String s) {

    }
}
