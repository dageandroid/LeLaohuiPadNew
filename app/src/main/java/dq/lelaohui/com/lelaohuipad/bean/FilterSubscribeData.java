package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/11/29.
 */

public class FilterSubscribeData  extends BaseBean implements Parcelable {
    /**
     * serStockDetailId : 7634
     * serviceName : 足疗
     * ruleStr : 每天1次
     * currentNum : 15
     * execNumDay : 1
     * isEnable : 1
     */

    private int id;
    private int serStockDetailId;
    private String serviceName;
    private String ruleStr;
    private int currentNum;
    private int execNumDay;
    private int isEnable;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerStockDetailId() {
        return serStockDetailId;
    }

    public void setSerStockDetailId(int serStockDetailId) {
        this.serStockDetailId = serStockDetailId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRuleStr() {
        return ruleStr;
    }

    public void setRuleStr(String ruleStr) {
        this.ruleStr = ruleStr;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public int getExecNumDay() {
        return execNumDay;
    }

    public void setExecNumDay(int execNumDay) {
        this.execNumDay = execNumDay;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.serStockDetailId);
        dest.writeString(this.serviceName);
        dest.writeString(this.ruleStr);
        dest.writeInt(this.currentNum);
        dest.writeInt(this.execNumDay);
        dest.writeInt(this.isEnable);
    }

    public FilterSubscribeData() {
    }

    protected FilterSubscribeData(Parcel in) {
        this.id = in.readInt();
        this.serStockDetailId = in.readInt();
        this.serviceName = in.readString();
        this.ruleStr = in.readString();
        this.currentNum = in.readInt();
        this.execNumDay = in.readInt();
        this.isEnable = in.readInt();
    }

    public static final Creator<FilterSubscribeData> CREATOR = new Creator<FilterSubscribeData>() {
        @Override
        public FilterSubscribeData createFromParcel(Parcel source) {
            return new FilterSubscribeData(source);
        }

        @Override
        public FilterSubscribeData[] newArray(int size) {
            return new FilterSubscribeData[size];
        }
    };
}
