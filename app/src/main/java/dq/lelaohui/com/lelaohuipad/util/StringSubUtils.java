package dq.lelaohui.com.lelaohuipad.util;

import android.text.TextUtils;

/**
 * Created by ZTF on 2016/12/7.
 * 字符串截取
 */

public class StringSubUtils {
    /**
     * 字符串截取
     * @param strData
     * @return
     */
    public static String subStr(String strData){
        if (!TextUtils.isEmpty(strData)&&strData.length()>5){
            strData.substring(5,strData.length()-3);
        }else{
            return "";
        }
        return strData;
    }

    /**
     * 分割字符串
     * @param name
     * @param tag
     * @return
     */
    public static String spitValue(String name, String tag) {
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

    /**
     * 字符串追加
     * @param ipInt
     * @return
     */
    public static String int2ip(long ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

}
