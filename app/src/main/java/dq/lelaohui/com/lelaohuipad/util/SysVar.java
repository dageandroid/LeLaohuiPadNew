package dq.lelaohui.com.lelaohuipad.util;

import android.content.Context;
import android.content.SharedPreferences;

import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;

/**
 * Created by ThinkPad on 2016/10/18.
 */
public class SysVar {


    private Context mContext;
    private static SysVar ourInstance = new SysVar();
    private static String sys_var_name = "lelaohui_Sys";
    private SharedPreferences sharedPreferences = null;

    public static SysVar getInstance() {
        return ourInstance;
    }

    private SysVar() {
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
        if (sharedPreferences == null) {
            sharedPreferences = this.mContext.getSharedPreferences(sys_var_name, Context.MODE_PRIVATE);
        }
    }

    public void setSysVar(String key, Object value) {
        if (sharedPreferences == null || value == null) {
            return;

        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Class valuClass = value.getClass();
        if (valuClass == int.class || valuClass == Integer.class) {
            editor.putInt(key, (Integer) value);
        } else if (valuClass == String.class) {
            editor.putString(key, String.valueOf(value));
        } else if (valuClass == long.class || valuClass == Long.class) {
            editor.putLong(key, (Long) value);
        } else if (valuClass == float.class || valuClass == Float.class) {
            editor.putFloat(key, (Float) value);
        }
        editor.apply();
        editor.commit();
    }

    public int getCenterId() {
        if (sharedPreferences == null) {
            return -1;
        }

        return sharedPreferences.getInt(Protocol_KEY.CENTERID, -1);
    }

    public int getOrgId() {
        if (sharedPreferences == null) {
            return -1;
        }
        return sharedPreferences.getInt(Protocol_KEY.ORG_ID, -1);
    }
    public String getOrgName() {
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString(Protocol_KEY.ORG_NAME, null);
    }

    public String getUserId() {
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString(Protocol_KEY.USERID, null);

    }
    public String getUserName() {
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString(Protocol_KEY.REAL_NAME, null);

    }
    public String getCenterName() {
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString(Protocol_KEY.CENTER_NAME, null);

    }
    public int getOrgType() {
        if (sharedPreferences == null) {
            return -1;
        }
        return sharedPreferences.getInt(Protocol_KEY.ORG_TYPE, -1);
    }

    public int getCenterType() {
        return 3;
    }
}