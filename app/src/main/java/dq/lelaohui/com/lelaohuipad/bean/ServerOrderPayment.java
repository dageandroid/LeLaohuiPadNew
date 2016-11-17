package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/11/15.
 */

public class ServerOrderPayment  extends BaseBean implements Parcelable {


    /**
     * amountPayable : 38
     * orderCode : 100020161115011822584
     * orderId : 274
     * payStyle : 1
     */

    private int amountPayable;
    private String orderCode;
    private int orderId;
    private int payStyle;

    public int getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(int amountPayable) {
        this.amountPayable = amountPayable;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(int payStyle) {
        this.payStyle = payStyle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.amountPayable);
        dest.writeString(this.orderCode);
        dest.writeInt(this.orderId);
        dest.writeInt(this.payStyle);
    }

    public ServerOrderPayment() {
    }

    protected ServerOrderPayment(Parcel in) {
        this.amountPayable = in.readInt();
        this.orderCode = in.readString();
        this.orderId = in.readInt();
        this.payStyle = in.readInt();
    }

    public static final Creator<ServerOrderPayment> CREATOR = new Creator<ServerOrderPayment>() {
        @Override
        public ServerOrderPayment createFromParcel(Parcel source) {
            return new ServerOrderPayment(source);
        }

        @Override
        public ServerOrderPayment[] newArray(int size) {
            return new ServerOrderPayment[size];
        }
    };
}
