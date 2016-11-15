package com.lpushcore;

import android.content.Context;
import android.content.Intent;

import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushService;

/**
 * Created by ThinkPad on 2016/11/15.
 */
public class LpushManager {
    private static LpushManager ourInstance =null;
    public static LpushManager getInstance() {
        if(ourInstance==null){
            synchronized (LpushManager.class){
                if (ourInstance==null){
                    ourInstance=new LpushManager();
                }
            }
        }

        return ourInstance;
    }

    private LpushManager() {
    }
    public void registerPush(Context context,String account){
        if(context==null){
            throw new RuntimeException("registerPush is param error......");
        }
        PushRegCallBack  callBack=new PushRegCallBack(account);
        Intent service = new Intent(context, XGPushService.class);
        context.startService(service);
        if(account==null){
            XGPushManager.registerPush(context,callBack);
        }else{

            XGPushManager.registerPush(context,account,callBack);
        }
    }
}
