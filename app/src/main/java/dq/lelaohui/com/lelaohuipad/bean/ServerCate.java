package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.NoToJson;

/**
 * Created by ThinkPad on 2016/10/23.
 */

public class ServerCate {

    /**
     * code : 200
     * msg : 查询成功
     * obj : [{"cateId":100,"cateName":"卫生清洁","cateLevel":0,"isDelete":0,"orgId":1,"orgName":"平台","orgTypeId":1,"addTime":"Aug 4, 2016 3:47:38 PM","updTime":"Aug 4, 2016 3:30:51 PM","remark":"卫生清洁方面的服务","pinYin":"weishengqingjie","pY":"wsqj","isPack":0},{"cateId":99,"cateName":"健康","cateLevel":0,"isDelete":0,"orgId":1,"orgName":"平台","orgTypeId":1,"addTime":"Aug 4, 2016 3:46:58 PM","updTime":"Aug 4, 2016 3:30:12 PM","remark":"身体健康方面的各类服务","pinYin":"jiankang","pY":"jk","isPack":0},{"cateId":98,"cateName":"生活照护","cateLevel":0,"isDelete":0,"orgId":1,"orgName":"平台","orgTypeId":1,"addTime":"Aug 4, 2016 3:46:17 PM","updTime":"Aug 4, 2016 3:29:31 PM","remark":"生活各个方面的照护","pinYin":"shenghuozhaohu","pY":"shzh","isPack":0},{"cateId":-1,"cateName":"套餐包类别","isPack":1,"pictureUrl":"/LelaoHuiWebApp/folder/a9.png"}]
     */

    private String code;
    private String msg;
    private String obj;

    public List<ProCateService> getData() {
        if(TextUtils.isEmpty(obj))
            return null;
        Gson gson = new GsonBuilder().create();
        List<ProCateService> data=null;
        data=gson.fromJson(obj,new TypeToken<List<ProCateService>>(){}.getType());
        return data;
    }

    @NoToJson
    private List<ProCateService>  data;

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
}
