package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/12/1.
 * 提交预约封装数据
 */

public class SubScribeOrderBean extends BaseBean implements Parcelable {

    private String orderDateStr;
    private String remark;
    private String mobile;
    private String orderPerson;//预约人姓名
    private Long orderPersonId;//预约人Id

    public List<SerSubsctibeData> getSerSubsctibeDataList() {
        return serSubsctibeDataList;
    }

    public void setSerSubsctibeDataList(List<SerSubsctibeData> serSubsctibeDataList) {
        this.serSubsctibeDataList = serSubsctibeDataList;
    }

    List<SerSubsctibeData> serSubsctibeDataList;

    public String getOrderDateStr() {
        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {
        this.orderDateStr = orderDateStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderPerson() {
        return orderPerson;
    }

    public void setOrderPerson(String orderPerson) {
        this.orderPerson = orderPerson;
    }

    public Long getOrderPersonId() {
        return orderPersonId;
    }

    public void setOrderPersonId(Long orderPersonId) {
        this.orderPersonId = orderPersonId;
    }

    public SubScribeOrderBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderDateStr);
        dest.writeString(this.remark);
        dest.writeString(this.mobile);
        dest.writeString(this.orderPerson);
        dest.writeValue(this.orderPersonId);
        dest.writeTypedList(this.serSubsctibeDataList);
    }

    protected SubScribeOrderBean(Parcel in) {
        this.orderDateStr = in.readString();
        this.remark = in.readString();
        this.mobile = in.readString();
        this.orderPerson = in.readString();
        this.orderPersonId = (Long) in.readValue(Long.class.getClassLoader());
        this.serSubsctibeDataList = in.createTypedArrayList(SerSubsctibeData.CREATOR);
    }

    public static final Parcelable.Creator<SubScribeOrderBean> CREATOR = new Parcelable.Creator<SubScribeOrderBean>() {
        @Override
        public SubScribeOrderBean createFromParcel(Parcel source) {
            return new SubScribeOrderBean(source);
        }

        @Override
        public SubScribeOrderBean[] newArray(int size) {
            return new SubScribeOrderBean[size];
        }
    };
}
