package com.lpushcore;

import android.content.Context;
import android.util.Log;

import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import static android.content.ContentValues.TAG;

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
//        XGPushConfig.setAccessId(context.getApplicationContext(),2100242144);
//        XGPushConfig.setAccessKey(context.getApplicationContext(),"AP38FXT819QW");

        PushRegCallBack  callBack=new PushRegCallBack("271767535");
        account="271767535";
        Log.i(TAG, "registerPush: "+context.getApplicationContext().getPackageName());
        XGPushManager.registerPush(context.getApplicationContext(),account,callBack);
//        if(account==null){
//            XGPushManager.registerPush(context.getApplicationContext());
//        }else{
//
////            XGPushManager.registerPush(context.getApplicationContext(),"271767535",callBack);
//        }


    }
}
