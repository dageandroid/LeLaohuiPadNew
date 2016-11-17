package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/11/17.
 */

public class SerOrderInfoFinish extends BaseBean implements Parcelable {

    @Override
    public String toString() {
        return "SerOrderInfoFinish{" +
                "orderId=" + orderId +
                ", orderCode='" + orderCode + '\'' +
                ", amountPayable=" + amountPayable +
                ", payStatus=" + payStatus +
                ", payStyle=" + payStyle +
                ", orderStatus=" + orderStatus +
                ", contactAddress='" + contactAddress + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", orderPersionId=" + orderPersionId +
                ", orderPersionName='" + orderPersionName + '\'' +
                ", orderPersionTime='" + orderPersionTime + '\'' +
                ", isDelete=" + isDelete +
                ", updTime='" + updTime + '\'' +
                ", addTime='" + addTime + '\'' +
                ", orgId=" + orgId +
                ", orgTypeId=" + orgTypeId +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierTypeId=" + supplierTypeId +
                ", stockStatus=" + stockStatus +
                '}';
    }

    /**
     * orderId : 302
     * orderCode : 100020161117090448180
     * amountPayable : 38
     * payStatus : 0
     * payStyle : 1
     * orderStatus : 0
     * contactAddress : 张学友(13788888888):地球亚洲中国
     * customerId : 101000000002000001
     * customerName : 张学友
     * customerPhone : 13788888888
     * orderPersionId : 101000000002000001
     * orderPersionName : test
     * orderPersionTime : Nov 17, 2016 9:04:54 AM
     * isDelete : 0
     * updTime : Nov 17, 2016 9:04:54 AM
     * addTime : Nov 17, 2016 9:04:54 AM
     * orgId : 1001
     * orgTypeId : 3
     * supplierId : 1001
     * supplierName : test
     * supplierTypeId : 3
     * stockStatus : 0
     */

    private int orderId;
    private String orderCode;
    private int amountPayable;
    private int payStatus;
    private int payStyle;
    private int orderStatus;
    private String contactAddress;
    private long customerId;
    private String customerName;
    private String customerPhone;
    private long orderPersionId;
    private String orderPersionName;
    private String orderPersionTime;
    private int isDelete;
    private String updTime;
    private String addTime;
    private int orgId;
    private int orgTypeId;
    private int supplierId;
    private String supplierName;
    private int supplierTypeId;
    private int stockStatus;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(int amountPayable) {
        this.amountPayable = amountPayable;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public int getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(int payStyle) {
        this.payStyle = payStyle;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public long getOrderPersionId() {
        return orderPersionId;
    }

    public void setOrderPersionId(long orderPersionId) {
        this.orderPersionId = orderPersionId;
    }

    public String getOrderPersionName() {
        return orderPersionName;
    }

    public void setOrderPersionName(String orderPersionName) {
        this.orderPersionName = orderPersionName;
    }

    public String getOrderPersionTime() {
        return orderPersionTime;
    }

    public void setOrderPersionTime(String orderPersionTime) {
        this.orderPersionTime = orderPersionTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgTypeId() {
        return orgTypeId;
    }

    public void setOrgTypeId(int orgTypeId) {
        this.orgTypeId = orgTypeId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getSupplierTypeId() {
        return supplierTypeId;
    }

    public void setSupplierTypeId(int supplierTypeId) {
        this.supplierTypeId = supplierTypeId;
    }

    public int getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(int stockStatus) {
        this.stockStatus = stockStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.orderId);
        dest.writeString(this.orderCode);
        dest.writeInt(this.amountPayable);
        dest.writeInt(this.payStatus);
        dest.writeInt(this.payStyle);
        dest.writeInt(this.orderStatus);
        dest.writeString(this.contactAddress);
        dest.writeLong(this.customerId);
        dest.writeString(this.customerName);
        dest.writeString(this.customerPhone);
        dest.writeLong(this.orderPersionId);
        dest.writeString(this.orderPersionName);
        dest.writeString(this.orderPersionTime);
        dest.writeInt(this.isDelete);
        dest.writeString(this.updTime);
        dest.writeString(this.addTime);
        dest.writeInt(this.orgId);
        dest.writeInt(this.orgTypeId);
        dest.writeInt(this.supplierId);
        dest.writeString(this.supplierName);
        dest.writeInt(this.supplierTypeId);
        dest.writeInt(this.stockStatus);
    }

    public SerOrderInfoFinish() {
    }

    protected SerOrderInfoFinish(Parcel in) {
        this.orderId = in.readInt();
        this.orderCode = in.readString();
        this.amountPayable = in.readInt();
        this.payStatus = in.readInt();
        this.payStyle = in.readInt();
        this.orderStatus = in.readInt();
        this.contactAddress = in.readString();
        this.customerId = in.readLong();
        this.customerName = in.readString();
        this.customerPhone = in.readString();
        this.orderPersionId = in.readLong();
        this.orderPersionName = in.readString();
        this.orderPersionTime = in.readString();
        this.isDelete = in.readInt();
        this.updTime = in.readString();
        this.addTime = in.readString();
        this.orgId = in.readInt();
        this.orgTypeId = in.readInt();
        this.supplierId = in.readInt();
        this.supplierName = in.readString();
        this.supplierTypeId = in.readInt();
        this.stockStatus = in.readInt();
    }

    public static final Parcelable.Creator<SerOrderInfoFinish> CREATOR = new Parcelable.Creator<SerOrderInfoFinish>() {
        @Override
        public SerOrderInfoFinish createFromParcel(Parcel source) {
            return new SerOrderInfoFinish(source);
        }

        @Override
        public SerOrderInfoFinish[] newArray(int size) {
            return new SerOrderInfoFinish[size];
        }
    };
}
