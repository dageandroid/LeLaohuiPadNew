package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.util.JsonUtil;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;

/**
 * Created by ThinkPad on 2016/11/2.
 */

public class SerInitProPackBean {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }
    public List<SerInitProPack> getData() {
        if(TextUtils.isEmpty(obj))
            return null;
        Gson gson = new GsonBuilder().create();
        List<SerInitProPack> data=null;

        data= (List<SerInitProPack>) JsonUtil.getInstance().jsonToObject(obj,new TypeToken<List<SerInitProPack>>(){}.getType());
        return data;
    }
    private String code;
    private String msg;
    private String obj;
}
