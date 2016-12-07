package dq.lelaohui.com.lelaohuipad.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZTF on 2016/12/7.
 * 时间转换工具类
 */

public class TimeUtils {
    public static String dateToString(Date date) {
        String str = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(date);
        str=sdf.format(date).toString();
        return str;
    }

}
