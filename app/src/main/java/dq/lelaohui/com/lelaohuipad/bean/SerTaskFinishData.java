package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/12/3.
 */

public class SerTaskFinishData  extends BaseBean implements Parcelable {

    /**
     * serTransId : 415
     * stockDetailId : 7599
     * serviceId : 乐老汇冶金养老中心|107
     * serviceName : 中心头颈椎按摩
     * customerId : 101002000002000197
     * customerName : 吕清风
     * customerPhone : 17310535372
     * orgName : 乐老汇冶金养老中心
     * orgTypeId : 3
     * orgId : 1002
     * supplierTypeId : 3
     * supplierId : 1002
     * proWaitersNum : 1
     * serEndTime : Nov 2, 2016 10:55:00 AM
     * serStartTime : Nov 2, 2016 10:55:00 AM
     * transStatus : 6
     * addTime : Nov 2, 2016 10:54:52 AM
     * orderPerson : 吕清风
     * orderPersonId : 101002000002000197
     * waitersName : 乐腾飞
     * isEval : 1
     */

    private int serTransId;
    private int stockDetailId;
    private String serviceId;
    private String serviceName;
    private long customerId;
    private String customerName;
    private String customerPhone;
    private String orgName;
    private int orgTypeId;
    private int orgId;
    private int supplierTypeId;
    private int supplierId;
    private int proWaitersNum;
    private String serEndTime;
    private String serStartTime;
    private String transStatus;
    private String addTime;
    private String orderPerson;
    private long orderPersonId;
    private String waitersName;
    private int isEval;

    @Override
    public String toString() {
        return "SerTaskFinishData{" +
                "serTransId=" + serTransId +
                ", stockDetailId=" + stockDetailId +
                ", serviceId='" + serviceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgTypeId=" + orgTypeId +
                ", orgId=" + orgId +
                ", supplierTypeId=" + supplierTypeId +
                ", supplierId=" + supplierId +
                ", proWaitersNum=" + proWaitersNum +
                ", serEndTime='" + serEndTime + '\'' +
                ", serStartTime='" + serStartTime + '\'' +
                ", transStatus='" + transStatus + '\'' +
                ", addTime='" + addTime + '\'' +
                ", orderPerson='" + orderPerson + '\'' +
                ", orderPersonId=" + orderPersonId +
                ", waitersName='" + waitersName + '\'' +
                ", isEval=" + isEval +
                '}';
    }

    public int getSerTransId() {
        return serTransId;
    }

    public void setSerTransId(int serTransId) {
        this.serTransId = serTransId;
    }

    public int getStockDetailId() {
        return stockDetailId;
    }

    public void setStockDetailId(int stockDetailId) {
        this.stockDetailId = stockDetailId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getOrgTypeId() {
        return orgTypeId;
    }

    public void setOrgTypeId(int orgTypeId) {
        this.orgTypeId = orgTypeId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getSupplierTypeId() {
        return supplierTypeId;
    }

    public void setSupplierTypeId(int supplierTypeId) {
        this.supplierTypeId = supplierTypeId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getProWaitersNum() {
        return proWaitersNum;
    }

    public void setProWaitersNum(int proWaitersNum) {
        this.proWaitersNum = proWaitersNum;
    }

    public String getSerEndTime() {
        return serEndTime;
    }

    public void setSerEndTime(String serEndTime) {
        this.serEndTime = serEndTime;
    }

    public String getSerStartTime() {
        return serStartTime;
    }

    public void setSerStartTime(String serStartTime) {
        this.serStartTime = serStartTime;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getOrderPerson() {
        return orderPerson;
    }

    public void setOrderPerson(String orderPerson) {
        this.orderPerson = orderPerson;
    }

    public long getOrderPersonId() {
        return orderPersonId;
    }

    public void setOrderPersonId(long orderPersonId) {
        this.orderPersonId = orderPersonId;
    }

    public String getWaitersName() {
        return waitersName;
    }

    public void setWaitersName(String waitersName) {
        this.waitersName = waitersName;
    }

    public int getIsEval() {
        return isEval;
    }

    public void setIsEval(int isEval) {
        this.isEval = isEval;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.serTransId);
        dest.writeInt(this.stockDetailId);
        dest.writeString(this.serviceId);
        dest.writeString(this.serviceName);
        dest.writeLong(this.customerId);
        dest.writeString(this.customerName);
        dest.writeString(this.customerPhone);
        dest.writeString(this.orgName);
        dest.writeInt(this.orgTypeId);
        dest.writeInt(this.orgId);
        dest.writeInt(this.supplierTypeId);
        dest.writeInt(this.supplierId);
        dest.writeInt(this.proWaitersNum);
        dest.writeString(this.serEndTime);
        dest.writeString(this.serStartTime);
        dest.writeString(this.transStatus);
        dest.writeString(this.addTime);
        dest.writeString(this.orderPerson);
        dest.writeLong(this.orderPersonId);
        dest.writeString(this.waitersName);
        dest.writeInt(this.isEval);
    }

    public SerTaskFinishData() {
    }

    protected SerTaskFinishData(Parcel in) {
        this.serTransId = in.readInt();
        this.stockDetailId = in.readInt();
        this.serviceId = in.readString();
        this.serviceName = in.readString();
        this.customerId = in.readLong();
        this.customerName = in.readString();
        this.customerPhone = in.readString();
        this.orgName = in.readString();
        this.orgTypeId = in.readInt();
        this.orgId = in.readInt();
        this.supplierTypeId = in.readInt();
        this.supplierId = in.readInt();
        this.proWaitersNum = in.readInt();
        this.serEndTime = in.readString();
        this.serStartTime = in.readString();
        this.transStatus = in.readString();
        this.addTime = in.readString();
        this.orderPerson = in.readString();
        this.orderPersonId = in.readLong();
        this.waitersName = in.readString();
        this.isEval = in.readInt();
    }

    public static final Parcelable.Creator<SerTaskFinishData> CREATOR = new Parcelable.Creator<SerTaskFinishData>() {
        @Override
        public SerTaskFinishData createFromParcel(Parcel source) {
            return new SerTaskFinishData(source);
        }

        @Override
        public SerTaskFinishData[] newArray(int size) {
            return new SerTaskFinishData[size];
        }
    };
}
