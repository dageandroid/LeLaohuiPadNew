package dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider;

import android.text.TextUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZTF on 2016/12/16.
 */

public class ReqSnQuee {
    private static ConcurrentHashMap<String,String> snQue=new ConcurrentHashMap<>();
    public static void add(String sn){
        snQue.put(sn,sn);
    }
    public static boolean hasSN(String SN){
        if(TextUtils.isEmpty(SN))
            return false;
        boolean flag=snQue.contains(SN);
        return flag;
    }
    public static void removeSn(String SN){
        if(TextUtils.isEmpty(SN))
            return;
        snQue.remove(SN);
    }
    public static boolean isEmpty(){
        return snQue.isEmpty();
    }
}
