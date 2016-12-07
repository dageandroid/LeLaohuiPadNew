package dq.lelaohui.com.lelaohuipad.util;

import android.text.TextUtils;

/**
 * Created by ZTF on 2016/12/7.
 * 字符串截取
 */

public class StringSubUtils {
    public static String subStr(String strData){
        if (!TextUtils.isEmpty(strData)&&strData.length()>5){
            strData.substring(5,strData.length()-3);
        }else{
            return "";
        }
        return strData;
    }
}
