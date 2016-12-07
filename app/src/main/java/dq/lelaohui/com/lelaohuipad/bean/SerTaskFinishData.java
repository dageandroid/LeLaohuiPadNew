package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

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
    private Date serEndTime;
    private Date serStartTime;
    private String transStatus;
    private Date addTime;
    private String orderPerson;
    private long orderPersonId;
    private String waitersName;
    private int isEval;

    protected SerTaskFinishData(Parcel in) {
        serTransId = in.readInt();
        stockDetailId = in.readInt();
        serviceId = in.readString();
        serviceName = in.readString();
        customerId = in.readLong();
        customerName = in.readString();
        customerPhone = in.readString();
        orgName = in.readString();
        orgTypeId = in.readInt();
        orgId = in.readInt();
        supplierTypeId = in.readInt();
        supplierId = in.readInt();
        proWaitersNum = in.readInt();
        transStatus = in.readString();
        orderPerson = in.readString();
        orderPersonId = in.readLong();
        waitersName = in.readString();
        isEval = in.readInt();
    }

    public static final Creator<SerTaskFinishData> CREATOR = new Creator<SerTaskFinishData>() {
        @Override
        public SerTaskFinishData createFromParcel(Parcel in) {
            return new SerTaskFinishData(in);
        }

        @Override
        public SerTaskFinishData[] newArray(int size) {
            return new SerTaskFinishData[size];
        }
    };

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

    public int getSupplierTypeId() {
        return supplierTypeId;
    }

    public void setSupplierTypeId(int supplierTypeId) {
        this.supplierTypeId = supplierTypeId;
    }

    public int getProWaitersNum() {
        return proWaitersNum;
    }

    public void setProWaitersNum(int proWaitersNum) {
        this.proWaitersNum = proWaitersNum;
    }

    public Date getSerEndTime() {
        return serEndTime;
    }

    public void setSerEndTime(Date serEndTime) {
        this.serEndTime = serEndTime;
    }

    public Date getSerStartTime() {
        return serStartTime;
    }

    public void setSerStartTime(Date serStartTime) {
        this.serStartTime = serStartTime;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(serTransId);
        parcel.writeInt(stockDetailId);
        parcel.writeString(serviceId);
        parcel.writeString(serviceName);
        parcel.writeLong(customerId);
        parcel.writeString(customerName);
        parcel.writeString(customerPhone);
        parcel.writeString(orgName);
        parcel.writeInt(orgTypeId);
        parcel.writeInt(orgId);
        parcel.writeInt(supplierTypeId);
        parcel.writeInt(supplierId);
        parcel.writeInt(proWaitersNum);
        parcel.writeString(transStatus);
        parcel.writeString(orderPerson);
        parcel.writeLong(orderPersonId);
        parcel.writeString(waitersName);
        parcel.writeInt(isEval);
    }
}
