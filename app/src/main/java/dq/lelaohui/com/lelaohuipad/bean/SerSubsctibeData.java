package dq.lelaohui.com.lelaohuipad.bean;
import android.os.Parcel;
import android.os.Parcelable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/12/1.
 * 提交预约选择服务员
 */

public class SerSubsctibeData extends BaseBean implements Parcelable {
    private String startTime;
    private String endTime;
    private String waiters;
    private String ordreNum;
    private String isChange;//是否允许更换服务员
    private int assignType;//预约方式

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWaiters() {
        return waiters;
    }

    public void setWaiters(String waiters) {
        this.waiters = waiters;
    }

    public String getOrdreNum() {
        return ordreNum;
    }

    public void setOrdreNum(String ordreNum) {
        this.ordreNum = ordreNum;
    }

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public int getAssignType() {
        return assignType;
    }

    public void setAssignType(int assignType) {
        this.assignType = assignType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeString(this.waiters);
        dest.writeString(this.ordreNum);
        dest.writeString(this.isChange);
        dest.writeInt(this.assignType);
        dest.writeInt(this.id);
    }

    public SerSubsctibeData() {
    }

    protected SerSubsctibeData(Parcel in) {
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.waiters = in.readString();
        this.ordreNum = in.readString();
        this.isChange = in.readString();
        this.assignType = in.readInt();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<SerSubsctibeData> CREATOR = new Parcelable.Creator<SerSubsctibeData>() {
        @Override
        public SerSubsctibeData createFromParcel(Parcel source) {
            return new SerSubsctibeData(source);
        }

        @Override
        public SerSubsctibeData[] newArray(int size) {
            return new SerSubsctibeData[size];
        }
    };
}
