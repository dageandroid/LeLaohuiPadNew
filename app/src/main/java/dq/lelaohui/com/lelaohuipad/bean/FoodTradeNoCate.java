package dq.lelaohui.com.lelaohuipad.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ZTF on 2016/12/9.
 */

public class FoodTradeNoCate  {
    @Expose
    private int code;
    @Expose
    private String msg;
    @Expose
    private List<FoodTradeNoData> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<FoodTradeNoData> getData() {
        return data;
    }

    public void setData(List<FoodTradeNoData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FoodTradeNoCate{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
