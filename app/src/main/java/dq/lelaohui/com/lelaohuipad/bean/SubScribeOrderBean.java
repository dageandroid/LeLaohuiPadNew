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

    protected SubScribeOrderBean(Parcel in) {
        orderDateStr = in.readString();
        remark = in.readString();
        mobile = in.readString();
        orderPerson = in.readString();
        orderList = in.createTypedArrayList(SerSubsctibeData.CREATOR);
    }

    public static final Creator<SubScribeOrderBean> CREATOR = new Creator<SubScribeOrderBean>() {
        @Override
        public SubScribeOrderBean createFromParcel(Parcel in) {
            return new SubScribeOrderBean(in);
        }

        @Override
        public SubScribeOrderBean[] newArray(int size) {
            return new SubScribeOrderBean[size];
        }
    };

    public List<SerSubsctibeData> getSerSubsctibeDataList() {
        return orderList;
    }

    public void setSerSubsctibeDataList(List<SerSubsctibeData> orderList) {
        this.orderList = orderList;
    }

    List<SerSubsctibeData> orderList;

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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(orderDateStr);
        parcel.writeString(remark);
        parcel.writeString(mobile);
        parcel.writeString(orderPerson);
        parcel.writeTypedList(orderList);
    }
}
